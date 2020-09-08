package excel.service;

import excel.model.StockPrice;
import excel.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    @Autowired
    private StockPriceRepository stockPriceRepository;

    @Override
    public List<String> addExcelData(String details) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
        details = details.substring(2,details.length()-2);
        String[] detailList=details.split("},\\{");
        LocalDateTime min = null;
        LocalDateTime max = null;
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
            if(i==0){
                min=localDateTime;
                max=localDateTime;
            }
            else if(min.isAfter(localDateTime)){
                min =localDateTime;
            }
            else if(max.isBefore(localDateTime)){
                max=localDateTime;
            }
            StockPrice sp = new StockPrice(objList[0],objList[1],parseFloat(objList[2]),localDateTime);
            stockPriceRepository.save(sp);
        }
        List<String> result= new ArrayList<>();
        result.add(""+detailList.length);
        result.add(min.toString());
        result.add(max.toString());
        return result;
    }
}
