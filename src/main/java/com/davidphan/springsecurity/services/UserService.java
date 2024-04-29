package com.davidphan.springsecurity.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserService {
    public UserDetailsService userDetailsService();
}
