package ua.iot.kovalyk.mysqlspringlab.controller;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;
import ua.iot.kovalyk.mysqlspringlab.dto.RepairmanDTO;
import ua.iot.kovalyk.mysqlspringlab.dto.mappers.RepairmanDTOAssembler;
import ua.iot.kovalyk.mysqlspringlab.service.RepairmanService;

@RestController
@RequestMapping(value =  "/api/repairmen")
public class RepairmanController extends  GeneralController<Repairman, RepairmanDTO, Integer>{


    public RepairmanController(RepairmanService service, RepairmanDTOAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RepairmanDTO>> getAllEntities() {
        return super.getAllEntities();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RepairmanDTO> getEntity(@PathVariable Integer id) {
        return super.getEntity(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<RepairmanDTO> addEntity(@RequestBody Repairman entity) {
        return super.addEntity(entity);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEntity(@RequestBody Repairman updateEntity, @PathVariable Integer id) {
        return super.updateEntity(updateEntity, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer id) {
        return super.deleteEntity(id);
    }
}
