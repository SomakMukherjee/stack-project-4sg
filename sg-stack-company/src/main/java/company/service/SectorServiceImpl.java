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
    public Iterable<SectorDto> findAll() {
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
    public Iterable<SectorDto> findByName(String name) {
        return null;
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
        Sector sector =  sectorRepository.deleteBySectorName(name).get();
        return modelMapper.map(sector,SectorDto.class);
    }

    @Override
    public Iterable<CompanyDto> findCompanyBySector(String sectorName) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<CompanyDto>>(){}.getType();
        if(sectorRepository.existsBySectorName(sectorName)) {
            List<CompanyDto> companyDtoList = modelMapper.map(sectorRepository.findBySectorName(sectorName).get().getCompanyList(), listType);
            return companyDtoList;
        }
        return null;
    }

    @Override
    public boolean ifExistsById(String id){
        return sectorRepository.existsById(id);
    }
}
