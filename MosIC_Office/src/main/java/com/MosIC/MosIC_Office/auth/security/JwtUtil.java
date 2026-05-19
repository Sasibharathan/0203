package com.MosIC.MosIC_Office.auth.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // ⚠️ Change this in production — must be 32+ characters
    // -----------------------------------------------------------------------
    //private static final String SECRET   = "MosIC_Office_Secret_Key_2026_XXXX";
    //private static final long   EXPIRY_MS = 8 * 60 * 60 * 1000L; // 8 hours

    //private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    @Value("${jwt.secret}")
    private String secret;

    // ── CHANGED: 8 hours → 2 hours ───────────────────────────────────────────
    // Shorter expiry = less damage if a token is ever stolen.
    // User will need to log in again after 2 hours of inactivity.
    private static final long EXPIRY_MS = 2 * 60 * 60 * 1000L;

    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ── Generate token ────────────────────────────────────────────────────────
    public String generateToken(String gmail, String username, String role) {
        return Jwts.builder()
                .setSubject(gmail)
                .claim("username", username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_MS))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ── Extract role from token ───────────────────────────────────────────────
    public String extractRole(String token) {
        return (String) parseClaims(token).get("role");
    }

    // ── Extract gmail (subject) from token ───────────────────────────────────
    public String extractGmail(String token) {
        return parseClaims(token).getSubject();
    }

    // ── Extract username claim from token ─────────────────────────────────────
    public String extractUsername(String token) {
        return (String) parseClaims(token).get("username");
    }

    // ── Validate: signature + expiry ──────────────────────────────────────────
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ── Internal parser ───────────────────────────────────────────────────────
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}