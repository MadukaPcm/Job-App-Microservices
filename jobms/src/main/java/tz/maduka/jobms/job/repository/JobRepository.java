package tz.maduka.jobms.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.maduka.jobms.job.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
//    Optional<Job> findByCompanyId(Long companyId);
//    List<Job> findByCompanyId(Long companyId);

}
