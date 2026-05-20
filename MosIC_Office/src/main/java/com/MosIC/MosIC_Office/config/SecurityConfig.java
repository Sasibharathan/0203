package com.MosIC.MosIC_Office.config;

import com.MosIC.MosIC_Office.auth.security.AuthEntryPoint;
import com.MosIC.MosIC_Office.auth.security.JwtFilter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter          jwtFilter;
    private final UserDetailsService userDetailsService;
    private final AuthEntryPoint     authEntryPoint;

    public SecurityConfig(JwtFilter jwtFilter,
                          UserDetailsService userDetailsService,
                          AuthEntryPoint authEntryPoint) {
        this.jwtFilter          = jwtFilter;
        this.userDetailsService = userDetailsService;
        this.authEntryPoint     = authEntryPoint;
    }

    // ── BCrypt strength 12 ────────────────────────────────────────────────────
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // ── Wires UserDetailsService + PasswordEncoder into Spring Security ────────
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // ── Exposes AuthenticationManager for use in UserController ───────────────
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    // ── CORS ──────────────────────────────────────────────────────────────────
    // Localhost only for now — when you deploy to AWS replace these with
    // your real domain: "https://yourdomain.com"
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of(
            "http://localhost:3000",
            "http://172.16.88.113:3000",
            "http://192.168.110.1:3000",
            "https://*.vercel.app"               // ← this covers ALL vercel previews          
            // "https://yourdomain.com"  ← uncomment and set when deploying to AWS
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // ── Security rules ────────────────────────────────────────────────────────
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // Clean JSON 401 when token is missing or invalid
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(authEntryPoint)
                .accessDeniedHandler((request, response, denied) -> {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.setContentType("application/json");
                    response.getWriter().write(
                        "{\"status\":403,\"error\":\"Forbidden\"," +
                        "\"message\":\"You do not have permission to access this resource\"}"
                    );
                })
            )

            // Stateless — JWT only, no sessions
            .sessionManagement(sm -> sm
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                // ✅ PUBLIC — no token needed
                .requestMatchers("/api/auth/login").permitAll()

                // 🔒 ROLE ENFORCEMENT
                .requestMatchers(HttpMethod.DELETE, "/api/**")
                    .hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/auth/users/**")
                    .hasAnyRole("SUPERUSER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/auth/register")
                    .hasAnyRole("SUPERUSER", "ADMIN")

                // 🔒 everything else — valid JWT, any role
                .anyRequest().authenticated()
            )

            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
{/*
            .authorizeHttpRequests(auth -> auth

                // ✅ PUBLIC — login only, no token needed
                .requestMatchers("/api/auth/login").permitAll()

                // 🔒 ROLE ENFORCEMENT — backend now enforces roles
                // not just the frontend UI hiding buttons
                .requestMatchers(HttpMethod.DELETE, "/api/**")
                    .hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/**")
                    .hasAnyRole("SUPERUSER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/auth/register")
                    .hasAnyRole("SUPERUSER", "ADMIN")

                // 🔒 everything else requires a valid JWT (any role)
                .anyRequest().authenticated()
            )
*/}
        return http.build();
    }
}
