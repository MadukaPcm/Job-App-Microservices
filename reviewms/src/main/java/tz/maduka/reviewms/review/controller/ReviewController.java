package tz.maduka.reviewms.review.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.maduka.reviewms.review.messaging.ReviewMessageProducer;
import tz.maduka.reviewms.review.model.Review;
import tz.maduka.reviewms.review.payload.rest.ReviewDto;
import tz.maduka.reviewms.review.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
   final private ReviewService reviewService;
   final private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService, ReviewMessageProducer reviewMessageProducer) {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllREviews(@RequestParam Long companyId){ //PathVariable change  to RequestParam in MS
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody ReviewDto reviewDto){
      boolean success = reviewService.addReview(companyId,reviewDto);

      if (success){
          reviewDto.setCompanyId(companyId);
          reviewMessageProducer.sendMessage(reviewDto);
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

    @GetMapping("/averageMapping")
    public double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
