package com.skupina1.rateuser.repo;

import com.skupina1.rateuser.user.UserRating;

public  class UserRatingDTO {
    private Long ratedUserId ;
    private short userRating;
    private String comment ;
    public UserRatingDTO() {
    }
    public UserRatingDTO(Long userId, short userRating,  String comment) {
        this.ratedUserId= userId;
        this.userRating = userRating;
        this.comment = comment;
    }
    public UserRatingDTO(UserRating userRating){


    }
    public Long getRatedUserId() {
        return ratedUserId;
    }
    public void setRatedUserId(Long userId) {
        this.ratedUserId = userId;
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