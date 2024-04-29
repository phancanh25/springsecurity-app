package com.davidphan.springsecurity.services;

import com.davidphan.springsecurity.dto.JwtAuthenticationResponse;
import com.davidphan.springsecurity.dto.RefreshTokenRequest;
import com.davidphan.springsecurity.dto.SignupRequest;
import com.davidphan.springsecurity.entities.User;

public interface AuthenticationService {
    public User signup(SignupRequest signupRequest);
    public JwtAuthenticationResponse signin(SignupRequest signupRequest);
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
}
