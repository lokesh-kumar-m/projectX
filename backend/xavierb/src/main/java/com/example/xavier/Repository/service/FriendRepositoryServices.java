package com.example.xavier.Repository.service;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.example.xavier.Repository.FriendsRepository;
import com.example.xavier.model.FriendsEntity;

//without service or constructor the applications gives parameter in the constructor required Bean but not find
@Service
public class FriendRepositoryServices {
    private FriendsRepository friendServices;

    public FriendRepositoryServices(FriendsRepository friendServices) {
        this.friendServices = friendServices;
    }
    
    public List<FriendsEntity> getMyFriends(String currentUser){
        Predicate<? super FriendsEntity> predicate=list->list.getCurrentuser().equalsIgnoreCase(currentUser);
        List<FriendsEntity> friendsList=friendServices.findAll().stream().filter(predicate).toList();
        return friendsList;
    }
    public String addNewFriend(String currentuser, String name,double amount ){
        FriendsEntity friend=new FriendsEntity();
        friend.setCurrentuser(currentuser);
        friend.setAmount(amount);
        friend.setFriend(name);
        friendServices.save(friend);
        return "new friend added!";
    }
    public void updateAmount(int id, double amount){
        friendServices.updateAmount(id, amount);
    }
}
