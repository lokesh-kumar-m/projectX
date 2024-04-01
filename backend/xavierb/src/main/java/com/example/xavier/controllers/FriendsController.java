package com.example.xavier.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.xavier.Dto.FriendsDto;
import com.example.xavier.Repository.FriendsRepository;
import com.example.xavier.model.FriendsEntity;

@RestController
public class FriendsController {

    private FriendsRepository friendsRepository;
    public FriendsController(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    @GetMapping(path="/home/friends/")
    public ResponseEntity<List<FriendsEntity>> getMyFriends(){
        // List<FriendsEntity> friendsList =new ArrayList();
        List<FriendsEntity> friendsList=friendsRepository.findAll();
        Predicate<? super FriendsEntity> predicate=list->list.getCurrentuser().equalsIgnoreCase("Lome");
        List<FriendsEntity> lst =friendsList.stream().filter(predicate).toList();
        return new ResponseEntity<>(lst,HttpStatus.OK);
    }
    @PostMapping(path="/home/newfriend")
    public ResponseEntity<String> addNewFriends(@RequestBody FriendsDto newFriend){
        FriendsEntity friends=new FriendsEntity();
        friends.setCurrentuser(newFriend.getCurrentuser());
        friends.setAmount(0);
        friends.setFriend(newFriend.getFriend());
        friendsRepository.save(friends);
        return new ResponseEntity<>("Added new Friend",HttpStatus.OK);
    }
}
