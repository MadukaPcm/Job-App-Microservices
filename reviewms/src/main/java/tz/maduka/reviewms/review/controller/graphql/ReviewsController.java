package tz.maduka.reviewms.review.controller.graphql;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

@Service
@GraphQLApi
public class ReviewsController {

    @GraphQLQuery(name = "getReviewHome", description = "Get Review Home")
    public String findByDepartmentName() {
        return "Welcome to graphql review service";
    }
}
