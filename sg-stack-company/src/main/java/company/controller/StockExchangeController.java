package company.controller;

import company.dto.StockExchangeDto;
import company.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = {"http://localhost:8088","http://localhost:4200"})
@RestController
public class StockExchangeController {

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }
    @Autowired
    private StockExchangeService stockExchangeService;

    @GetMapping("/stockExchanges/{id}")
    public ResponseEntity<StockExchangeDto> getStockExchangeById(@PathVariable("id") String id){
        return ResponseEntity.ok().body(stockExchangeService.findById(id));
    }

    //find all
    @GetMapping("/stockExchanges")
    public ResponseEntity<Iterable<StockExchangeDto>> getAllStockExchanges(){
        return ResponseEntity.ok().body(stockExchangeService.findAllStockExchanges());
    }

    //add stock exchange
    @PostMapping("/stockExchanges/add")
    @Transactional
    public ResponseEntity<StockExchangeDto> addStockExchange(@RequestBody StockExchangeDto stockExchangeDto){
        if(!stockExchangeService.existsByStockExchange(stockExchangeDto.getStockExchange())) {
            return ResponseEntity.ok().body(stockExchangeService.saveStockExchange(stockExchangeDto));
        }
        return ResponseEntity.badRequest().body(new StockExchangeDto("","Stock Exchange with similar name already exists","",""));
    }
    //delete SE
    @DeleteMapping("/stockExchanges/delete/{id}")
    @Transactional
    public ResponseEntity<StockExchangeDto> deleteStockExchange(@PathVariable("id") String id){
        if(stockExchangeService.existsById(id)) {
            return ResponseEntity.ok().body(stockExchangeService.deleteStockExchange(id));
        }
        return ResponseEntity.notFound().build();
    }

    //edit SE
    @PostMapping("/stockExchanges/update")
    @Transactional
    public ResponseEntity<StockExchangeDto> editStockExchange(@RequestBody StockExchangeDto stockExchangeDto){
        if(stockExchangeService.existsById(stockExchangeDto.getId())) {
            return ResponseEntity.ok().body(stockExchangeService.saveStockExchange(stockExchangeDto));
        }
        return ResponseEntity.notFound().build();
    }

}
