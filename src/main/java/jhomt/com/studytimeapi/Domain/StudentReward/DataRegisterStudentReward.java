package jhomt.com.studytimeapi.Domain.StudentReward;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DataRegisterStudentReward(
        @NotNull(message = "El ID del estudiante no puede ser nulo")
        @Min(value = 1, message = "El ID del estudiante debe ser un número positivo mayor que 0")
        Integer studentId,

        @NotNull(message = "El ID de la recompensa no puede ser nulo")
        @Min(value = 1, message = "El ID de la recompensa debe ser un número positivo mayor que 0")
        Integer rewardId,

        @NotNull(message = "La fecha de obtención no puede ser nula")
        LocalDate earnedDate
) {
}
