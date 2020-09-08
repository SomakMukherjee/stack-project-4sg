package company.service;

import company.dto.StockExchangeDto;
import company.model.StockExchange;
import company.repository.StockExchangeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class StockExchangeServiceImpl implements StockExchangeService {

    private StockExchangeRepository stockExchangeRepository;
    private ModelMapper modelMapper;

    public StockExchangeServiceImpl(StockExchangeRepository stockExchangeRepository, ModelMapper modelMapper) {
        this.stockExchangeRepository = stockExchangeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<StockExchangeDto> findAllStockExchanges() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Type listType = new TypeToken<List<StockExchangeDto>>(){}.getType();
        List<StockExchangeDto> stockExchangeDtoList = modelMapper.map(stockExchangeRepository.findAll(),listType);
        return stockExchangeDtoList;
    }

    @Override
    public StockExchangeDto saveStockExchange(StockExchangeDto stockExchangeDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StockExchange result =  stockExchangeRepository.save(modelMapper.map(stockExchangeDto,StockExchange.class));
        return modelMapper.map(result,StockExchangeDto.class);
    }

    @Override
    public StockExchangeDto deleteStockExchange(String id) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StockExchangeDto result =  modelMapper.map(stockExchangeRepository.findById(id).get(),StockExchangeDto.class);
        stockExchangeRepository.deleteById(id);
        return result;
    }

    @Override
    public boolean existsById(String id) {
        return stockExchangeRepository.existsById(id);
    }

    @Override
    public boolean existsByStockExchange(String stockExchange) {
        return stockExchangeRepository.existsByStockExchange(stockExchange);
    }

    @Override
    public StockExchangeDto findById(String id) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        StockExchangeDto result =  modelMapper.map(stockExchangeRepository.findById(id).get(),StockExchangeDto.class);
        return result;
    }
}
