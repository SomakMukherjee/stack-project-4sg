package company.repository;

import company.dto.StockDto;
import company.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8088"})
public interface StockRepository extends MongoRepository<Stock,String> {
    List<StockDto> findByCompanyCode(String companyCode);

    @Query(value="{companyCode : ?0, dateTime : {$gte: ?1, $lte: ?2}}")
    public List<Stock> findStocksOverPeriod(String companyCode, LocalDateTime localDateStart, LocalDateTime localDateEnd);

    @Query(value="{companyCode : ?0}", sort="{dateTime: -1}")
    public List<Stock> findStocksForCompany(String companyCode);
}
