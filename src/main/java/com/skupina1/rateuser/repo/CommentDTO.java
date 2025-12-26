package com.skupina1.rateuser.repo;

import javax.xml.stream.events.Comment;

public class CommentDTO {
    private String userId;
    private String comment;
    public CommentDTO() {
    }
    public CommentDTO(String userId, String comment) {
        this.userId = userId;
        this.comment = comment;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

}