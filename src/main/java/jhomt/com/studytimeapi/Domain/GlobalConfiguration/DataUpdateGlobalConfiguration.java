package jhomt.com.studytimeapi.Domain.GlobalConfiguration;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataUpdateGlobalConfiguration(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        Integer studentId,
        Boolean usePomodoro,
        Boolean enableNotifications,
        Integer defaultPomodoroDuration,
        Integer defaultBreakDuration,
        Boolean showRanking,
        String theme
) {
}