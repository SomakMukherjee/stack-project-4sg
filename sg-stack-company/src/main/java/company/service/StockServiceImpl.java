package company.service;

import company.dto.StockDto;
import company.model.Stock;
import company.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    private StockRepository stockRepository;
    private ModelMapper modelMapper;

    public StockServiceImpl(StockRepository stockRepository, ModelMapper modelMapper) {
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StockDto addStock(StockDto stockDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Stock stock =stockRepository.save(modelMapper.map(stockDto,Stock.class));
        return modelMapper.map(stock, StockDto.class);
    }

    @Override
    public Iterable<StockDto> findAll() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<StockDto>>(){}.getType();
        List<StockDto> stockDtoList = modelMapper.map(stockRepository.findAll(),listType);
        return stockDtoList;
    }

    @Override
    public List<StockDto> getStockByCompanyCode(String companyCode){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<StockDto>>(){}.getType();
        List<StockDto> stockDtoList = modelMapper.map(stockRepository.findStocksForCompany(companyCode),listType);
        return stockDtoList;
    }

    @Override
    public List<StockDto> getStockByCompanyCodeOverPeriod(String companyCode, LocalDateTime localDateStart, LocalDateTime localDateEnd, int periodicity) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<StockDto>>(){}.getType();
        System.out.println(companyCode+" "+localDateStart+" "+localDateEnd);
        List<StockDto> stockDtoList = modelMapper.map(stockRepository.findStocksOverPeriod(companyCode,localDateStart,localDateEnd),listType);
        return stockDtoList;
    }

    @Override
    public boolean ifExistsById(String id){
        return stockRepository.existsById(id);
    }
}
