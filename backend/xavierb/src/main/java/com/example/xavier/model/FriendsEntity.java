package com.example.xavier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class FriendsEntity {

    @Id
    @GeneratedValue
    private int id;
    private String friendName;
    private String user;
    private long amount;
    public FriendsEntity(int id, String friendName, String user, long amount) {
        this.id = id;
        this.friendName = friendName;
        this.user = user;
        this.amount = amount;
    }
    public FriendsEntity() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFriendName() {
        return friendName;
    }
    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "FriendsEntity [id=" + id + ", friendName=" + friendName + ", user=" + user + ", amount=" + amount + "]";
    }

}
