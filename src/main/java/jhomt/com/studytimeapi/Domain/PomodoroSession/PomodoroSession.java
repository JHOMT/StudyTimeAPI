package jhomt.com.studytimeapi.Domain.PomodoroSession;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.Duration.between;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pomodoro_sessions")
public class PomodoroSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "session_date", nullable = false)
    private LocalDate sessionDate;

    @Column(name = "session_start_time", nullable = false)
    private LocalTime sessionStartTime;

    @Column(name = "session_end_time", nullable = false)
    private LocalTime sessionEndTime;

    @Column(name = "pomodoro_count")
    private Integer pomodoroCount = 0;

    public PomodoroSession(DataRegisterPomodoroSession registerPomodoroSession) {
        this.sessionDate = registerPomodoroSession.sessionDate();
        this.sessionStartTime = registerPomodoroSession.sessionStartTime();
        this.sessionEndTime = registerPomodoroSession.sessionEndTime();
        this.pomodoroCount += getPomodoroCount();
    }

    public void update(DataUpdatePomodoroSession updatePomodoroSession) {
        EntityUpdater.updateIfNotNull(updatePomodoroSession.sessionDate(), this::setSessionDate);
        EntityUpdater.updateIfNotNull(updatePomodoroSession.sessionStartTime(), this::setSessionStartTime);
        EntityUpdater.updateIfNotNull(updatePomodoroSession.sessionEndTime(), this::setSessionEndTime);
    }
    public Integer getSessionDuration() {
        if (sessionStartTime != null && sessionEndTime != null) {
            return (int) between(sessionStartTime, sessionEndTime).toMinutes();
        }
        return 0;
    }

}