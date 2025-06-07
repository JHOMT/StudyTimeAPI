package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.Medal.DataListMedal;
import jhomt.com.studytimeapi.Domain.Medal.DataRegisterMedal;
import jhomt.com.studytimeapi.Domain.Medal.DataUpdateMedal;
import jhomt.com.studytimeapi.Domain.Medal.MedalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medals")
public class MedalController {

    private final MedalService medalService;

    public MedalController(MedalService medalService) {
        this.medalService = medalService;
    }

    @PostMapping
    public ResponseEntity<?> registerMedal(@RequestBody @Valid DataRegisterMedal dataRegisterMedal) {
        try {
            DataListMedal medal = medalService.registerMedal(dataRegisterMedal);
            return new ResponseEntity<>(medal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering medal: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateMedal(@RequestBody @Valid DataUpdateMedal dataUpdateMedal) {
        try {
            DataListMedal updatedMedal = medalService.updateMedal(dataUpdateMedal);
            return new ResponseEntity<>(updatedMedal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating medal: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listMedals() {
        try {
            List<DataListMedal> medals = medalService.listMedals();
            return new ResponseEntity<>(medals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving medals: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
