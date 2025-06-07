package jhomt.com.studytimeapi.Domain.Task;

import jakarta.persistence.*;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.StudentTask.StudentTask;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "title")
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type_task", nullable = false)
    private TaskType typeTask;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "points")
    private Integer points = 0;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentTask> studentTasks;

    public Task(DataRegisterTask registerTask) {
        this.title = registerTask.title();
        this.description = registerTask.description();
        this.typeTask = registerTask.typeTask();
        this.dueDate = registerTask.dueDate();
        this.points = registerTask.points();
    }

    public void update(DataUpdateTask updateTask) {
        EntityUpdater.updateIfNotNull(updateTask.title(), this::setTitle);
        EntityUpdater.updateIfNotNull(updateTask.description(), this::setDescription);
        EntityUpdater.updateIfNotNull(updateTask.typeTask(), this::setTypeTask);
        EntityUpdater.updateIfNotNull(updateTask.dueDate(), this::setDueDate);
        EntityUpdater.updateIfNotNull(updateTask.points(), this::setPoints);
    }

}