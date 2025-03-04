package tz.maduka.companyms.company.service;
import tz.maduka.companyms.company.dto.ReviewMessage;
import tz.maduka.companyms.company.model.Company;
import tz.maduka.companyms.company.payload.rest.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    boolean createCompany(CompanyDto companyDto);
    boolean updateCompany(CompanyDto companyDto, Long id);
    void updateCompanyRating(ReviewMessage reviewMessage);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
}
