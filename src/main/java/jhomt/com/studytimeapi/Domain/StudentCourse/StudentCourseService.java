package jhomt.com.studytimeapi.Domain.StudentCourse;

import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public StudentCourseService(StudentCourseRepository studentCourseRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.studentCourseRepository = studentCourseRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListStudentCourse registerStudentCourse(DataRegisterStudentCourse dataRegisterStudentCourse) {
        Student student = validationsIDsGlobalService.findStudentById(dataRegisterStudentCourse.studentId());
        Course course = validationsIDsGlobalService.findCourseById(dataRegisterStudentCourse.courseId());

        StudentCourse studentCourse = new StudentCourse(dataRegisterStudentCourse, student, course);
        studentCourse = studentCourseRepository.save(studentCourse);
        return new DataListStudentCourse(studentCourse);
    }

    public List<DataListStudentCourse> listStudentCoursesByCourseId(Integer courseId) {
        Course course = validationsIDsGlobalService.findCourseById(courseId);
        return course.getStudentCourses()
                .stream()
                .map(DataListStudentCourse::new)
                .toList();
    }

    public List<DataListStudentCourse> listCoursesByStudentId(Integer studentId) {
        Student student = validationsIDsGlobalService.findStudentById(studentId);
        return student.getCourses()
                .stream()
                .map(DataListStudentCourse::new)
                .toList();
    }

    public void changeStatusByStudentCourseId(Integer studentId, Integer courseId) {
        Student student = validationsIDsGlobalService.findStudentById(studentId);
        Course course = validationsIDsGlobalService.findCourseById(courseId);

        StudentCourse studentCourse = studentCourseRepository.findByStudentAndCourse(student, course)
                .orElseThrow(() -> new RuntimeException("StudentCourse not found"));


        studentCourse.changeStatus();
        studentCourseRepository.save(studentCourse);
    }
}
