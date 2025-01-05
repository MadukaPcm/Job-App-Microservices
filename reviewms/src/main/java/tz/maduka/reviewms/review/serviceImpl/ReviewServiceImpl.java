package tz.maduka.reviewms.review.serviceImpl;

import org.springframework.stereotype.Service;
import tz.maduka.reviewms.review.model.Review;
import tz.maduka.reviewms.review.payload.rest.ReviewDto;
import tz.maduka.reviewms.review.repository.ReviewRepository;
import tz.maduka.reviewms.review.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(ReviewDto reviewDto) {
        if (reviewDto != null){
            Review review = new Review();
            System.out.println(reviewDto.getTitle() +"  ====> "+reviewDto.getDescription()+"=============>>");
            review.setTitle(reviewDto.getTitle());
            review.setDescription(reviewDto.getDescription());
            review.setCompanyId(reviewDto.getCompanyId());
            review.setRating(reviewDto.getRating());
            System.out.println("Saving review: " + review); // Debug log
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        return review;
    }

    @Override
    public boolean updateReview(Long reviewId, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            System.out.println("reviewId: " + reviewId);
            System.out.println("ReviewDto: " + reviewDto);

            review.setId(reviewId);
            review.setTitle(reviewDto.getTitle());
            review.setDescription(reviewDto.getDescription());
            review.setRating(reviewDto.getRating());
            review.setCompanyId(reviewDto.getCompanyId());
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }else{
            return false;
        }
    }

}
