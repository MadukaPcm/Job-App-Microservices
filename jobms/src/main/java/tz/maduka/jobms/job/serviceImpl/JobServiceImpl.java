package tz.maduka.jobms.job.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tz.maduka.jobms.job.client.CompanyClient;
import tz.maduka.jobms.job.client.ReviewClient;
import tz.maduka.jobms.job.dto.JobDTO;
import tz.maduka.jobms.job.external.Company;
import tz.maduka.jobms.job.external.Review;
import tz.maduka.jobms.job.mapper.JobMapper;
import tz.maduka.jobms.job.model.Job;
import tz.maduka.jobms.job.payload.rest.dto.JobDto;
import tz.maduka.jobms.job.repository.JobRepository;
import tz.maduka.jobms.job.service.JobService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    final private CompanyClient companyClient;
    final private ReviewClient reviewClient;

    @Value("${external.api.company.base-url}")
    private String companyServiceBaseUrl;

    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    //    List<Job> jobs = new ArrayList<>();

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobDTO convertToDto(Job job) {
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job);

        // Check if companyId is null
        if (job.getCompanyId() == null) {
            jobDTO.setCompany(null);
        } else {
            try {
                // Fetch the company details if companyId is not null
                // Rest-template............
                // Company company = restTemplate.getForObject(
                //        "http://COMPANY-SERVICE/companies/" + job.getCompanyId(),
                //        Company.class
                //);

                // OpenFeign package...........
                Company company = companyClient.getCompany(job.getCompanyId());

                if (company != null){
                    // Rest-template............
                    //ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange(
                    //        "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
                    //        HttpMethod.GET,
                    //       null,
                    //        new ParameterizedTypeReference<List<Review>>() {
                    //        }
                    // );
                    // List<Review> reviews = reviewResponse.getBody();

                    // OpenFeign package...........
                    List<Review> reviews = reviewClient.getReview(job.getCompanyId());
                    System.out.println("===============    reviews  ========================= " +reviews);
                    jobDTO.setCompany(company);
                    jobDTO.setReview(reviews);
                }else {
                    jobDTO.setCompany(null);
                    jobDTO.setReview(null);
                }
            } catch (Exception e) {
                // Handle any exception during the API call and set company to null
                jobDTO.setCompany(null);
                // Optionally log the error for debugging
                System.err.println("Error fetching company details: " + e.getMessage());
            }
        }
        return jobDTO;
    }



    @Override
    public void createJob(JobDto jobDto) {
        Job newJob = new Job();
        newJob.setTitle(jobDto.getTitle());
        newJob.setDescription(jobDto.getDescription());
        newJob.setMinSalary(jobDto.getMinSalary());
        newJob.setMaxSalary(jobDto.getMaxSalary());
        newJob.setLocation(jobDto.getLocation());
        newJob.setCompanyId(jobDto.getCompanyId());
        jobRepository.save(newJob);
    }

    @Override
    public JobDTO getJobById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);
        if(job == null){
            return null;
        }else {
            JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job);
            // Check if companyId is null
            if (job.getCompanyId() == null) {
                jobDTO.setCompany(null);
            } else {
                try {
                    // Fetch the company details if companyId is not null
                    Company company = restTemplate.getForObject(
                            "http://COMPANY-SERVICE/companies/" + job.getCompanyId(),
                            Company.class
                    );
                    jobDTO.setCompany(company);
                } catch (Exception e) {
                    // Handle any exception during the API call and set company to null
                    jobDTO.setCompany(null);
                    // Optionally log the error for debugging
                    System.err.println("Error fetching company details: " + e.getMessage());
                }
            }

            return jobDTO;
        }

    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if(job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public boolean updateJob(Long id, JobDto updatedJob) {

        try{
            Optional<Job> jobOptional = jobRepository.findById(id);
            if(jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
                }
        }catch (Exception e){
            return false;
            }
        return false;
//        for(Job job : jobs){
//            if(job.getId().equals(id)){
//                job.setTitle(updatedJob.getTitle());
//                job.setDescription(updatedJob.getDescription());
//                job.setMinSalary(updatedJob.getMinSalary());
//                job.setMaxSalary(updatedJob.getMaxSalary());
//                job.setLocation(updatedJob.getLocation());
//                return true;
//            }
//            return false;
//        }
//        return false;
    }
}
