package excel.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Document(collection = "StockPrice")
public class StockPrice {

    @Id
    private String id;
    private String companyCode;
    private String stockExchange;
    private float pricePerShare;
    private LocalDateTime dateTime;


    public StockPrice(){

    }

    public StockPrice(String companyCode, String stockExchange, float pricePerShare, LocalDateTime dateTime) {
        this.companyCode = companyCode;
        this.stockExchange = stockExchange;
        this.pricePerShare = pricePerShare;
        this.dateTime = dateTime;

    }

    @Override
    public String toString() {
        return "StockPrice{" +
                "id='" + id + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", stockExchange='" + stockExchange + '\'' +
                ", pricePerShare=" + pricePerShare +
                ", dateTime=" + dateTime +
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
