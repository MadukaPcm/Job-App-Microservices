package tz.maduka.companyms.company.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("REVIEW-SERVICE")
public interface ReviewClient {

    @GetMapping("/review-service/reviews/averageMapping")
    Double getAverageRatingForCompany(@RequestParam("companyId") Long companyId);
}
