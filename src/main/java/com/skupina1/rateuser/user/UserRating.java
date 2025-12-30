package  com.skupina1.rateuser.user;
import com.skupina1.rateuser.repo.UserRatingDTO;
import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity
@Table(name="ratings")
@NamedQueries({
                @NamedQuery(
                        name = "UserRating.findAvgRating",
                        query = "select AVG(ur.rating) from UserRating ur where ur.userId = :id"
                ),
                @NamedQuery(
                        name = "UserRating.findAllComments",
                        query = "select ur.userId , ur.comment from UserRating  ur where ur.ratedUserId = :id"
                ),
                @NamedQuery(
                        name="UserRating.findReviewCount",
                        query = "select COUNT(ur) from UserRating ur where ur.ratedUserId = :id"
                ),
                @NamedQuery(
                        name = "UserRating.findAllRatings" ,
                        query = "select ur from UserRating ur where ur.ratedUserId = :id"
                )
        })
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100 ,  nullable = false , name = "user_id")
    private Long userId;
    @Column(name="rated_user_id" , nullable = false)
    private Long ratedUserId ;
    @Column(name="user_rating", nullable = false)
    private Short rating;
    @Column(name="comment")
    private String comment ;
    public UserRating(){}
    public UserRating(Long rating_id, Long userID, Long rated_userId, Short rating) {
        this.id = rating_id;
        this.userId = userID;
        this.ratedUserId = rated_userId;
        this.rating = rating;
    }
    public UserRating(UserRatingDTO userRatingDTO , Long userId) {
        this.userId = userId;
        this.rating = userRatingDTO.getUserRating();
        this.ratedUserId = userRatingDTO.getRatedUserId();
        this.comment = userRatingDTO.getComment();
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setRating(Short rating) {
        this.rating = rating;
    }
    public short getRating() {
        return this.rating;
    }

    public Long getRatingId() {
        return id;
    }
    public void setRatingId(Long ratingId) {
        this.id = ratingId;
    }
    public void setRatedUserId(Long ratedUserId) {
        this.ratedUserId = ratedUserId;
    }
    public  Long getRatedUserId() {
        return ratedUserId;
    }
}