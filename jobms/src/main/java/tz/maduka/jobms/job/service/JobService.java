package tz.maduka.jobms.job.service;

import tz.maduka.jobms.job.dto.JobDTO;
import tz.maduka.jobms.job.payload.rest.dto.JobDto;

import java.util.List;

public interface JobService {

    List<JobDTO> findAll();

    void createJob(JobDto job);

    JobDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, JobDto updatedJob);
}
