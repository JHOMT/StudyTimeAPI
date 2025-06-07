package jhomt.com.studytimeapi.Domain.StudentMedal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Medal.Medal;
import jhomt.com.studytimeapi.Domain.Student.Student;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_medals")
public class StudentMedal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medal_id", nullable = false)
    private Medal medal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(name = "earned_date", nullable = false)
    private LocalDate earnedDate;

    public StudentMedal(DataRegisterStudentMedal registerStudentMedal) {
        this.earnedDate = registerStudentMedal.earnedDate();
    }

    public void update(DataUpdateStudentMedal updateStudentMedal) {
        EntityUpdater.updateIfNotNull(updateStudentMedal.earnedDate(), this::setEarnedDate);
    }

}