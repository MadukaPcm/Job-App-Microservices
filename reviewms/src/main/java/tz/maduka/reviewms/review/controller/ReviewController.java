package tz.maduka.reviewms.review.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.maduka.reviewms.review.model.Review;
import tz.maduka.reviewms.review.payload.rest.ReviewDto;
import tz.maduka.reviewms.review.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllREviews(@RequestParam Long companyId){ //PathVariable change  to RequestParam in MS
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody ReviewDto reviewDto){
      boolean success = reviewService.addReview(reviewDto);
      if (success){
          return new ResponseEntity<>("Review added successfully !!", HttpStatus.OK);
      }else {
          return new ResponseEntity<>("Failed to create review !!",HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto){
        boolean isReviewUpdated = reviewService.updateReview(reviewId, reviewDto);
        if(isReviewUpdated){
            return new ResponseEntity<>("Review updated successfully !!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review update failed !!", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewService.deleteReview(reviewId);
        if(isReviewDeleted){
            return new ResponseEntity<>("Review deleted successfully !!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review delete failed !!", HttpStatus.NOT_FOUND);
    }
}
