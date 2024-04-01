package com.example.xavier.Repository.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.xavier.Repository.UserRepository;
import com.example.xavier.model.UsersEntity;

@Service
public class UserRepositoryServices {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public UserRepositoryServices(UserRepository userRepo,PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.passwordEncoder=encoder;
    }

    public boolean existingUser(String username){
        return userRepo.existsByUsername(username);
    }

    public String addUser(String username, String email, String password){
        UsersEntity user =new UsersEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return "User Created";
    }

    public String isAuthenticUser(String username, String password){
        if(existingUser(username)){
            UsersEntity user=userRepo.findByUsername(username).get();
            boolean isValidName=user.getUsername().equalsIgnoreCase(username);
            boolean isValidPassword=passwordEncoder.matches(password,user.getPassword());
            if(isValidName && isValidPassword){
                return "success";
            }
            return "Invalid password!";
        }
        return "user not found! Please Register";
    }
}