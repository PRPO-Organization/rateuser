package com.skupina1.rateuser.repo;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@NamedQueries(
        {
                @NamedQuery(
                    name="User.findById",
                    query = "select  u from User u where u.userId =:id"
                )
        }
)
@Table(name="users")
public class User{
    @Column(name="user_id" , nullable = false)
    @Id
    private String userId ;
    private String username ;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable=false, updatable=false)
    private Date createdAt;
    @PrePersist
    public void prePersist(){
        if (createdAt == null){
            this.createdAt = new Date();
        }
    }
    public User(){}
    public  User(String userId, String username, String email){
        this.userId = userId;
        this.email=email;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public  String getUsername() {
        return username;
    }
    public  void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public User(UserDTO userDTO){
        this.userId = userDTO.getUserId();
        this.username = userDTO.getUsername();
        this.email = userDTO.getEmail();
    }
}