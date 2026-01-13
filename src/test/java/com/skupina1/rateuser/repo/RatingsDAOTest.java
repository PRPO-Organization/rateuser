package com.skupina1.rateuser.repo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.skupina1.rateuser.user.UserRating;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
@Testable
class RatingsDAOImplTest {

    @InjectMocks
    private RatingsDAOImpl ratingsDAO;

    @Mock
    private EntityManager em;

    @Mock
    private TypedQuery<UserRating> userRatingQuery;

    @Mock
    private TypedQuery<Double> doubleQuery;

    @Mock
    private TypedQuery<Integer> intQuery;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvgUserRating_WithNullId() {
        Double result = ratingsDAO.getAvgUserRating(null);
        assertEquals(0.0, result);
    }

    @Test
    void testGetAvgUserRating_ReturnsValue() {
        Long userId = 1L;
        when(em.createNamedQuery("UserRating.findAvgRating", Double.class)).thenReturn(doubleQuery);
        when(doubleQuery.setParameter("id", userId)).thenReturn(doubleQuery);
        when(doubleQuery.getSingleResult()).thenReturn(4.5);

        Double result = ratingsDAO.getAvgUserRating(userId);
        assertEquals(4.5, result);
    }

    @Test
    void testAddUserRating_Success() throws Exception {
        UserRating rating = new UserRating();
        rating.setRating((short) 5);
        assertTrue(ratingsDAO.addUserRating(rating));
        verify(em, times(1)).persist(rating);
    }

    @Test
    void testChangeUserRating_Success() throws Exception {
        UserRating newRating = new UserRating();
        newRating.setUserId(1L);
        newRating.setRating((short) 4);

        UserRating existingRating = new UserRating();
        existingRating.setRating((short) 2);

        when(em.find(UserRating.class, 1L)).thenReturn(existingRating);

        boolean result = ratingsDAO.changeUserRating(newRating);
        assertTrue(result);
        assertEquals(4, existingRating.getRating());
        verify(em, times(1)).persist(existingRating);
    }

    @Test
    void testRemoveUserRating_Success() throws Exception {
        Long ratingId = 1L;
        UserRating rating = new UserRating();
        when(em.find(UserRating.class, ratingId)).thenReturn(rating);

        boolean result = ratingsDAO.removeUserRating(ratingId);
        assertTrue(result);
        verify(em).remove(rating);
    }

    @Test
    void testFindUserRatings_Success() throws Exception {
        Long ratedId = 1L;
        List<UserRating> ratings = Arrays.asList(new UserRating(), new UserRating());

        when(em.createNamedQuery("UserRating.findAllRatings", UserRating.class)).thenReturn(userRatingQuery);
        when(userRatingQuery.setParameter("id", ratedId)).thenReturn(userRatingQuery);
        when(userRatingQuery.getResultList()).thenReturn(ratings);

        List<UserRating> result = ratingsDAO.findUserRatings(ratedId);
        assertEquals(2, result.size());
    }

    @Test
    void testCommentCount_ReturnsValue() {
        Long ratedId = 1L;
        when(em.createNamedQuery("UserRating.findReviewCount", Integer.class)).thenReturn(intQuery);
        when(intQuery.setParameter("id", ratedId)).thenReturn(intQuery);
        when(intQuery.getSingleResult()).thenReturn(5);

        int count = ratingsDAO.commentCount(ratedId);
        assertEquals(5, count);
    }

    @Test
    void testCommentCount_NullId() {
        assertEquals(-1, ratingsDAO.commentCount(null));
    }
}
