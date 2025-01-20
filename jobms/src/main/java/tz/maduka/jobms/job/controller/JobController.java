package tz.maduka.jobms.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.maduka.jobms.job.dto.JobWithCompanyDto;
import tz.maduka.jobms.job.model.Job;
import tz.maduka.jobms.job.payload.rest.dto.JobDto;
import tz.maduka.jobms.job.service.JobService;
import java.util.List;

@RestController
@RequestMapping(path = "/jobs")
public class JobController {

//    @Autowired=
    private JobService jobService;
    private Long nextId = 1L;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public ResponseEntity<List<JobWithCompanyDto>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createJob(@RequestBody JobDto job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully !!",HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.getJobById(id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if (deleted){
            return new ResponseEntity<>("Job deleted successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data Not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody JobDto updatedJob){
        boolean updated = jobService.updateJob(id,updatedJob);
        if(updated){
            return new ResponseEntity<>("Job updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update Job", HttpStatus.BAD_REQUEST);
    }
}
