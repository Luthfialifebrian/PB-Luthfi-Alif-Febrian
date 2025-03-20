package com.example.hallowluthfi.Models;

public class UserDetails {

    private String userId, username, userEmail, userPassword, userNim;

    public UserDetails(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNim() {
        return userNim;
    }

    public void setUserNim(String userNim) {
        this.userNim = userNim;
    }

    public UserDetails(String userId, String username, String userEmail, String userNim, String userPassword) {
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.userNim = userNim;
        this.userPassword = userPassword;
    }

}
