package excel.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "StockPrice")
public class StockPriceOld {

    @Id
    private String id;
    private String companyCode;
    private String stockExchange;
    private float pricePerShare;
    private LocalDate date;
    private LocalTime time;

    public StockPriceOld(){

    }

    public StockPriceOld(String companyCode, String stockExchange, float pricePerShare, LocalDate date, LocalTime time) {
        this.companyCode = companyCode;
        this.stockExchange = stockExchange;
        this.pricePerShare = pricePerShare;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "id='" + id + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", stockExchange='" + stockExchange + '\'' +
                ", pricePerShare=" + pricePerShare +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getId() {
        return id;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public float getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(float pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
