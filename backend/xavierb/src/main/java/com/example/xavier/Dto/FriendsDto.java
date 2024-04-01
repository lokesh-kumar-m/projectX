package com.example.xavier.Dto;

public class FriendsDto {
    private String currentuser;
    private String friend;
    private long amount;
    public FriendsDto(String user, String name, long amount) {
        this.currentuser = user;
        this.friend = name;
        this.amount = amount;
    }
    public String getCurrentuser() {
        return currentuser;
    }
    public void setCurrentuser(String user) {
        this.currentuser = user;
    }
    public String getFriend() {
        return friend;
    }
    public void setFriend(String name) {
        this.friend = name;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
}
