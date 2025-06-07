package jhomt.com.studytimeapi.Domain.Task;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record DataRegisterTask(
        @Min(value = 1, message = "El ID de la unidad debe ser un número positivo mayor que 0")
        Integer unitId,

        @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
        String title,

        @NotNull(message = "La descripción no puede ser nula")
        String description,

        @NotNull(message = "El tipo de tarea no puede ser nulo")
        TaskType typeTask,

        @NotNull(message = "La fecha de vencimiento no puede ser nula")
        LocalDate dueDate,

        @NotNull(message = "Los puntos no pueden ser nulos")
        @Min(value = 1, message = "Los puntos deben ser un número positivo mayor que 0")
        Integer points
) {
    public DataRegisterTask {
        if (unitId == null && title.isEmpty()) {
            throw new RuntimeException("El ID de la unidad y el título no pueden ser nulos al mismo tiempo");
        }
    }
}
