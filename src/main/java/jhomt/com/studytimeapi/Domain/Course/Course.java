package jhomt.com.studytimeapi.Domain.Course;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.CourseType.CourseType;
import jhomt.com.studytimeapi.Domain.Forum.Forum;
import jhomt.com.studytimeapi.Domain.StudentCourse.StudentCourse;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type", nullable = false)
    private CourseType type;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Forum> forums;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Unit> units;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentCourse> studentCourses;

    public Course(DataRegisterCourse registerCourse) {
        this.title = registerCourse.title();
        this.description = registerCourse.description();
    }

    public void update(DataUpdateCourse updateCourse) {
        EntityUpdater.updateIfNotNull(updateCourse.title(), this::setTitle);
        EntityUpdater.updateIfNotNull(updateCourse.description(), this::setDescription);
    }

    public void changeStatus(){
        this.status = !this.status;
    }
}