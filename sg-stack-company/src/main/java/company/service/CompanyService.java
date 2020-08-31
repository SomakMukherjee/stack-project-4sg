package company.service;

import company.dto.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    public Iterable<CompanyDto> getAllCompanies();
    public CompanyDto getCompanyByName(String name);
    public CompanyDto addCompany(CompanyDto companyDto);
    public void deleteCompany(String Id);
    public CompanyDto updateCompany(CompanyDto companyDto, String companyId);
    public List<CompanyDto> searchByPattern(String pattern);
    public List<CompanyDto> getCompanyBySector(String sectorName);
    public boolean ifExistsById(String id);

    public boolean ifExistsByName(String companyName);

    public List<CompanyDto> getCompanyByStockExchange(String stockExchange);
}
