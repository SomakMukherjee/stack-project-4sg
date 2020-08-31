package excel.controller;
import excel.model.StockPrice;
import excel.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Float.parseFloat;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StockPriceController {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    //Add file to DB
    @PostMapping("/importExcel")
    public String importExcel(@RequestBody String details){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
        details = details.substring(2,details.length()-2);
        String[] detailList=details.split("},\\{");
        for(int i=0;i<detailList.length;i++){
            String[] objList = detailList[i].split(",");
            for(int j=0;j< objList.length;j++){
                objList[j]=objList[j].substring(objList[j].lastIndexOf('"',objList[j].length()-2)+1,objList[j].length()-1).trim();
                if(j==0){
                    objList[j] = objList[j].substring(0,objList[j].length()-1);
                }
            }
            String dateTime = objList[3]+":"+objList[4];
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime,formatter);
            StockPrice sp = new StockPrice(objList[0],objList[1],parseFloat(objList[2]),localDateTime);
            stockPriceRepository.save(sp);
        }
        return "[999]";
    }

    //Get Data from DB
}
