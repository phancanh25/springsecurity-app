package com.davidphan.springsecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
//  http://localhost:8080/api/v1/user attach with Bearer Token
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello User");
    }
}
