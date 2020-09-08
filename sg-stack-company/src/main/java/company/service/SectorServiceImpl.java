package company.service;

import company.dto.CompanyDto;
import company.dto.SectorDto;
import company.model.Sector;
import company.repository.SectorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class SectorServiceImpl implements SectorService{

    private SectorRepository sectorRepository;
    private ModelMapper modelMapper;

    public SectorServiceImpl(SectorRepository sectorRepository, ModelMapper modelMapper) {
        this.sectorRepository = sectorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SectorDto> findAll() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<SectorDto>>(){}.getType();
        List<SectorDto> sectorDtoList = modelMapper.map(sectorRepository.findAll(),listType);
        return sectorDtoList;
    }

    @Override
    public Iterable<SectorDto> findById(String id) {
        return null;
    }

    @Override
    public List<SectorDto> findByName(String name) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<SectorDto>>(){}.getType();
        List<SectorDto> sectorDtoList = modelMapper.map(sectorRepository.findBySectorName(name),listType);
        return sectorDtoList;
    }

    @Override
    public SectorDto addSector(SectorDto sectorDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Sector sector =sectorRepository.save(modelMapper.map(sectorDto,Sector.class));
        return modelMapper.map(sector,SectorDto.class);
    }

    @Override
    public SectorDto deleteSectorByName(String name) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Sector sector =  sectorRepository.findBySectorName(name).get(0);
        sectorRepository.deleteBySectorName(name);
        return modelMapper.map(sector,SectorDto.class);

    }

    @Override
    public Iterable<CompanyDto> findCompanyBySector(String sectorName) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        if(sectorRepository.existsBySectorName(sectorName)) {
            List<CompanyDto> companyDtoList = modelMapper.map(sectorRepository.findBySectorName(sectorName).get(0).getCompanyList(), listType);
            return companyDtoList;
        }
        return null;
    }

    @Override
    public boolean ifExistsById(String id){
        return sectorRepository.existsById(id);
    }

    @Override
    public boolean ifExistsByName(String sectorName) {
        return sectorRepository.existsBySectorName(sectorName);
    }
}
