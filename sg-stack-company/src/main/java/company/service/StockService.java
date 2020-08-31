package company.service;

import company.dto.StockDto;
import company.model.Stock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StockService {
    public StockDto addStock(StockDto stockDto);
    public Iterable<StockDto> findAll();
    public List<StockDto> getStockByCompanyCode(String stockCode);
    public List<StockDto> getStockByCompanyCodeOverPeriod(String companyCode, LocalDateTime localDateStart, LocalDateTime localDateEnd, int periodicity);
    public boolean ifExistsById(String id);
}
