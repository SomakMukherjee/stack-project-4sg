package company.dto;

import org.springframework.data.annotation.Id;

public class StockExchangeDto {
    @Id
    private String id;
    private String stockExchange;
    private String brief;
    private String contactAddress;
    private String remarks;

    public StockExchangeDto(String stockExchange, String brief, String contactAddress, String remarks) {
        this.stockExchange = stockExchange;
        this.brief = brief;
        this.contactAddress = contactAddress;
        this.remarks = remarks;
    }

    public StockExchangeDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
