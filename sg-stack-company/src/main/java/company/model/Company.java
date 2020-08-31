package company.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Company")
public class Company {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String companyId;
    private String companyName;
    private String ceo;
    private String boardOfDirectors;
    private List<String> stockExchanges = new ArrayList<>();
    private String sector;
    private String description;
    private List<String> codeInStockExchange = new ArrayList<>();



    public String getCompanyId() {
        return companyId;
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

    public Company() {
    }

    public Company(String companyId, String companyName, String ceo, String boardOfDirectors, List<String> stockExchanges, String sector, String description, List<String> codeInStockExchange) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.ceo = ceo;
        this.boardOfDirectors = boardOfDirectors;
        this.stockExchanges = stockExchanges;
        this.sector = sector;
        this.description = description;
        this.codeInStockExchange = codeInStockExchange;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ceo='" + ceo + '\'' +
                ", boardOfDirectors='" + boardOfDirectors + '\'' +
                ", stockExchanges=" + stockExchanges +
                ", sector=" + sector +
                ", description='" + description + '\'' +
                ", codeInStockExchange=" + codeInStockExchange +
                '}';
    }
}
