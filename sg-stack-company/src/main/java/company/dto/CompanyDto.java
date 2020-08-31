package company.dto;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class CompanyDto {
    @Id
    private String companyId;
    private String companyName;
    private String ceo;
    private String boardOfDirectors;
    private List<String> stockExchanges = new ArrayList<String>();
    private String sector;
    private String description;
    private List<String> codeInStockExchange = new ArrayList<String>();

    public String getCompanyId() {
        return companyId;
    }

    public CompanyDto() {
    }

    public CompanyDto(String id, String companyName, String ceo, String boardOfDirectors,List<String> stockExchanges, String sector, String description, List<String> codeInStockExchange) {
        super();
        this.companyId = id;
        this.companyName = companyName;
        this.ceo = ceo;
        this.boardOfDirectors = boardOfDirectors;
        this.stockExchanges = stockExchanges;
        this.sector = sector;
        this.description = description;
        this.codeInStockExchange = codeInStockExchange;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getBoardOfDirectors() {
        return boardOfDirectors;
    }

    public void setBoardOfDirectors(String boardOfDirectors) {
        this.boardOfDirectors = boardOfDirectors;
    }

    public List<String> getStockExchanges() {
        return stockExchanges;
    }

    public void setStockExchanges(List<String> stockExchanges) {
        this.stockExchanges = stockExchanges;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCodeInStockExchange() {
        return codeInStockExchange;
    }

    public void setCodeInStockExchange(List<String> codeInStockExchange) {
        this.codeInStockExchange = codeInStockExchange;
    }
}

