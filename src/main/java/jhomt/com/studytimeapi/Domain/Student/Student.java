package jhomt.com.studytimeapi.Domain.Student;

import jakarta.persistence.*;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.GlobalConfiguration.GlobalConfiguration;
import jhomt.com.studytimeapi.Domain.Medal.Medal;
import jhomt.com.studytimeapi.Domain.PomodoroSession.PomodoroSession;
import jhomt.com.studytimeapi.Domain.StudentCourse.StudentCourse;
import jhomt.com.studytimeapi.Domain.StudentMedal.StudentMedal;
import jhomt.com.studytimeapi.Domain.StudentReward.StudentReward;
import jhomt.com.studytimeapi.Domain.StudentTask.StudentTask;
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
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "total_points")
    private Integer totalPoints = 0;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourse> courses;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PomodoroSession> pomodoroSessions;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentMedal> medals;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentReward> rewards;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentTask> tasks;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private GlobalConfiguration globalConfiguration;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentTask> studentTasks;

    public Student (DataRegisterStudent registerStudent) {
        this.name = registerStudent.name();
        this.email = registerStudent.email();
        this.password = registerStudent.password();
    }

    public void update(DataUpdateStudent updateStudent) {
        EntityUpdater.updateIfNotNull(updateStudent.name(), this::setName);
        EntityUpdater.updateIfNotNull(updateStudent.email(), this::setEmail);
        EntityUpdater.updateIfNotNull(updateStudent.password(), this::setPassword);
    }

    public void sumPoints(Integer pointsAwarded) {
        this.totalPoints += pointsAwarded;
    }
}