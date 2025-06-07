package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.Unit.DataListUnit;
import jhomt.com.studytimeapi.Domain.Unit.DataRegisterUnit;
import jhomt.com.studytimeapi.Domain.Unit.DataUpdateUnit;
import jhomt.com.studytimeapi.Domain.Unit.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    public ResponseEntity<?> registerUnit(@RequestBody @Valid DataRegisterUnit dataRegisterUnit) {
        try {
            DataListUnit unit = unitService.registerUnit(dataRegisterUnit);
            return new ResponseEntity<>(unit, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering unit: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUnit(@RequestBody @Valid DataUpdateUnit dataUpdateUnit) {
        try {
            DataListUnit updatedUnit = unitService.updateUnit(dataUpdateUnit);
            return new ResponseEntity<>(updatedUnit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating unit: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<?> listUnitsByCourseId(@PathVariable Integer courseId) {
        try {
            List<DataListUnit> units = unitService.listUnitsByCourseId(courseId);
            return new ResponseEntity<>(units, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving units by course ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
