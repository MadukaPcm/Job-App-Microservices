package tz.maduka.companyms.company.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tz.maduka.companyms.company.model.Company;
import tz.maduka.companyms.company.payload.rest.dto.CompanyDto;
import tz.maduka.companyms.company.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    List<Company> getAllCompany(){
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    ResponseEntity<String> createCompany(@RequestBody CompanyDto companyDto){
        try {
            boolean companyCreated =  companyService.createCompany(companyDto);
            if (companyCreated){
                return new  ResponseEntity<>("Company created successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Failed to create company", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Failed to create company", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        try {
            boolean companyUpdated = companyService.updateCompany(companyDto, id);
            if (companyUpdated){
                return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Failed to update company", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Failed to update company", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean isDeleted = companyService.deleteCompany(id);
        if (isDeleted){
            return new ResponseEntity<>("Company is deleted", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
        }
    }

}
