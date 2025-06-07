package jhomt.com.studytimeapi.Domain.StudentTask;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record DataUpdateStudentTask(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        Integer studentId,
        Integer taskId,
        LocalDate completionDate,
        Importance importance,
        Boolean isCompleted,
        Integer pointsAwarded
) {
}
