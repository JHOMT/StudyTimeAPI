package jhomt.com.studytimeapi.Domain.PomodoroSession;

import jhomt.com.studytimeapi.Domain.Core.Utils.DateUtils;

public record DataListPomodoroSession(
        Integer id,
        Integer studentId,
        String studentName,
        String sessionDate,
        String sessionStartTime,
        String sessionEndTime,
        Integer sessionDuration,
        Integer pomodoroCount
) {
    public DataListPomodoroSession(PomodoroSession pomodoroSession) {
        this(
                pomodoroSession.getId(),
                pomodoroSession.getStudent().getId(),
                pomodoroSession.getStudent().getName(),
                DateUtils.formatDate(pomodoroSession.getSessionDate()),
                DateUtils.formatTime(pomodoroSession.getSessionStartTime()),
                DateUtils.formatTime(pomodoroSession.getSessionEndTime()),
                pomodoroSession.getSessionDuration(),
                pomodoroSession.getPomodoroCount()
        );
    }
}
