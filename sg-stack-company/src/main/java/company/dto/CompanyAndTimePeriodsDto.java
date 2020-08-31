package company.dto;

import java.util.List;

public class CompanyAndTimePeriodsDto {
    private CompanyDto companyDto;
    private List<String> timePeriods;

    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }

    public List<String> getTimePeriods() {
        return timePeriods;
    }

    public void setTimePeriods(List<String> timePeriods) {
        this.timePeriods = timePeriods;
    }
}
