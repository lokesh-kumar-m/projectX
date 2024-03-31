package com.example.xavier.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.RegisterUserDto;
import com.example.xavier.Repository.UserRepository;
import com.example.xavier.model.UsersEntity;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private UserRepository userService;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/users/register")
    public ResponseEntity<String> getRegisterUser(@RequestBody RegisterUserDto registerUser) {
        // logger.info("********lome testing*******");
        // logger.info("" + registerUser);
        // logger.info("" + registerUser.getEmail());
        if (userService.existsByUsername(registerUser.getUsername())) {
            return new ResponseEntity<>("User Exists please login", HttpStatus.BAD_REQUEST);
        }
        UsersEntity user = new UsersEntity();
        user.setUsername(registerUser.getUsername());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        userService.save(user);
        return new ResponseEntity<>("User Created", HttpStatus.OK);
    }
    @PostMapping("/users/login")
    public ResponseEntity<String> getUserDetails(@RequestBody RegisterUserDto userRegistry){
        UsersEntity user=userService.findByUsername(userRegistry.getUsername()).get();
        boolean isValidUserName=user.getUsername().equalsIgnoreCase(userRegistry.getUsername());
        logger.info(" "+isValidUserName);
        boolean isValidPassword=passwordEncoder.matches(userRegistry.getPassword(),user.getPassword());
        logger.info(" "+isValidPassword);
        if(isValidPassword&&isValidUserName){
            return new ResponseEntity<>("Welcome user",HttpStatus.OK);    
        }
        return new ResponseEntity<>("invalid credentials",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/Hello")
    public String getHello() {
        return "HEyya Buddy!";
    }

}
