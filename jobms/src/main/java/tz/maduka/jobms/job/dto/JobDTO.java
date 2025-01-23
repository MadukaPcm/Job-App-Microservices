package tz.maduka.jobms.job.dto;

import lombok.Getter;
import lombok.Setter;
import tz.maduka.jobms.job.external.Company;
import tz.maduka.jobms.job.external.Review;

import java.util.List;

@Getter
@Setter
public class JobDTO {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Review> review;
}
