package jhomt.com.studytimeapi.Domain.Course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

public record DataRegisterCourse(
        @NotNull(message = "Nombre de curso no puede ser nulo")
        @Size(min = 1, max = 255, message = "El nombre del curso debe tener entre 1 y 255 caracteres")
        String title,

        @NotNull(message = "Descripción del curso no puede ser nula")
        @Size(min = 1, max = 1000, message = "La descripción del curso debe tener entre 1 y 1000 caracteres")
        String description,

        @NotNull(message = "Tipo de curso no puede ser nulo")
        @Min(value = 1, message = "El tipo de curso debe ser un número positivo mayor que 0")
        Integer typeId
) {
}