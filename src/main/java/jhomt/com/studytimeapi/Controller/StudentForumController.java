package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.StudentForum.DataListStudentForum;
import jhomt.com.studytimeapi.Domain.StudentForum.DataRegisterStudentForum;
import jhomt.com.studytimeapi.Domain.StudentForum.DataUpdateStudentForum;
import jhomt.com.studytimeapi.Domain.StudentForum.StudentForumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-forums")
public class StudentForumController {

    private final StudentForumService studentForumService;

    public StudentForumController(StudentForumService studentForumService) {
        this.studentForumService = studentForumService;
    }

    @PostMapping
    public ResponseEntity<?> registerStudentForum(@RequestBody @Valid DataRegisterStudentForum dataRegisterStudentForum) {
        try {
            DataListStudentForum studentForum = studentForumService.registerStudentForum(dataRegisterStudentForum);
            return new ResponseEntity<>(studentForum, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering student forum: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateStudentForum(@RequestBody @Valid DataUpdateStudentForum dataUpdateStudentForum) {
        try {
            DataListStudentForum updatedStudentForum = studentForumService.updateStudentForum(dataUpdateStudentForum);
            return new ResponseEntity<>(updatedStudentForum, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating student forum: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-forum/{forumId}")
    public ResponseEntity<?> findStudentForumById(@PathVariable Integer forumId) {
        try {
            List<DataListStudentForum> studentForums = studentForumService.findStudentForumById(forumId);
            return new ResponseEntity<>(studentForums, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student forums: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
