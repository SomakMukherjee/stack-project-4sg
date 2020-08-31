package excel;

import excel.repository.StockPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelApplication{// implements CommandLineRunner{

	@Autowired
	private StockPriceRepository stockPriceRepository;

	public static void main(String[] args){
		SpringApplication.run(ExcelApplication.class, args);
	}

}

