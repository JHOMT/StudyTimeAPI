package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.StudentReward.DataListStudentReward;
import jhomt.com.studytimeapi.Domain.StudentReward.DataRegisterStudentReward;
import jhomt.com.studytimeapi.Domain.StudentReward.DataUpdateStudentReward;
import jhomt.com.studytimeapi.Domain.StudentReward.StudentRewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-rewards")
public class StudentRewardController {

    private final StudentRewardService studentRewardService;

    public StudentRewardController(StudentRewardService studentRewardService) {
        this.studentRewardService = studentRewardService;
    }

    @PostMapping
    public ResponseEntity<?> registerStudentReward(@Valid @RequestBody DataRegisterStudentReward dataRegisterStudentReward) {
        try {
            DataListStudentReward studentReward = studentRewardService.registerStudentReward(dataRegisterStudentReward);
            return new ResponseEntity<>(studentReward, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering student reward: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateStudentReward(@Valid @RequestBody DataUpdateStudentReward dataUpdateStudentReward) {
        try {
            DataListStudentReward updatedStudentReward = studentRewardService.updateStudentReward(dataUpdateStudentReward);
            return new ResponseEntity<>(updatedStudentReward, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating student reward: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listStudentRewards() {
        try {
            List<DataListStudentReward> studentRewards = studentRewardService.listStudentRewards();
            return new ResponseEntity<>(studentRewards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student rewards: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-student-reward/{studentRewardId}")
    public ResponseEntity<?> findStudentRewardById(@PathVariable Integer studentRewardId) {
        try {
            List<DataListStudentReward> studentRewards = studentRewardService.findStudentRewardById(studentRewardId);
            return new ResponseEntity<>(studentRewards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student reward by ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<?> findStudentById(@PathVariable Integer studentId) {
        try {
            List<DataListStudentReward> studentRewards = studentRewardService.findStudentById(studentId);
            return new ResponseEntity<>(studentRewards, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student reward by ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
