package com.davidphan.springsecurity.controllers;

import com.davidphan.springsecurity.dto.JwtAuthenticationResponse;
import com.davidphan.springsecurity.dto.RefreshTokenRequest;
import com.davidphan.springsecurity.dto.SignupRequest;
import com.davidphan.springsecurity.entities.User;
import com.davidphan.springsecurity.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

//    payload
//    {
//        "firstname": "David",
//            "lastname": "Phan",
//            "email": "test@gmail.com",
//            "password": "test"
//    }
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest){
        return ResponseEntity.ok(authenticationService.signup(signupRequest));
    }
//    {
//        "email": "test@gmail.com",
//            "password": "test"
//    }
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignupRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }
//    {
//        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImlhdCI6MTcxNDM2ODAxNSwiZXhwIjoxNzE0OTcyODE1fQ.Cv_LdvgXCG2XHoVByaFVdobq2kvjet5NBShX7p63DTc"
//    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
