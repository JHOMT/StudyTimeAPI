package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.Student.DataListStudent;
import jhomt.com.studytimeapi.Domain.Student.DataLoginStudent;
import jhomt.com.studytimeapi.Domain.Student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final StudentService studentService;

    public AuthController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid DataLoginStudent loginStudent) {
        DataListStudent student = studentService.authLogin(loginStudent);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
