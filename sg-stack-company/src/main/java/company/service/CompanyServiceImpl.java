package company.service;

import company.dto.CompanyDto;
import company.model.Company;
import company.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CompanyDto getCompanyById(String id) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(companyRepository.findById(id).get(),CompanyDto.class);
    }

    @Override
    public Iterable<CompanyDto> getAllCompanies() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        List<CompanyDto> postDtoList = modelMapper.map(companyRepository.findAll(),listType);
        return postDtoList;
    }

    @Override
    public CompanyDto getCompanyByName(String companyName) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(companyRepository.findByCompanyName(companyName).get(),CompanyDto.class);
    }

    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Company company =companyRepository.save(modelMapper.map(companyDto,Company.class));
        return modelMapper.map(company,CompanyDto.class);
    }

    @Override
    public void deleteCompany(String companyId) {
        Company company = companyRepository.findByCompanyId(companyId).get();
        companyRepository.delete(company);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto, String companyId) {
        Company company = companyRepository.findById(companyId).get();
        company.setCompanyName(companyDto.getCompanyName());
        company.setCeo(companyDto.getCeo());
        company.setBoardOfDirectors(companyDto.getBoardOfDirectors());
        company.setDescription(companyDto.getDescription());
        company.setSector(companyDto.getSector());
        company.setStockExchanges(companyDto.getStockExchanges());
        company.setCodeInStockExchange(companyDto.getCodeInStockExchange());
        companyRepository.save(company);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(company,CompanyDto.class);
    }

    @Override
    public List<CompanyDto> searchByPattern(String pattern) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        List<CompanyDto> postDtoList = modelMapper.map(companyRepository.findByCompanyNameContaining(pattern),listType);
        return postDtoList;
    }

    @Override
    public List<CompanyDto> getCompanyBySector(String sectorName) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        List<CompanyDto> postDtoList = modelMapper.map(companyRepository.findBySector(sectorName),listType);
        return postDtoList;
    }

    @Override
    public boolean ifExistsById(String id){
        return companyRepository.existsById(id);
    }

    @Override
    public boolean ifExistsByName(String companyName) {
        return companyRepository.existsByCompanyName(companyName);
    }

    @Override
    public List<CompanyDto> getCompanyByStockExchange(String stockExchange) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        List<CompanyDto> postDtoList = modelMapper.map(companyRepository.findByStockExchange(stockExchange),listType);
        return postDtoList;
    }
}
