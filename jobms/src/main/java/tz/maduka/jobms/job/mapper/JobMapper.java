package tz.maduka.jobms.job.mapper;


import tz.maduka.jobms.job.dto.JobDTO;
import tz.maduka.jobms.job.model.Job;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDTO (Job job){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(null);
        jobDTO.setReview(null);

        return jobDTO;
    }

}
