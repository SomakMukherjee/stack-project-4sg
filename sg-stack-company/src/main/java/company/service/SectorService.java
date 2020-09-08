package company.service;

import company.dto.CompanyDto;
import company.dto.SectorDto;

import java.util.List;

public interface SectorService {
    public List<SectorDto> findAll();
    public Iterable<SectorDto> findById(String id);
    public List<SectorDto> findByName(String name);
    public SectorDto addSector(SectorDto sectorDto);
    public SectorDto deleteSectorByName(String name);
    public Iterable<CompanyDto> findCompanyBySector(String sectorName);
    public boolean ifExistsById(String id);

    public boolean ifExistsByName(String sectorName);
}
