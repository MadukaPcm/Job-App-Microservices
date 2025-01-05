package tz.maduka.companyms.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tz.maduka.companyms.company.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
