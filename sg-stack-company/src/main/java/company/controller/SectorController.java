package company.controller;

import company.dto.SectorDto;
import company.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class SectorController {
    private SectorService sectorService;
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/sectors")
    public ResponseEntity<Iterable<SectorDto>> getAllSectors(){
        return new ResponseEntity<Iterable<SectorDto>>(sectorService.findAll(), HttpStatus.OK);
    }

    //add Sector
    @PostMapping("/sectors/add")
    @Transactional
    public ResponseEntity<SectorDto> addSector(@RequestBody SectorDto sectorDto){
        return ResponseEntity.ok().body(sectorService.addSector(sectorDto));
    }

    //delete Sector
    @DeleteMapping("/sectors/delete/{sectorName}")
    @Transactional
    public ResponseEntity<SectorDto> deleteSector(@PathVariable String sectorName){
        SectorDto deletedSector = sectorService.findByName(sectorName).iterator().next();
        sectorService.deleteSectorByName(sectorName);
        return ResponseEntity.ok().body(deletedSector);
    }

    //update Sector
    @PostMapping("sectors/update")
    @Transactional
    public ResponseEntity<SectorDto> updateSector(@RequestBody SectorDto sectorDto){
        return ResponseEntity.ok().body(sectorService.addSector(sectorDto));
    }
}
