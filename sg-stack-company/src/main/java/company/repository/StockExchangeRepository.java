package company.repository;

import company.model.StockExchange;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8088"})
public interface StockExchangeRepository extends MongoRepository<StockExchange,String> {
    public boolean existsByStockExchange(String stockExchange);
}
