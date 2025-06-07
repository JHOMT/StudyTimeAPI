package jhomt.com.studytimeapi.Domain.Unit;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Course.Course;
import jhomt.com.studytimeapi.Domain.Task.Task;
import jhomt.com.studytimeapi.Domain.UnitMaterial.UnitMaterial;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "units")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Size(max = 255)
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "completed")
    private Boolean completed = false;

    @OneToMany( mappedBy = "unit",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnitMaterial> unitMaterials;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Unit(DataRegisterUnit registerUnit) {
        this.title = registerUnit.title();
        this.description = registerUnit.description();
    }

    public void update(DataUpdateUnit updateUnit) {
        EntityUpdater.updateIfNotNull(updateUnit.title(), this::setTitle);
        EntityUpdater.updateIfNotNull(updateUnit.description(), this::setDescription);
        EntityUpdater.updateIfNotNull(updateUnit.completed(), this::setCompleted);
    }

}