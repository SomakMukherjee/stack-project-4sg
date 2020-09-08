package excel.controller;
import excel.model.StockPrice;
import excel.repository.StockPriceRepository;
import excel.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8088"})
@RestController
public class StockPriceController {
    @Autowired
    private StockPriceService stockPriceService;


    //Add file to DB
    @PostMapping("/importExcel")
    public ResponseEntity<List<String>> importExcel(@RequestBody String details){
        List<String> result = stockPriceService.addExcelData(details);
        return ResponseEntity.ok().body(result);
    }
}
