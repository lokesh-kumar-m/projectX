package com.example.xavier.Repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.example.xavier.Repository.UserRepository;
import com.example.xavier.model.UsersEntity;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserRepository userService;
    
    @Autowired
    public UserDetailsService(UserRepository userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity user=userService.findByUsername(username)
        .orElseThrow(()->new UsernameNotFoundException("Username not found"));
        
        return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities("USER")
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
    }
}
