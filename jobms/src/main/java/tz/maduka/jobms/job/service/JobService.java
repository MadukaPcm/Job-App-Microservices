package tz.maduka.jobms.job.service;

import tz.maduka.jobms.job.model.Job;
import tz.maduka.jobms.job.payload.rest.dto.JobDto;

import java.util.List;

public interface JobService {

    List<Job> findAll();

    void createJob(JobDto job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, JobDto updatedJob);
}
