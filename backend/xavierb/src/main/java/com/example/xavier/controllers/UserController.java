package com.example.xavier.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.RegisterUserDto;
import com.example.xavier.Repository.service.UserRepositoryServices;
import com.example.xavier.security.JwtResponse;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserRepositoryServices userServices;
    private JwtResponse jwtResponse;

    public UserController( JwtResponse jwtResponse, UserRepositoryServices userServices) {
        this.jwtResponse=jwtResponse;
        this.userServices=userServices;
    }

    @PostMapping(path = "/users/register")
    public ResponseEntity<String> getRegisterUser(@RequestBody RegisterUserDto registerUser) {
        // logger.info("********lome testing*******");
        if (userServices.existingUser(registerUser.getUsername())) {
            return new ResponseEntity<>("User Exists please login", HttpStatus.BAD_REQUEST);
        }
        String response=userServices.addUser(registerUser.getUsername(), registerUser.getEmail(), registerUser.getPassword());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/users/login")
    public ResponseEntity<String> getUserDetails(@RequestBody RegisterUserDto userRegistry){
        String response;
        response=userServices.isAuthenticUser(userRegistry.getUsername(), userRegistry.getPassword());
        if(response.equals("success")){
            String token=jwtResponse.getJwtToken(userRegistry.getUsername());
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users/Hello")
    public String getHello() {
        return "HEyya Buddy!";
    }

}


