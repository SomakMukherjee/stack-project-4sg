package company.controller;

import company.model.Ipo;
import company.service.IpoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = {"http://localhost:8088","http://localhost:4200"})
@RestController
public class IpoController {
    private IpoService ipoService;

    public IpoController(IpoService ipoService) {
        this.ipoService = ipoService;
    }

    //Get Company data
    @GetMapping("/ipos/{companyName}")
    public ResponseEntity<Iterable<Ipo>> getIpos(@PathVariable String companyName) {
        if (ipoService.ifExistsByName(companyName)) {
            return new ResponseEntity<Iterable<Ipo>>(ipoService.findByCompanyName(companyName), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    //update ipo data
    @PostMapping("/ipos/update")
    public ResponseEntity<Ipo> updateIpo(@RequestBody Ipo ipo) {
        if(ipoService.ifExistsById(ipo.getIpoId())) {
            return new ResponseEntity<Ipo>(ipoService.addIpo(ipo), HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    //add ipo data
    @PostMapping("/ipos/add")
    public ResponseEntity<Ipo> addIpo(@RequestBody Ipo ipo) {
        return new ResponseEntity<Ipo>(ipoService.addIpo(ipo), HttpStatus.CREATED);
    }

}
