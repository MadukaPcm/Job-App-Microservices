package tz.maduka.reviewms.review.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tz.maduka.reviewms.review.model.Review;
import tz.maduka.reviewms.review.payload.rest.ReviewDto;

import java.util.List;

public interface ReviewService {

   List<Review> getAllReviews(Long companyId);
   boolean addReview(ReviewDto reviewDto);
   Review getReview(Long reviewId);
   boolean updateReview(Long reviewId,ReviewDto reviewDto );
   boolean deleteReview(Long reviewId);
}
