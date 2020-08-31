package company.controller;

import company.dto.CompanyAndTimePeriodsDto;
import company.dto.CompanyDto;
import company.dto.StockDto;
import company.service.CompanyService;
import company.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController {

    private CompanyService companyService;
    private StockService stockService;

    public CompanyController(CompanyService companyService, StockService stockService) {
        this.companyService = companyService;
        this.stockService = stockService;
    }


    @GetMapping("/companies")
    public ResponseEntity<Iterable<CompanyDto>> getCompanyDetails(){

        return new ResponseEntity<Iterable<CompanyDto>>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping("/companies/add")
    @Transactional
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto companyDto) {
        if(!companyService.ifExistsByName(companyDto.getCompanyName())){
            return ResponseEntity.ok().body(companyService.addCompany(companyDto));
        }
        CompanyDto failResult = new CompanyDto();
        failResult.setCompanyName("Company with same name already exists. Try again.");
        return ResponseEntity.badRequest().body(failResult);
    }

    @PostMapping("/stocks/add/{companyName}")
    @Transactional
    public ResponseEntity<StockDto> addStock(@RequestBody StockDto stockDto, @PathVariable("companyName") String  companyName){
        CompanyDto companyDto = companyService.getCompanyByName(companyName);
        stockService.addStock(stockDto);
        List<String> stockExchanges = companyDto.getStockExchanges();
        List<String> companyCodes = companyDto.getCodeInStockExchange();
        companyCodes.add(stockDto.getCompanyCode());
        stockExchanges.add(stockDto.getStockExchange());
        companyDto.setCodeInStockExchange(companyCodes);
        companyDto.setStockExchanges(stockExchanges);
        companyService.updateCompany(companyDto,companyDto.getCompanyId());
        return ResponseEntity.ok().body(stockDto);
    }

    @PutMapping("/companies/update/{companyId}")
    @Transactional
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto companyDto,@PathVariable("companyId") String companyId){
        return new ResponseEntity<CompanyDto> (companyService.updateCompany(companyDto,companyId), HttpStatus.OK);

    }

    @DeleteMapping("/companies/delete/{companyId}")
    @Transactional
    public ResponseEntity<String> deleteCompany(@PathVariable("companyId") String companyId){

        companyService.deleteCompany(companyId);
        return new ResponseEntity<String>("Successfully Deleted!!!", HttpStatus.OK);

    }

    @GetMapping("/companies/findByPattern/{pattern}")
    public ResponseEntity<List<CompanyDto>> searchCompany(@PathVariable("pattern") String pattern)
    {
        return new ResponseEntity<List<CompanyDto>> (companyService.searchByPattern(pattern), HttpStatus.OK);
    }

    //findCompanyByName+LatestStockInfo
    @GetMapping("/companies/name/{companyName}")
    public ResponseEntity<List<Object>> getCompanyByName(@PathVariable("companyName") String companyName) {
        List<Object> result = new ArrayList();
        if(companyService.ifExistsByName(companyName)) {
            CompanyDto companyDetails = companyService.getCompanyByName(companyName);
            result.add(companyDetails);
            List<String> companyCodes = companyDetails.getCodeInStockExchange();
            for (int i = 0; i < companyCodes.size(); i++) {
                StockDto latestStock = getCompanyStockPrice(companyCodes.get(i)).getBody();
                result.add(latestStock);
            }
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }

    //check stock price of company
    @GetMapping("/companies/stocks/{companyCode}")
    public ResponseEntity<StockDto> getCompanyStockPrice(@PathVariable("companyCode") String companyCode) {
        List<StockDto> x = stockService.getStockByCompanyCode(companyCode);
        if (x.size() > 0) {
            return ResponseEntity.ok().body(x.get(0));
        }
        return ResponseEntity.notFound().build();
    }

    //A company stock info over period of time with specific intervals
    @GetMapping("/companies/stock/{periodData}")
    public ResponseEntity<Object> getCompanyStockPrice(@RequestBody CompanyDto companyDto, @PathVariable("periodData") String periodData){

        int noOfStockExchanges = companyDto.getCodeInStockExchange().size();
        List<List<StockDto>> result= new ArrayList<List<StockDto>>();

        for(int i =0;i<noOfStockExchanges;i++) {
            String companyCode = companyDto.getCodeInStockExchange().get(i);
            String[] periodDataList = periodData.split("\\+");
            //periodDataList comes in format startDate(//)+startTime(::)+endDate(//)+endTime(::)+periodicity
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy:HH:mm:ss");
            String startDate = periodDataList[0] + ":" + periodDataList[1];
            LocalDateTime localDateStart = LocalDateTime.parse(startDate, formatter);
            String endDate = periodDataList[2] + ":" + periodDataList[3];
            LocalDateTime localDateEnd = LocalDateTime.parse(endDate, formatter);
            int periodicity = Integer.parseInt(periodDataList[4]);
            List<StockDto> x = stockService.getStockByCompanyCodeOverPeriod(companyCode, localDateStart, localDateEnd, periodicity);
            result.add(x);
        }
        return ResponseEntity.ok().body(result);
    }

    //B for a list of companies do A
    @GetMapping("/companies/stock/multiple/{periodData}")
    public ResponseEntity<Object> getCompaniesStockPrice(@RequestBody List<CompanyDto> companyDtos, @PathVariable("periodData") String periodData){
        List<Object> result=new ArrayList();
        for(int i=0; i<companyDtos.size();i++){
            result.add(getCompanyStockPrice(companyDtos.get(i),periodData).getBody());
        }
        return ResponseEntity.ok().body(result);
    }

    //findCompaniesBySector
    @GetMapping("/companies/sector/{sectorName}")
    public ResponseEntity<List<CompanyDto>> getCompaniesBySector(@PathVariable("sectorName") String sectorName){
        return ResponseEntity.ok().body(companyService.getCompanyBySector(sectorName));
    }
    //C for each company in a sector do A
    @GetMapping("/companies/stock/sector/{sector}/{periodData}")
    public ResponseEntity<Object> getCompanyStockPricesBySector(@PathVariable("sector") String sector, @PathVariable("periodData") String periodData){
        List<CompanyDto> companyDtoList = getCompaniesBySector(sector).getBody();
        return ResponseEntity.ok().body(getCompaniesStockPrice(companyDtoList,periodData).getBody());
    }

    //for a list of sectors do C
    @GetMapping("/companies/stock/sector/multiple/{periodData}")
    public ResponseEntity<Object> getStockPricesBySectors(@RequestBody String[] sectors, @PathVariable("periodData") String periodData){
        List<Object> result= new ArrayList();
        for(int i=0; i<sectors.length;i++){
            result.add(getCompanyStockPricesBySector(sectors[i],periodData).getBody());
        }
        return ResponseEntity.ok().body(result);
    }
    //do A for a series of periods
    @GetMapping("/companies/stock/multiPeriods")
    public ResponseEntity<List<Object>> getStockPricesForCompanyOverPeriods(@RequestBody CompanyAndTimePeriodsDto companyAndPeriodData){
        CompanyDto companyDto = companyAndPeriodData.getCompanyDto();
        List<String> periods = companyAndPeriodData.getTimePeriods();
        List<Object> result = new ArrayList();
        for(int i=0;i<periods.size();i++){
            result.add(getCompanyStockPrice(companyDto,periods.get(i)).getBody());
        }
        return ResponseEntity.ok().body(result);
    }

    //do C for a series of periods
    @GetMapping("/companies/stock/sector/multiPeriods")
    public ResponseEntity<List<Object>> getStockPricesForSectorOverPeriods(@RequestBody CompanyAndTimePeriodsDto companyAndPeriodData){
        String sector = companyAndPeriodData.getCompanyDto().getSector();
        List<String> periods = companyAndPeriodData.getTimePeriods();
        List<Object> result = new ArrayList();
        for(int i=0;i<periods.size();i++){
            result.add(getCompanyStockPricesBySector(sector,periods.get(i)).getBody());
        }
        return ResponseEntity.ok().body(result);
    }

    //Do B and C
    @GetMapping("/companies/stock/sectorAndCompany/{sector}/{periodData}")
    public ResponseEntity<List<Object>> getStockPricesForBothCompanyAndSector(@RequestBody CompanyDto companyDto,  @PathVariable String sector, @PathVariable String periodData){
        List<Object> result = new ArrayList<>();
        result.add(getCompanyStockPrice(companyDto,periodData).getBody());
        result.add(getCompanyStockPricesBySector(sector,periodData).getBody());
        return ResponseEntity.ok().body((result));
    }


    //get list of companies in SE // Do from Company POV
    @GetMapping("/please/work/{stockExchange}")
    public ResponseEntity<List<CompanyDto>>  getCompaniesInStockExchange(@PathVariable("stockExchange") String stockExchange){
        return ResponseEntity.ok().body(companyService.getCompanyByStockExchange(stockExchange));
    }

    //remove company from SE //Visible from Company POV
    @PostMapping("/companies/stockExchange/delete/{companyName}/{stockExchange}")
    @Transactional
    public ResponseEntity<CompanyDto> removeStockExchangeFromCompany(@PathVariable("stockExchange") String stockExchange
                                                                        ,@PathVariable("companyName") String companyName){
        if(companyService.ifExistsByName(companyName)){
            CompanyDto temp = companyService.getCompanyByName(companyName);
            int index = temp.getStockExchanges().indexOf(stockExchange);
            if(index==-1){
                return  ResponseEntity.badRequest().body(new CompanyDto("","Stock Exchange Absent","","",null,"","",null));
            }
            temp.getStockExchanges().remove(index);
            temp.getCodeInStockExchange().remove(index);
            CompanyDto result = companyService.updateCompany(temp,temp.getCompanyId());
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }
    //add company to SE //Visible from Company POV
    @PostMapping("/companies/stockExchange/add/{companyName}/{stockExchange}/{companyCode}")
    @Transactional
    public ResponseEntity<CompanyDto> addStockExchangeFromCompany(@PathVariable("stockExchange") String stockExchange,
                                                                  @PathVariable("companyName") String companyName,
                                                                  @PathVariable("companyCode") String companyCode) {
        if (companyService.ifExistsByName(companyName)) {
            CompanyDto temp = companyService.getCompanyByName(companyName);
            int index = temp.getStockExchanges().indexOf(stockExchange);
            if (index != -1) {
                return ResponseEntity.badRequest().body(new CompanyDto("", "Stock Exchange Already Present", "", "", null, "", "", null));
            }
            temp.getStockExchanges().add(stockExchange);
            temp.getCodeInStockExchange().add(companyCode);
            CompanyDto result = companyService.updateCompany(temp, temp.getCompanyId());
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }

}
