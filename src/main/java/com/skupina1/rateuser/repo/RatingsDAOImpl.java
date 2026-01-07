package com.skupina1.rateuser.repo;

import com.skupina1.rateuser.user.UserRating;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.inject.Inject;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class RatingsDAOImpl implements RatingsDAO {
    @Inject
    private EntityManager em;

    @Override
    public Double getUserVotes(Long user_id) {
        return 0.0;
    }

    @Override
    public Double getAvgUserRating(Long user_id) {
        if (user_id == null) {
            System.out.println("user_id is null");
            return 0.0;
        }
        Double avgRating = em.createNamedQuery("UserRating.findAvgRating",Double.class)
                .setParameter("id", user_id).getSingleResult();
        return avgRating == null ? 0.0 : avgRating;
    }

//    @Override
//    public Double getUserRating(String rated_user_id) {
//        return 0.0;
//    }

    @Override
    public boolean addUserRating(UserRating userRating) throws Exception {
        try {
            em.persist(userRating);
            return true;
        }catch (Exception e){
            System.out.print(e.getMessage());
            throw new Exception(e);
        }
    }

    @Override
    public boolean changeUserRating(UserRating newUserRating) throws Exception {
        try {
            UserRating userRating = em.find(UserRating.class,newUserRating.getUserId());
            userRating.setRating(newUserRating.getRating());
            em.persist(userRating);
        }catch (Exception e){
            throw new Exception(e);
        }
        return true;
    }

    @Override
    public boolean removeUserRating(Long ratingId) throws Exception{
        if (ratingId == null ){
            throw  new Exception("id is null");
        }
        try{
            UserRating userRating = em.find(UserRating.class,ratingId);
            em.remove(userRating);
            em.persist(userRating);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserRating> findUserRatings(Long ratedUserId) throws Exception {
       if (ratedUserId == null) {
           throw  new Exception("id is null");
       }
       return this.em.createNamedQuery("UserRating.findAllRatings",UserRating.class)
               .setParameter("id", ratedUserId)
               .getResultList();
    }

    @Override
    public List<CommentDTO> findComment(Long rated_id) throws Exception {
        if  (rated_id == null) {
            throw  new Exception("rated_id is null");
        }
        try {
            return em.createNamedQuery("UserRating.findAllComments", CommentDTO.class)
                    .setParameter("id", rated_id).getResultList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public boolean adduser(User user) throws Exception {
        try{
            em.persist(user);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User findUser(Long userId) throws Exception {
        return em.createNamedQuery("User.findById",User.class).getSingleResult();
    }
    @Override
    public int commentCount(Long ratedUserId){
        if (ratedUserId == null) {
            return -1 ;
        }
        return em.createNamedQuery("UserRating.findReviewCount",Integer.class)
                .setParameter("id", ratedUserId)
                .getSingleResult();

    }
    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
}