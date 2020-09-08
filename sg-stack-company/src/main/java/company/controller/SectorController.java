package company.controller;

import company.dto.SectorDto;
import company.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8088","http://localhost:4200"})
@RestController
public class SectorController {
    private SectorService sectorService;
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping("/sectors")
    public ResponseEntity<List<SectorDto>> getAllSectors(){
        return ResponseEntity.ok().body(sectorService.findAll());
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
        if(sectorService.ifExistsByName(sectorDto.getSectorName())){
            if(!sectorService.findByName(sectorDto.getSectorName()).get(0).getId().equals(sectorDto.getId())){
            return ResponseEntity.badRequest().body(new SectorDto("Sector With same name already exists",""));
            }
        }
        return ResponseEntity.ok().body(sectorService.addSector(sectorDto));
    }
}
