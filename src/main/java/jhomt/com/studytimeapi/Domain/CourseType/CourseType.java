package jhomt.com.studytimeapi.Domain.CourseType;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Course.Course;
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
@Table(name = "course_types")
public class CourseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;

    public CourseType(DataRegisterCourseType courseType) {
        this.name = courseType.name();
        this.description = courseType.description();
    }

    public void update(DataUpdateCourseType courseType) {
        EntityUpdater.updateIfNotNull(courseType.name(), this::setName);
        EntityUpdater.updateIfNotNull(courseType.description(), this::setDescription);
    }
}