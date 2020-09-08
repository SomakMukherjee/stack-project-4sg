package company.controller;

import company.dto.StockDto;
import company.model.Stock;
import company.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8088","http://localhost:4200"})
@RestController
public class StockController {

    public StockController() {
    }

    @Autowired
    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public ResponseEntity<Iterable<StockDto>> findAll(){
        return ResponseEntity.ok().body(stockService.findAll());
    }

    @PostMapping("/stocks/add")
    @Transactional
    public ResponseEntity<StockDto> addStock(@RequestBody StockDto stockDto){
        return ResponseEntity.ok().body(stockService.addStock(stockDto));
    }

    @GetMapping("/stocks/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("Stock Controller works");
    }
}
