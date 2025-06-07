package jhomt.com.studytimeapi.Domain.StudentMedal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DataUpdateStudentMedal(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        Integer studentId,
        Integer medalId,
        Integer unitId,
        LocalDate earnedDate
) {
}
