package jhomt.com.studytimeapi.Controller;

import jakarta.validation.Valid;
import jhomt.com.studytimeapi.Domain.CourseType.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course-types")
public class CourseTypeController {

    private final CourseTypeService courseTypeService;

    public CourseTypeController(CourseTypeService courseTypeService) {
        this.courseTypeService = courseTypeService;
    }

    @PostMapping
    public ResponseEntity<?> registerCourseType(@RequestBody @Valid DataRegisterCourseType dataRegisterCourseType) {
        try {
            return new ResponseEntity<>(courseTypeService.registerCourseType(dataRegisterCourseType), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering course type: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCourseType(@RequestBody DataUpdateCourseType dataUpdateCourseType) {
        try {
            return new ResponseEntity<>(courseTypeService.updateCourseType(dataUpdateCourseType), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating course type: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> listCourseTypes() {
        try {
            return new ResponseEntity<>(courseTypeService.listCourseTypes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving course types: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<?> getCourseTypeById(@RequestParam Integer id) {
        try{
            return new ResponseEntity<>(courseTypeService.findById(id), HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>("Error retrieving course type: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
