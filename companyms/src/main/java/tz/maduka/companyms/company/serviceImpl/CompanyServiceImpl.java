package tz.maduka.companyms.company.serviceImpl;

import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import tz.maduka.companyms.company.clients.ReviewClient;
import tz.maduka.companyms.company.dto.ReviewMessage;
import tz.maduka.companyms.company.model.Company;
import tz.maduka.companyms.company.payload.rest.dto.CompanyDto;
import tz.maduka.companyms.company.repository.CompanyRepository;
import tz.maduka.companyms.company.service.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean createCompany(CompanyDto companyDto) {
        try {
            Company companyCreate = new Company();
            companyCreate.setName(companyDto.getName());
            companyCreate.setDescription(companyDto.getDescription());

            companyRepository.save(companyCreate);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateCompany(CompanyDto companyDto, Long id) {
        try {
            Optional<Company> companyOptional = companyRepository.findById(id);
            if(companyOptional.isPresent()){
                Company companyUpdate = companyOptional.get();
                companyUpdate.setName(companyDto.getName());
                companyUpdate.setDescription(companyDto.getDescription());
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println("Description "+ reviewMessage.getDescription());
        Company company = companyRepository.findById(reviewMessage.getCompanyId()).orElseThrow(()->new NotFoundException("Company Not Found"+reviewMessage.getCompanyId()));

        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            return company;
        }else {
            return null;
        }
    }
}
