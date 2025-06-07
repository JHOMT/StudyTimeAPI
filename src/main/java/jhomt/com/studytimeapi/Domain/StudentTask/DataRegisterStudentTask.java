package jhomt.com.studytimeapi.Domain.StudentTask;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record DataRegisterStudentTask(
        @NotNull(message = "El ID del estudiante no puede ser nulo")
        @Min(value = 1, message = "El ID del estudiante debe ser un número positivo mayor que 0")
        Integer studentId,

        @NotNull(message = "El ID de la tarea no puede ser nulo")
        @Min(value = 1, message = "El ID de la tarea debe ser un número positivo mayor que 0")
        Integer taskId,

        @NotNull(message = "La importancia no puede ser nula")
        Importance importance
) {
}
