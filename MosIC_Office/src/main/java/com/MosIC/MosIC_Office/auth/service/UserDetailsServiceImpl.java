package com.MosIC.MosIC_Office.auth.service;


import com.MosIC.MosIC_Office.auth.entity.UserEntity;
import com.MosIC.MosIC_Office.auth.repository.UserRepository;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Called by Spring Security during login.
     * The "username" here is the gmail — that is our login field.
     */
    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByGmail(gmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + gmail));

        // Reject disabled accounts before token is issued
        if (user.getStatus() == null || user.getStatus() != 1) {
            //throw new UsernameNotFoundException("Account is disabled: " + gmail);
            throw new DisabledException("Account is disabled: " + gmail);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getGmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getProfile().toUpperCase()))
                // "admin" → ROLE_ADMIN, "superuser" → ROLE_SUPERUSER, "user" → ROLE_USER
        );
    }
}