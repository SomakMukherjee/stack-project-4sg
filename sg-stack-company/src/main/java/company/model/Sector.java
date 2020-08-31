package company.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Sector {

    @Id
    private String id;
    private String sectorName;
    private String brief;
    private List<Company> companyList = new ArrayList<Company>();

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public Sector(String id, String sectorName, String brief) {
        this.id = id;
        this.sectorName = sectorName;
        this.brief = brief;
    }

    public Sector() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    @Override
    public String toString() {
        return "Sectors{" +
                "id='" + id + '\'' +
                ", sectorName='" + sectorName + '\'' +
                ", brief='" + brief + '\'' +
                '}';
    }
}
