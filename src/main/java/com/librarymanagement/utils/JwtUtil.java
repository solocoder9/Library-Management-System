package com.librarymanagement.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expirationMs;

    public JwtUtil(@Value("${app.jwt-secret}") String secret,
                   @Value("${app.jwt-expiration-ms}") long expirationMs) {
        // Decode the base64-encoded secret key
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
        this.expirationMs = expirationMs;
    }

    // Generate JWT token with user ID and role
    public String generateToken(String userid, String role) {
        return Jwts.builder()
                .setSubject(userid) 
                .claim("role", role) 
                .setIssuedAt(new Date()) 
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs)) 
                .signWith(secretKey, SignatureAlgorithm.HS256) 
                .compact(); 
    }

    // Extract user ID from token
    public String extractUserid(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract role from token
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // Validate token (check expiration and signature)
    public boolean validateToken(String token) {
        try {
            Claims claims = extractClaims(token);
            return !claims.getExpiration().before(new Date()); 
        } catch (Exception e) {
            return false; 
        }
    }

    // Extract claims from token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) 
                .build()
                .parseClaimsJws(token) 
                .getBody(); 
    }
}