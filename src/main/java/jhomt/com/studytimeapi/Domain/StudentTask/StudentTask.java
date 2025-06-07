package jhomt.com.studytimeapi.Domain.StudentTask;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Student.Student;
import jhomt.com.studytimeapi.Domain.Task.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.engine.spi.EntityUniqueKey;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_tasks")
public class StudentTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "importance", nullable = false)
    private Importance importance;

    @Column(name = "is_completed")
    private Boolean isCompleted = false;

    @Column(name = "points_awarded")
    private Integer pointsAwarded = 0;


    @OneToMany(mappedBy = "studentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;


    public StudentTask(DataRegisterStudentTask registerStudentTask){
        this.importance = registerStudentTask.importance();
    }

    public void update(DataUpdateStudentTask updateStudentTask) {
        EntityUpdater.updateIfNotNull(updateStudentTask.importance(), this::setImportance);
        EntityUpdater.updateIfNotNull(updateStudentTask.isCompleted(), this::setIsCompleted);
        EntityUpdater.updateIfNotNull(updateStudentTask.completionDate(), this::setCompletionDate);
        EntityUpdater.updateIfNotNull(updateStudentTask.pointsAwarded(), this::setPointsAwarded);
    }

}