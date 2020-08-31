package company.repository;

import company.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8088"})
public interface CompanyRepository extends MongoRepository<Company,String> {
    public Optional<Company> findByCompanyId(String companyId);
    public List<Company> findByCompanyNameContaining(String pattern);
    public Optional<Company> findByCompanyName(String companyName);
    public List<Company> findBySector(String sector);
    public boolean existsByCompanyName(String companyName);

    @Query(value="{ stockExchanges : { $elemMatch : {$eq: ?0}}}")
    public List<Company> findByStockExchange(String stockExchange);
}
