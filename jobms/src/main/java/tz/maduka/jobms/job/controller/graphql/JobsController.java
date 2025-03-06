package tz.maduka.jobms.job.controller.graphql;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

@Service
@GraphQLApi
public class JobsController {

    @GraphQLQuery(name = "getJobHome", description = "Get Job Home")
    public String findByDepartmentName() {
        return "Welcome to graphql job service";
    }
}
