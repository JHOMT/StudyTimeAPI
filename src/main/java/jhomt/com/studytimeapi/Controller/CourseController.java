package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.Course.CourseService;
import jhomt.com.studytimeapi.Domain.Course.DataListCourse;
import jhomt.com.studytimeapi.Domain.Course.DataRegisterCourse;
import jhomt.com.studytimeapi.Domain.Course.DataUpdateCourse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> registerCourse(@RequestBody @Valid DataRegisterCourse dataRegisterCourse) {
        try {
            DataListCourse course = courseService.registerCourse(dataRegisterCourse);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering course: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCourse(@RequestBody @Valid DataUpdateCourse dataUpdateCourse) {
        try {
            DataListCourse updatedCourse = courseService.updateCourse(dataUpdateCourse);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating course: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listCourses() {
        try {
            List<DataListCourse> courses = courseService.listCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving courses: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/change-status/{courseId}")
    public ResponseEntity<?> changeCourseStatus(@PathVariable Integer courseId) {
        try {
            courseService.changeCourseStatus(courseId);
            return new ResponseEntity<>("Course status updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error changing course status: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by-course-type/{courseTypeId}")
    public ResponseEntity<?> listCoursesByCourseType(@PathVariable Integer courseTypeId) {
        try {
            List<DataListCourse> courses = courseService.listCoursesByCourseType(courseTypeId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving courses by course type: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
