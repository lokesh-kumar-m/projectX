package com.example.xavier.Dto;


public class RegisterUserDto {
    
    private String username;
    private String password;
    private String email;

    

    public RegisterUserDto(String name, String password,String email) {
        this.username = name;
        this.password = password;
        this.email=email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterUseDto [name=" + username + ", password=" + password + "]";
    }
    
}
