package jhomt.com.studytimeapi.Domain.GlobalConfiguration;

import jakarta.persistence.*;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "global_configurations")
public class GlobalConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "use_pomodoro")
    private Boolean usePomodoro = true;

    @Column(name = "enable_notifications")
    private Boolean enableNotifications = true;

    @Column(name = "default_pomodoro_duration")
    private Integer defaultPomodoroDuration = 25;

    @Column(name = "default_break_duration")
    private Integer defaultBreakDuration = 5;

    @Column(name = "show_ranking")
    private Boolean showRanking = true;

    @Column(name = "theme", length = 50)
    private String theme = "light";

    public GlobalConfiguration(Student student) {
        this.student = student;
    }

    public void update(DataUpdateGlobalConfiguration updateGlobalConfiguration) {
        EntityUpdater.updateIfNotNull(updateGlobalConfiguration.usePomodoro(), this::setUsePomodoro);
        EntityUpdater.updateIfNotNull(updateGlobalConfiguration.enableNotifications(), this::setEnableNotifications);
        EntityUpdater.updateIfNotNull(updateGlobalConfiguration.defaultPomodoroDuration(), this::setDefaultPomodoroDuration);
        EntityUpdater.updateIfNotNull(updateGlobalConfiguration.defaultBreakDuration(), this::setDefaultBreakDuration);
        EntityUpdater.updateIfNotNull(updateGlobalConfiguration.showRanking(), this::setShowRanking);
        EntityUpdater.updateIfNotNull(updateGlobalConfiguration.theme(), this::setTheme);
    }
}