package com.skupina1.rateuser.repo;


public  class AvgRatingDTO {
    private String rated_user_id ;
    private short rating ;

    public AvgRatingDTO(){}
    public AvgRatingDTO(String rated_user_id, short rating) {
        this.rated_user_id = rated_user_id;
        this.rating = rating;
    }

    public String getRated_user_id() {
        return rated_user_id;
    }
    public void setRated_user_id(String rated_user_id) {
        this.rated_user_id = rated_user_id;
    }
    public short getRating() {
        return this.rating;
    }
    public void setRating(short rating) {
        this.rating = rating;
    }

}