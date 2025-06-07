package jhomt.com.studytimeapi.Domain.Task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record DataUpdateTask(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        Integer unitId,
        String title,
        String description,
        TaskType typeTask,
        LocalDate dueDate,
        Integer points
) {
}
