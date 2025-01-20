package tz.maduka.jobms.job.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tz.maduka.jobms.job.dto.JobWithCompanyDto;
import tz.maduka.jobms.job.external.Company;
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

    private JobRepository jobRepository;
    private final WebClient webClient;

    @Value("${external.api.company.base-url}")
    private String companyServiceBaseUrl;

    public JobServiceImpl(JobRepository jobRepository, WebClient.Builder webClientBuilder) {
        this.jobRepository = jobRepository;
        this.webClient = webClientBuilder.build();
    }

    //    List<Job> jobs = new ArrayList<>();

    @Override
    public List<JobWithCompanyDto> findAll() {
        WebClient client = webClient.mutate().baseUrl(companyServiceBaseUrl).build();

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDto> JobWithCompanyDTOs = new ArrayList<>();



//        USING REST-TEMPLATE (OLD)

//        RestTemplate restTemplate = new RestTemplate();
//        Company company = restTemplate.getForObject("http://localhost:8082/companies/1", Company.class);
//        System.out.println("Company - name: "+company.getName());
//        System.out.println("Company - id: "+company.getId());

//        USING WEB-CLIENT  (MODERN)
//        Create a WebClient instance with the base URL from properties
//        WebClient client = webClient.mutate().baseUrl(companyServiceBaseUrl).build();
//
//        // Call the external service
//        Mono<Company> companyMono = client
//                .get()
//                .uri("/companies/1") // Specify the endpoint
//                .retrieve()
//                .bodyToMono(Company.class);

        // Process the Mono asynchronously
//        companyMono.subscribe(
//                company -> {
//                    // This block runs when the company data is successfully retrieved
//                    System.out.println("Company - name: " + company.getName());
//                    System.out.println("Company - id: " + company.getId());
//                },
//                error -> {
//                    // This block runs if there's an error fetching the company data
//                    System.err.println("Error fetching company details: " + error.getMessage());
//                }
//        );

        // Block to get the Company object (for synchronous behavior)
//        Company company = companyMono.block();
//
//        // Log the company details
//        if (company != null) {
//            System.out.println("Company -> name: " + company.getName());
//            System.out.println("Company -> id: " + company.getId());
//        } else {
//            System.err.println("Failed to fetch company details.");
//        }

        return jobs.stream()
                .map(job -> convertToDto(job, client))
                .collect(Collectors.toList());
//                .map(this::convertToDto).collect(Collectors.toList());
    }

    private JobWithCompanyDto convertToDto(Job job, WebClient client){

        JobWithCompanyDto jobWithCompanyDto = new JobWithCompanyDto();
        jobWithCompanyDto.setJob(job);

        if(job.getCompanyId() == null){
            jobWithCompanyDto.setCompany(null);
        }else {
            try{
                // Call the external service
                Mono<Company> companyMono = client
                        .get()
                        .uri("/companies/"+job.getCompanyId()) // Specify the endpoint
                        .retrieve()
                        .bodyToMono(Company.class);

                // Block to get the Company object (for synchronous behavior)
                Company company = companyMono.block();
                if(company == null){
                    // LOG
                    System.out.println("No company Found for company-id "+ job.getCompanyId());
                    jobWithCompanyDto.setCompany(null);
                }
                jobWithCompanyDto.setCompany(company);
            }catch (Exception e){
                // LOG ERROR
                System.out.println("Error occurred while fetching company: e -> "+e.getMessage());
                jobWithCompanyDto.setCompany(null);
            }
        }
//        for (Job job : jobs) {
        return jobWithCompanyDto;
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
    public Job getJobById(Long id) {

        return jobRepository.findById(id).orElse(null);
//        for(Job job : jobs){
//            if(job.getId().equals(id)){
//                return job;
//            }
//        }
//        return null;
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
