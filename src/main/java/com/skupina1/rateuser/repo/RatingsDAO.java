package com.skupina1.rateuser.repo;

import com.skupina1.rateuser.user.UserRating;

import java.sql.SQLException;
import java.util.List;

interface RatingsDAO {
    Double getUserVotes(String user_id);
    Double getUserRating(String rated_user_id);
    boolean addUserRating(UserRating userRatingDTO) throws Exception;
    boolean changeUserRating(UserRating userRatingDTO) throws Exception;
    boolean removeUserRating(Short id) throws Exception;
    UserRating findUserRating(String rated_user_id) throws Exception;
    List<CommentDTO> findComment(String id) throws Exception;
    boolean adduser(User userDTO)throws Exception;
    User findUser (String usedId) throws Exception;
}