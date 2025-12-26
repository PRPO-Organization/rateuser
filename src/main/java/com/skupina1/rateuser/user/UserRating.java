package  com.skupina1.rateuser.user;
import com.skupina1.rateuser.repo.UserRatingDTO;
import jakarta.persistence.*;

@Entity
@Table(name="ratings")
@NamedQueries({
                @NamedQuery(
                        name = "UserRating.findAvgRating",
                        query = "select AVG(ur.rating) from UserRating ur where ur.userId = :id"
                ),
                @NamedQuery(
                        name = "UserRating.findAllComments",
                        query = "select ur.userId , ur.comment from UserRating  ur where ur.rated_userId = :id"
                )

        })
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rating_id;
    @Column(length = 100 ,  nullable = false , name = "user_id")
    private String userId;
    @Column(name="rated_user_id")
    private Long rated_userId ;
    private Short rating;
    @Column(name="comment")
    private String comment ;
    public UserRating(){}
    public UserRating(Long rating_id, String userID, Long rated_userId, Short rating) {
        this.rating_id = rating_id;
        this.userId = userID;
        this.rated_userId = rated_userId;
        this.rating = rating;
    }
    public UserRating(UserRatingDTO userRatingDTO , Long ratedUserId) {
        this.userId = userRatingDTO.getUserId();
        this.rating = userRatingDTO.getUserRating();
        this.rated_userId = ratedUserId;
        this.comment = userRatingDTO.getComment();
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
    public void setRating(Short rating) {
        this.rating = rating;
    }
    public short getRating() {
        return this.rating;
    }

    public Long getRatingId() {
        return rating_id;
    }
    public void setRatingId(Long ratingId) {
        this.rating_id = ratingId;
    }
    public void setRatedUserId(Long ratedUserId) {
        this.rated_userId = ratedUserId;
    }
    public  Long getRatedUserId() {
        return rated_userId;
    }
}