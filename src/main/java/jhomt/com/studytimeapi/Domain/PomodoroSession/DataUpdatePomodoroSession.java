package jhomt.com.studytimeapi.Domain.PomodoroSession;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataUpdatePomodoroSession(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        Integer studentId,
        LocalDate sessionDate,
        LocalTime sessionStartTime,
        LocalTime sessionEndTime
) {
    public DataUpdatePomodoroSession {
        if (sessionStartTime != null && sessionEndTime != null && !sessionEndTime.isAfter(sessionStartTime)) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la hora de inicio");
        }
    }
}
