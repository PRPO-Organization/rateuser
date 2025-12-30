package com.skupina1.rateuser.repo;


import java.util.Date;


public class UserDTO{
    private Long userId ;
    private String username ;
    private String email;
    public  UserDTO() {}
    public UserDTO(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long  getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
}