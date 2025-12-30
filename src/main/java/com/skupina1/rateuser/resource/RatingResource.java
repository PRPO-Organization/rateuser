package com.skupina1.rateuser.resource;

import com.skupina1.rateuser.repo.*;
import com.skupina1.rateuser.user.UserRating;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@ApplicationScoped
@Path("/ratings")
public  class RatingResource {
    @Inject
    RatingsDAOImpl ratingsDAO;
    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @Transactional
    @Path("{id}")
    public Response getRating(@PathParam("id") Long id) {
        try {
            if (id == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("id is required")
                        .build();
            }
            Double avgRating = ratingsDAO.getUserRating(id);
            User user = ratingsDAO.findUser(id);
            if (avgRating == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("rating not found")
                        .build();
            }
            UserRatingDTO userRatingDTO = new UserRatingDTO();
            return Response.ok(userRatingDTO)
                    .entity("average rating received")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage())
                    .build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Transactional
    public Response uploadRating(@PathParam("id") Long id ,  UserRatingDTO rating) {
        try {
            if (rating == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("ratingDTO is required")
                        .build();
            }
            if (id  == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("id is required")
                        .build();
            }
            UserRating userRating = new UserRating(rating , id);
            boolean ok = ratingsDAO.addUserRating(userRating) ;
            if (!ok){
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("rating not uploaded")
                        .build();
            }
            EntityManager em = ratingsDAO.getEm();
            em.persist(userRating);
            return Response.status(Response.Status.CREATED)
                    .entity("rating uploaded")
                    .build();
        } catch (Exception e) {
           return Response.status(Response.Status.BAD_REQUEST).
                    entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}/comments")
    public Response getComments(
            @PathParam("id") Long id
    ) {
        try {
            if (id == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("id is required")
                        .build();
            }
            List<CommentDTO> comments = ratingsDAO.findComment(id);
            if (comments == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("comment not found")
                        .build();
            }
            return Response.ok(comments).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("id is required")
                    .build();
        }
    }

//    @GET
//    @Path ("users/{id}")
//    public Response getUsers(){
//
//    }
//
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("users")
    @Transactional
    public Response uploadUserRating(UserDTO userDetails) {
        try {
            if  (userDetails == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("userDTO is required")
                        .build();
            }

            EntityManager em = ratingsDAO.getEm();
//            ratingsDAO.adduser(user);
            User user = new User(userDetails);
            em.persist(user);
            return Response.created(uriInfo.getAbsolutePath()).build();
        } catch (Exception e) {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/users/{id}")
  public Response getRatings(@PathParam("id")Long id ) {
         try {
            if (id == null){
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("id is required")
                        .build();
            }
            EntityManager em = ratingsDAO.getEm();
            List<UserRating> userRatings = ratingsDAO.findUserRatings(id);
            if (userRatings == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("user not found")
                        .build();
            }
            return Response.ok(userRatings).build();
         } catch (Exception e) {
             System.err.println(e.getMessage());
            return Response.serverError().build();
         }
  }
  //returns the number of reviews the user has
  @GET
  @Produces
  @Path("/users")
  public Response getReviewsCount(
          @QueryParam("ratedUserId") Long ratedUserId
  ) {
        if (ratedUserId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ratedUserId is required")
                    .build();
        }
        EntityManager em =  ratingsDAO.getEm();
        Integer count = ratingsDAO.commentCount(ratedUserId);
        return Response.ok(count).build();

    }
}
