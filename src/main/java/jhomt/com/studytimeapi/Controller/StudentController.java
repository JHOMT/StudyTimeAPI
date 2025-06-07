package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.Student.DataListStudent;
import jhomt.com.studytimeapi.Domain.Student.DataRegisterStudent;
import jhomt.com.studytimeapi.Domain.Student.DataUpdateStudent;
import jhomt.com.studytimeapi.Domain.Student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> registerStudent(@RequestBody @Valid DataRegisterStudent dataRegisterStudent) {
        try {
            DataListStudent student = studentService.registerStudentAndCreateConfiguration(dataRegisterStudent);
            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering student: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@RequestBody DataUpdateStudent dataUpdateStudent) {
        try {
            DataListStudent updatedStudent = studentService.updateStudent(dataUpdateStudent);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating student: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listStudents() {
        try {
            List<DataListStudent> students = studentService.listStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving students: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
