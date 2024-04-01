package com.example.xavier.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.FriendsDto;
import com.example.xavier.Repository.service.FriendRepositoryServices;
import com.example.xavier.model.FriendsEntity;

@RestController
public class FriendsController {

    private FriendRepositoryServices friendsRepo;

    public FriendsController(FriendRepositoryServices friendsRepo) {
        this.friendsRepo = friendsRepo;
    }
    @GetMapping(path="/home/friends/")
    public ResponseEntity<List<FriendsEntity>> getMyFriends(){
        
        return new ResponseEntity<>(friendsRepo.getMyFriends("Lome"),HttpStatus.OK);
    }
    @PostMapping(path="/home/newfriend")
    public ResponseEntity<String> addNewFriends(@RequestBody FriendsDto newFriend){
        String response=friendsRepo.addNewFriend(newFriend.getCurrentuser(), newFriend.getFriend(), newFriend.getAmount());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
