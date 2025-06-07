package jhomt.com.studytimeapi.Domain.PomodoroSession;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DataRegisterPomodoroSession(
        @NotNull(message = "El ID del estudiante no puede ser nulo")
        Integer studentId,

        @NotNull(message = "La fecha de la sesión no puede ser nula")
        LocalDate sessionDate,

        @NotNull(message = "La hora de inicio de la sesión no puede ser nula")
        LocalTime sessionStartTime,

        @NotNull(message = "La hora de fin de la sesión no puede ser nula")
        LocalTime sessionEndTime
) {
}
