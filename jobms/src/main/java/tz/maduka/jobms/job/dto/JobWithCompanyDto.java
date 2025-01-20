package tz.maduka.jobms.job.dto;

import tz.maduka.jobms.job.external.Company;
import tz.maduka.jobms.job.model.Job;

public class JobWithCompanyDto {

    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
