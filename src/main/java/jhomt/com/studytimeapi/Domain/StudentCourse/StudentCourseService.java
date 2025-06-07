package jhomt.com.studytimeapi.Domain.StudentCourse;

import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public DataListStudentCourse updateStudentCourse(DataUpdateStudentCourse dataUpdateStudentCourse) {
        Student student = validationsIDsGlobalService.findStudentById(dataUpdateStudentCourse.studentId());
        Course course = validationsIDsGlobalService.findCourseById(dataUpdateStudentCourse.courseId());

        StudentCourse studentCourse = new StudentCourse(dataUpdateStudentCourse, student, course);
        studentCourse = studentCourseRepository.save(studentCourse);
        return new DataListStudentCourse(studentCourse);
    }

    public List<StudentCourse> listStudentCourses() {
        return studentCourseRepository.findAll();
    }

    public List<DataListStudentCourse> listStudentCoursesByCourseId(Integer courseId) {
        Course course = validationsIDsGlobalService.findCourseById(courseId);
        return course.getStudentCourses()
                .stream()
                .map(DataListStudentCourse::new)
                .toList();
    }

}
