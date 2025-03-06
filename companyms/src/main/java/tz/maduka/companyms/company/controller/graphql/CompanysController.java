package tz.maduka.companyms.company.controller.graphql;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;

@Service
@GraphQLApi
public class CompanysController {

    @GraphQLQuery(name = "getCompanyHome", description = "Get Company Home")
    public String findByDepartmentName() {
        return "Welcome to graphql company service";
    }
}
