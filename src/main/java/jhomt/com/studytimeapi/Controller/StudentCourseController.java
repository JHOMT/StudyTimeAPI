package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jhomt.com.studytimeapi.Domain.StudentCourse.DataListStudentCourse;
import jhomt.com.studytimeapi.Domain.StudentCourse.DataRegisterStudentCourse;
import jhomt.com.studytimeapi.Domain.StudentCourse.StudentCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-courses")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    public StudentCourseController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @PostMapping
    public ResponseEntity<?> registerStudentCourse(@RequestBody @Valid DataRegisterStudentCourse dataRegisterStudentCourse) {
        try {
            DataListStudentCourse studentCourse = studentCourseService.registerStudentCourse(dataRegisterStudentCourse);
            return new ResponseEntity<>(studentCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering student course: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<?> listStudentCoursesByCourseId(@PathVariable Integer courseId) {
        try {
            List<DataListStudentCourse> studentCourses = studentCourseService.listStudentCoursesByCourseId(courseId);
            return new ResponseEntity<>(studentCourses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving student courses by course ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<?> listCoursesByStudentId(@PathVariable Integer studentId) {
        try {
            List<DataListStudentCourse> courses = studentCourseService.listCoursesByStudentId(studentId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving courses by student ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/change-status/{studentId}/{courseId}")
    public ResponseEntity<?> changeStatusByStudentCourseId(
            @PathVariable @NotNull Integer studentId,
            @PathVariable @NotNull Integer courseId
    ) {
        try {
            studentCourseService.changeStatusByStudentCourseId(studentId, courseId);
            return new ResponseEntity<>("Change status successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error change status: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
