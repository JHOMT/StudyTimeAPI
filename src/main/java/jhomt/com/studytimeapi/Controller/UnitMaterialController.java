package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.UnitMaterial.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit-materials")
public class UnitMaterialController {

    private final UnitMaterialService unitMaterialService;

    public UnitMaterialController(UnitMaterialService unitMaterialService) {
        this.unitMaterialService = unitMaterialService;
    }

    @PostMapping
    public ResponseEntity<?> registerUnitMaterial(@RequestBody @Valid DataRegisterUnitMaterial dataRegisterUnitMaterial) {
        try {
            DataListUnitMaterial unitMaterial = unitMaterialService.registerUnitMaterial(dataRegisterUnitMaterial);
            return new ResponseEntity<>(unitMaterial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering unit material: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUnitMaterial(@RequestBody @Valid DataUpdateUnitMaterial dataUpdateUnitMaterial) {
        try {
            DataListUnitMaterial updatedUnitMaterial = unitMaterialService.updateUnitMaterial(dataUpdateUnitMaterial);
            return new ResponseEntity<>(updatedUnitMaterial, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating unit material: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-unit/{unitId}")
    public ResponseEntity<?> listUnitMaterialsByUnitIdAll(@PathVariable Integer unitId) {
        try {
            List<DataListUnitMaterial> unitMaterials = unitMaterialService.listUnitMaterialsByUnitIdAll(unitId);
            return new ResponseEntity<>(unitMaterials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving unit materials: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info/{unitMaterialId}")
    public ResponseEntity<?> listUnitMaterialsByUnitIdInfo(@PathVariable Integer unitMaterialId) {
        try {
            DataListUnitMaterialInfo unitMaterialInfo = unitMaterialService.listUnitMaterialsByUnitIdInfo(unitMaterialId);
            return new ResponseEntity<>(unitMaterialInfo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving unit material info: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
