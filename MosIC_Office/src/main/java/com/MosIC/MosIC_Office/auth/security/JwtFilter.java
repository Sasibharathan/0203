package com.MosIC.MosIC_Office.auth.security;


import com.MosIC.MosIC_Office.auth.security.JwtUtil;
import com.MosIC.MosIC_Office.auth.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;   // ✅ Added back

    public JwtFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest  request,
                                    HttpServletResponse response,
                                    FilterChain         filterChain)
            throws ServletException, IOException {

        // ── 1. Read Authorization header ──────────────────────────────────────

            String token = null;
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7); //header starts with "Bearer ", so remove it to get the token
            } else {
                String queryToken = request.getParameter("token");
                if (queryToken != null && !queryToken.isBlank()) {
                    token = queryToken;
                }
            }

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

        // ── 2. Validate token ─────────────────────────────────────────────────
        // final String token = authHeader.substring(7);

        if (!jwtUtil.isTokenValid(token)) {
            // Tampered or expired token — pass through; AuthEntryPoint will return 401
            filterChain.doFilter(request, response);
            return;
        }

        // ── 3. Set authentication in SecurityContext ───────────────────────────
        final String gmail = jwtUtil.extractGmail(token);
        final String role  = jwtUtil.extractRole(token);   // ✅ read from token

        if (gmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try{
                UserDetails userDetails = userDetailsService.loadUserByUsername(gmail);
                // Role comes from the token — no extra DB call needed here
                //List<SimpleGrantedAuthority> authorities = List.of(
                //        new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())
                //);
                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (Exception e) {
                // User not found or disabled — pass through; AuthEntryPoint will return 401
                filterChain.doFilter(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}