package company.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Ipo")
public class Ipo {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String ipoId;
    private String companyName;
    private String stockExchange;
    private Float pricePerShare;
    private Integer noOfShares;
    private String openingDate;
    private String openingTime;
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Ipo() {
    }

    public Ipo(String ipoId, String companyName, String stockExchange, Float pricePerShare, Integer noOfShares, String openingDate, String openingTime, String remarks){
        super();
        this.ipoId = ipoId;
        this.companyName = companyName;
        this.stockExchange = stockExchange;
        this.pricePerShare = pricePerShare;
        this.noOfShares = noOfShares;
        this.openingDate = openingDate;
        this.openingTime = openingTime;
        this.remarks = remarks;
    }

    public String getIpoId() {
        return ipoId;
    }

    public void setIpoId(String ipoId) {
        this.ipoId = ipoId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public Float getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(Float pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    public Integer getNoOfShares() {
        return noOfShares;
    }

    public void setNoOfShares(Integer noOfShares) {
        this.noOfShares = noOfShares;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    @Override
    public String toString() {
        return "Ipo{" +
                "ipoId=" + ipoId +
                ", companyName='" + companyName + '\'' +
                ", stockExchange='" + stockExchange + '\'' +
                ", pricePerShare=" + pricePerShare +
                ", noOfShares=" + noOfShares +
                ", openingDate='" + openingDate + '\'' +
                ", openingTime='" + openingTime + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
