package company.repository;

import company.dto.SectorDto;
import company.model.Sector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8088"})
public interface SectorRepository extends MongoRepository<Sector,String> {
    public List<Sector> findBySectorName(String sectorName);
    public boolean existsBySectorName(String name);
    public void deleteBySectorName(String name);
}
