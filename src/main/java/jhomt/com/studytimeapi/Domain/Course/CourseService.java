package jhomt.com.studytimeapi.Domain.Course;

import jhomt.com.studytimeapi.Domain.CourseType.CourseType;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public CourseService(CourseRepository courseRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.courseRepository = courseRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public Course registerCourse(DataRegisterCourse dataRegisterCourse) {
        Course course = new Course(dataRegisterCourse);
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(DataUpdateCourse dataUpdateCourse) {
        Course course = findCourseById(dataUpdateCourse.id());
        course.update(dataUpdateCourse);
        return courseRepository.save(course);
    }

    public List<DataListCourse> listCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(DataListCourse::new)
                .toList();
    }

    @Transactional
    public void changeCourseStatus(Integer courseId) {
        Course course = findCourseById(courseId);
        course.changeStatus();
        courseRepository.save(course);
    }

    public List<DataListCourse> listCoursesByCourseType(Integer courseTypeId) {
        CourseType type = validationsIDsGlobalService.findCourseTypeById(courseTypeId);
        return type.getCourses()
                .stream()
                .map(DataListCourse::new)
                .toList();
    }

    public Course findCourseById (Integer courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
}
