package com.MosIC.MosIC_Office.auth.controller;

import com.MosIC.MosIC_Office.auth.dto.AuthResponse;
import com.MosIC.MosIC_Office.auth.dto.LoginRequest;
import com.MosIC.MosIC_Office.auth.dto.RegisterRequest;
import com.MosIC.MosIC_Office.auth.dto.UserDTO;
import com.MosIC.MosIC_Office.auth.entity.UserEntity;
import com.MosIC.MosIC_Office.auth.mapper.UserMapper;
import com.MosIC.MosIC_Office.auth.repository.UserRepository;
import com.MosIC.MosIC_Office.auth.security.JwtUtil;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository        userRepository;
    private final PasswordEncoder       passwordEncoder;
    private final JwtUtil               jwtUtil;
    private final UserMapper            userMapper;

    // Rate-limit buckets — one per IP address
    private final Map<String, Bucket> loginBuckets = new ConcurrentHashMap<>();

    public UserController(AuthenticationManager authenticationManager,
                          UserRepository        userRepository,
                          PasswordEncoder       passwordEncoder,
                          JwtUtil               jwtUtil,
                          UserMapper            userMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository        = userRepository;
        this.passwordEncoder       = passwordEncoder;
        this.jwtUtil               = jwtUtil;
        this.userMapper            = userMapper;
    }

    // 5 attempts per IP per minute
    private Bucket getLoginBucket(String ip) {
        return loginBuckets.computeIfAbsent(ip, k ->
            Bucket.builder()
                .addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1))))
                .build()
        );
    }

    // =========================================================================
    // POST /api/auth/login   — PUBLIC
    // =========================================================================
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request,
                                   HttpServletRequest httpRequest) {

        // Rate limiting — block after 5 attempts per IP per minute
        String ip = httpRequest.getRemoteAddr();
        if (!getLoginBucket(ip).tryConsume(1)) {
            return ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of(
                            "status",  429,
                            "error",   "Too Many Requests",
                            "message", "Too many login attempts. Please wait 1 minute."
                    ));
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getGmail(), request.getPassword())
            );

            UserEntity user = userRepository.findByGmail(request.getGmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.generateToken(user.getGmail(), user.getUsername(), user.getProfile());

            return ResponseEntity.ok(new AuthResponse(
                    token,
                    user.getUsername(),
                    user.getGmail(),
                    user.getContact(),
                    user.getStatus(),
                    user.getProfile()
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                            "status",  401,
                            "error",   "Unauthorized",
                            "message", "Invalid gmail or password"
                    ));
        } catch (DisabledException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of(
                            "status",  403,
                            "error",   "Forbidden",
                            "message", "Your account is disabled. Contact an administrator."
                    ));
        }
    }

    // =========================================================================
    // POST /api/auth/register   — REQUIRES VALID JWT
    // =========================================================================
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

        if (userRepository.existsByGmail(request.getGmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Gmail already registered: " + request.getGmail()));
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Username already taken: " + request.getUsername()));
        }

        UserEntity newUser = userMapper.toUserEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setStatus(request.getStatus() != null ? request.getStatus() : 1);
        newUser.setProfile(request.getProfile() != null ? request.getProfile() : "user");

        userRepository.save(newUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", "User registered successfully: " + newUser.getGmail()));
    }

    // =========================================================================
    // GET /api/auth/users   — REQUIRES VALID JWT
    // =========================================================================
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userMapper.toUserDTOs(userRepository.findAll());
        return ResponseEntity.ok(users);
    }

    // =========================================================================
    // GET /api/auth/users/{id}   — REQUIRES VALID JWT
    // =========================================================================
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return ResponseEntity.ok(userMapper.toUserDTO(user));
    }

    // =========================================================================
    // PUT /api/auth/users/{id}   — REQUIRES VALID JWT
    // =========================================================================
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @Valid @RequestBody RegisterRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setUsername(request.getUsername());
        user.setContact(request.getContact());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getProfile() != null && !request.getProfile().isBlank()) {
            user.setProfile(request.getProfile());
        }

        userRepository.save(user);
        return ResponseEntity.ok(Map.of("message", "User updated: " + user.getGmail()));
    }

    // =========================================================================
    // PUT /api/auth/users/{id}/status   — REQUIRES VALID JWT
    // =========================================================================
    @PutMapping("/users/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                          @RequestBody Map<String, Integer> body) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setStatus(body.get("status"));
        userRepository.save(user);

        String statusLabel = body.get("status") == 1 ? "enabled" : "disabled";
        return ResponseEntity.ok(Map.of(
                "message", "User " + user.getGmail() + " is now " + statusLabel
        ));
    }

    // =========================================================================
    // DELETE /api/auth/users/{id}   — REQUIRES VALID JWT
    // =========================================================================
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
        return ResponseEntity.ok(Map.of("message", "User deleted: " + user.getGmail()));
    }
}