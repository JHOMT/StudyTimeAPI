package jhomt.com.studytimeapi.Domain.CourseType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisterCourseType(
        @NotNull(message = "El nombre no puede ser nulo")
        @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        String name,

        @NotNull(message = "La descripción no puede ser nula")
        @Size(min = 1, max = 500, message = "La descripción debe tener entre 1 y 500 caracteres")
        String description
) {
}
