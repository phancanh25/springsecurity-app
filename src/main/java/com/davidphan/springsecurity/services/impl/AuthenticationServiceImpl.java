package com.davidphan.springsecurity.services.impl;

import com.davidphan.springsecurity.dto.JwtAuthenticationResponse;
import com.davidphan.springsecurity.dto.RefreshTokenRequest;
import com.davidphan.springsecurity.dto.SignupRequest;
import com.davidphan.springsecurity.entities.Role;
import com.davidphan.springsecurity.entities.User;
import com.davidphan.springsecurity.repositories.UserRepository;
import com.davidphan.springsecurity.services.AuthenticationService;
import com.davidphan.springsecurity.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @Override
    public User signup(SignupRequest signupRequest){
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstname(signupRequest.getFirstname());
        user.setSecondname(signupRequest.getLastname());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SignupRequest signupRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signupRequest.getEmail(),signupRequest.getPassword()));
        var user = userRepository.findByEmail(signupRequest.getEmail()).orElseThrow( () -> new IllegalArgumentException("Invalid email or password!!!"));
        var jwt = jwtService.generateToken(user);
        var refresheToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setRefreshToken(refresheToken);
        jwtAuthenticationResponse.setToken(jwt);
        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        String userEmail = jwtService.extractUsername(refreshToken.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshToken.getToken(),user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

}
