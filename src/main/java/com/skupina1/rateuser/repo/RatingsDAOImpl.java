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
    public Double getUserVotes(String user_id) {
        return 0.0;
    }

    @Override
    public Double getUserRating(String user_id) {
        TypedQuery<Double> query  = em.createNamedQuery("UserRating.findAvgRating",Double.class)
                .setParameter("id", user_id);
        return query.getSingleResult();
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
    public boolean removeUserRating(Short id) throws Exception{
        if (id == null ){
            throw  new Exception("id is null");
        }
        try{
            UserRating userRating = em.find(UserRating.class,id);
            em.remove(userRating);
            em.persist(userRating);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserRating findUserRating(String rated_user_id) throws Exception {
        return null;
    }

    @Override
    public List<CommentDTO> findComment(String rated_id) throws Exception {
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
    public User findUser(String usedId) throws Exception {
        return em.createNamedQuery("User.findById",User.class).getSingleResult();
    }

    public EntityManager getEm() {
        return em;
    }
    public void setEm(EntityManager em) {
        this.em = em;
    }
}