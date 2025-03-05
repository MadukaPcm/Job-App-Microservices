package tz.maduka.companyms.company.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import tz.maduka.companyms.company.dto.ReviewMessage;
import tz.maduka.companyms.company.model.Company;
import tz.maduka.companyms.company.repository.CompanyRepository;
import tz.maduka.companyms.company.service.CompanyService;

import java.util.Optional;


@Slf4j
@Service
public class ReviewMessageConsumer {

    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    public ReviewMessageConsumer(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @RabbitListener(queues = "CompanyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage) {
        log.info("Received message: {}", reviewMessage);

        // Validate message
        if (reviewMessage == null || reviewMessage.getCompanyId() == null) {
            log.error("Received a null or invalid review message: {}", reviewMessage);
            return;
        }

        // Fetch the company safely
        Optional<Company> companyOpt = companyRepository.findById(reviewMessage.getCompanyId());
        if (companyOpt.isEmpty()) {
            log.warn("No company found for ID: {}", reviewMessage.getCompanyId());
            return;
        }

        // Process the message
        try {
            companyService.updateCompanyRating(reviewMessage);
            log.info("Successfully updated company rating.");
        } catch (Exception e) {
            log.error("Error updating company rating: {}", e.getMessage(), e);
        }
    }
}
