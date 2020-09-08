package company.service;

import company.dto.CompanyDto;
import company.dto.StockExchangeDto;
import company.model.StockExchange;

import java.util.Optional;

public interface StockExchangeService {
    public Iterable<StockExchangeDto> findAllStockExchanges();
    public StockExchangeDto saveStockExchange(StockExchangeDto stockExchangeDto);
    public StockExchangeDto deleteStockExchange(String id);
    public boolean existsById(String id);
    public boolean existsByStockExchange(String stockExchange);

    public StockExchangeDto findById(String id);
}
