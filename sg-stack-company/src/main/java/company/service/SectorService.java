package company.service;

import company.dto.CompanyDto;
import company.dto.SectorDto;

public interface SectorService {
    public Iterable<SectorDto> findAll();
    public Iterable<SectorDto> findById(String id);
    public Iterable<SectorDto> findByName(String name);
    public SectorDto addSector(SectorDto sectorDto);
    public SectorDto deleteSectorByName(String name);
    public Iterable<CompanyDto> findCompanyBySector(String sectorName);
    public boolean ifExistsById(String id);
}
