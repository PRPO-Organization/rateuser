package com.skupina1.rateuser.repo;

import com.skupina1.rateuser.user.UserRating;

import java.sql.SQLException;
import java.util.List;

interface RatingsDAO {
    Double getUserVotes(Long user_id);
    Double getUserRating(Long rated_user_id);
    boolean addUserRating(UserRating userRatingDTO) throws Exception;
    boolean changeUserRating(UserRating userRatingDTO) throws Exception;
    boolean removeUserRating(Long  ratingId) throws Exception;
    List<UserRating> findUserRatings(Long rated_user_id) throws Exception;
    List<CommentDTO> findComment(Long ratingId) throws Exception;
    boolean adduser(User userDTO)throws Exception;
    User findUser (Long userId) throws Exception;
    int commentCount(Long ratedUserId) throws Exception;
}