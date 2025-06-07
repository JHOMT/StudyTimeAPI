package jhomt.com.studytimeapi.Domain.Unit;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisterUnit(
        @NotNull(message = "El ID del curso no puede ser nulo")
        @Min(value = 1, message = "El ID del curso debe ser un número positivo mayor que 0")
        Integer courseId,

        @NotNull(message = "El título no puede ser nulo")
        @Size(max = 255, message = "El título no puede exceder 255 caracteres")
        String title,

        @NotNull(message = "La descripción no puede ser nula")
        String description
) {
}