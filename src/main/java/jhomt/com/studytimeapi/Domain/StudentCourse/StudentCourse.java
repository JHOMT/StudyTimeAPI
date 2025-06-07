package jhomt.com.studytimeapi.Domain.StudentCourse;

import jakarta.persistence.*;
import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.Student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_courses")
public class StudentCourse {
    @EmbeddedId
    private StudentCourseId id;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @MapsId("courseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    public StudentCourse(DataRegisterStudentCourse dataRegisterStudentCourse, Student student, Course course) {
        this.id = new StudentCourseId(dataRegisterStudentCourse.studentId(), dataRegisterStudentCourse.courseId());
        this.student = student;
        this.course = course;
    }

    public StudentCourse(Student student, Course course) {
        this.id = new StudentCourseId(student.getId(), course.getId());
        this.student = student;
        this.course = course;
    }

    public void changeStatus() {
        this.status = !this.status;
    }
}