package jhomt.com.studytimeapi.Domain.StudentCourse;

import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
    Optional<StudentCourse> findByStudentAndCourse(Student student, Course course);
}
