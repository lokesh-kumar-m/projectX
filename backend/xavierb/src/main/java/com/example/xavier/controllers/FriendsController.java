package com.example.xavier.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.FriendsDto;
import com.example.xavier.Repository.service.FriendRepositoryServices;
import com.example.xavier.model.FriendsEntity;

@RestController
public class FriendsController {
    private Logger logger=LoggerFactory.getLogger(getClass());
    private FriendRepositoryServices friendsRepo;

    public FriendsController(FriendRepositoryServices friendsRepo) {
        this.friendsRepo = friendsRepo;
    }
    @GetMapping(path="/home/{currentuser}")
    public ResponseEntity<List<FriendsEntity>> getMyFriends(@PathVariable String currentuser){
        return new ResponseEntity<>(friendsRepo.getMyFriends(currentuser),HttpStatus.OK);
    }
    
    @PostMapping(path="/home/newfriend")
    public ResponseEntity<String> addNewFriends(@RequestBody FriendsDto newFriend){
        logger.info(""+newFriend);
        String response=friendsRepo.addNewFriend(newFriend.getCurrentuser(), newFriend.getFriend(), newFriend.getAmount());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping(path="/home/{currentuser}/{id}/update/{amount}")
    public void updateExistingAmount(@PathVariable int id,@PathVariable double amount){
        friendsRepo.updateAmount(id, amount);
    }
}
