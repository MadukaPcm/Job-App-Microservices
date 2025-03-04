package tz.maduka.reviewms.review.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tz.maduka.reviewms.review.dto.ReviewMessage;
import tz.maduka.reviewms.review.model.Review;
import tz.maduka.reviewms.review.payload.rest.ReviewDto;

@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ReviewDto reviewDto){
        ReviewMessage reviewMessage = new ReviewMessage();
        reviewMessage.setId(reviewMessage.getId());
        reviewMessage.setTitle(reviewDto.getTitle());
        reviewMessage.setDescription(reviewDto.getDescription());
        reviewMessage.setRating(reviewDto.getRating());
        reviewMessage.setCompanyId(reviewDto.getCompanyId());
        rabbitTemplate.convertAndSend("CompanyRatingQueue",reviewMessage);
    }
}
