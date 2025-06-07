package jhomt.com.studytimeapi.Domain.StudentMedal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DataRegisterStudentMedal(
        @NotNull(message = "El ID del estudiante no puede ser nulo")
        @Min(value = 1, message = "El ID del estudiante debe ser un número positivo mayor que 0")
        Integer studentId,

        @NotNull(message = "El ID de la medalla no puede ser nulo")
        @Min(value = 1, message = "El ID de la medalla debe ser un número positivo mayor que 0")
        Integer medalId,

        @NotNull(message = "El ID de la unidad no puede ser nulo")
        @Min(value = 1, message = "El ID de la unidad debe ser un número positivo mayor que 0")
        Integer unitId,

        @NotNull(message = "La fecha de obtención no puede ser nula")
        LocalDate earnedDate
) {
}
