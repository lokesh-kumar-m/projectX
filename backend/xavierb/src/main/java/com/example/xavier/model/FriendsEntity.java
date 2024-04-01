package com.example.xavier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FriendsEntity {
    @Id
    @GeneratedValue
    private int id;
    private String friend;
    private String currentuser;
    private double amount;
    public FriendsEntity(int id, String friendName, String user, double amount) {
        this.id=id;
        this.friend = friendName;
        this.currentuser = user;
        this.amount = amount;
    }
    public FriendsEntity() {
    }
   
    public String getFriend() {
        return friend;
    }
    public void setFriend(String friendName) {
        this.friend = friendName;
    }
    public String getCurrentuser() {
        return currentuser;
    }
    public void setCurrentuser(String user) {
        this.currentuser = user;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "FriendsEntity [friendName=" + friend + ", user=" + currentuser + ", amount=" + amount + "]";
    }

}
