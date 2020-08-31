package company.repository;

import company.model.Ipo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8088"})
public interface IpoRepository extends MongoRepository<Ipo,String> {
    public Iterable<Ipo> findByOpeningDateBetween(String startTime, String endTime);
    public Iterable<Ipo> findByCompanyName(String companyName);
    public Iterable<Ipo> findByCompanyNameAndOpeningDateBetween(String companyName, String startTime, String endTime);
    public boolean existsByCompanyName(String name);
}
