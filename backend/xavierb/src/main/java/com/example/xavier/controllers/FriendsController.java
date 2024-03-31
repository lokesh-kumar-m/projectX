package com.example.xavier.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.model.FriendsEntity;

@RestController
public class FriendsController {
    
    @GetMapping(path="/home")
    public List<FriendsEntity> getAllFriends(@PathVariable String name){
        
        return new ArrayList<>();
    }
}
