package jhomt.com.studytimeapi.Domain.StudentForum;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Forum.DataRegisterForum;
import jhomt.com.studytimeapi.Domain.Forum.Forum;
import jhomt.com.studytimeapi.Domain.Student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_forums")
public class StudentForum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum;

    @Column(name = "response", nullable = false)
    private String response;

    @Column(name = "points_awarded")
    private Integer pointsAwarded = 0;

    public StudentForum(DataRegisterStudentForum dataRegisterStudentForum) {
        this.response = dataRegisterStudentForum.response();
    }

    public void update(DataUpdateStudentForum dataUpdateStudentForum) {
        EntityUpdater.updateIfNotNull(dataUpdateStudentForum.response(), this::setResponse);
        EntityUpdater.updateIfNotNull(dataUpdateStudentForum.pointsAwarded(), this::setPointsAwarded);
    }

}