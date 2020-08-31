package excel.repository;

import excel.model.StockPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface StockPriceRepository extends MongoRepository<StockPrice,String> {
    //add entry from excel
    //retrieve data for a company in a given time period
    //retrieve data for a sector in a given time period
}
