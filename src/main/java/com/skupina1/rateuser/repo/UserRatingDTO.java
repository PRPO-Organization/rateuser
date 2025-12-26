package com.skupina1.rateuser.repo;

import com.skupina1.rateuser.user.UserRating;

public  class UserRatingDTO {
    private String userId ;
    private short userRating;
    private String comment ;
    public UserRatingDTO() {
    }
    public UserRatingDTO(String userId, short userRating,  String comment) {
        this.userId = userId;
        this.userRating = userRating;
        this.comment = comment;
    }
    public UserRatingDTO(UserRating userRating){


    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public short getUserRating() {
        return userRating;
    }
    public  void setUserRating(short userRating) {
        this.userRating = userRating;
    }


    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

}