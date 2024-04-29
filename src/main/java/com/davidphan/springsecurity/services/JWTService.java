package com.davidphan.springsecurity.services;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    public String generateToken(UserDetails userDetails);
    public String generateRefreshToken(Map<String, Object> extraClaims ,UserDetails userDetails);
    public String extractUsername(String token);
    public boolean isTokenValid(String token, UserDetails userDetails);
}
