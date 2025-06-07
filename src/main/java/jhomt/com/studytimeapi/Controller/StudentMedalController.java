package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.StudentMedal.DataListStudentMedal;
import jhomt.com.studytimeapi.Domain.StudentMedal.DataRegisterStudentMedal;
import jhomt.com.studytimeapi.Domain.StudentMedal.DataUpdateStudentMedal;
import jhomt.com.studytimeapi.Domain.StudentMedal.StudentMedalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-medals")
public class StudentMedalController {

    private final StudentMedalService studentMedalService;

    public StudentMedalController(StudentMedalService studentMedalService) {
        this.studentMedalService = studentMedalService;
    }

    @PostMapping
    public ResponseEntity<?> registerStudentMedal(@Valid @RequestBody DataRegisterStudentMedal dataRegisterStudentMedal) {
        try {
            DataListStudentMedal studentMedal = studentMedalService.registerStudentMedal(dataRegisterStudentMedal);
            return new ResponseEntity<>(studentMedal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering student medal: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateStudentMedal(@Valid @RequestBody DataUpdateStudentMedal dataUpdateStudentMedal) {
        try {
            DataListStudentMedal updatedStudentMedal = studentMedalService.updateStudentMedal(dataUpdateStudentMedal);
            return new ResponseEntity<>(updatedStudentMedal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating student medal: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listStudentMedals() {
        try {
            List<DataListStudentMedal> studentMedals = studentMedalService.listStudentMedals();
            return new ResponseEntity<>(studentMedals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student medals: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<?> listStudentMedalsByStudentId(@PathVariable Integer studentId) {
        try {
            List<DataListStudentMedal> studentMedals = studentMedalService.listStudentMedalsByStudentId(studentId);
            return new ResponseEntity<>(studentMedals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student medals by student ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
