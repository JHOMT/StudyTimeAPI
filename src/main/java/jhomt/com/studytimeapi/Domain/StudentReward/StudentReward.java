package jhomt.com.studytimeapi.Domain.StudentReward;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Reward.Reward;
import jhomt.com.studytimeapi.Domain.Student.Student;
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
@Table(name = "student_rewards")
public class StudentReward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reward_id", nullable = false)
    private Reward reward;

    @Column(name = "earned_date", nullable = false)
    private LocalDate earnedDate;

    public StudentReward(DataRegisterStudentReward registerStudentReward) {
        this.earnedDate = registerStudentReward.earnedDate();
    }

    public void update(DataUpdateStudentReward updateStudentReward) {
        EntityUpdater.updateIfNotNull(updateStudentReward.earnedDate(), this::setEarnedDate);
    }
}