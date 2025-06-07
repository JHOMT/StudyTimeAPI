package jhomt.com.studytimeapi.Domain.StudentForum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisterStudentForum(
        @NotNull(message = "El ID del estudiante no puede ser nulo")
        @Min(value = 1, message = "El ID del estudiante debe ser un número positivo mayor que 0")
        Integer studentId,

        @NotNull(message = "El ID del foro no puede ser nulo")
        @Min(value = 1, message = "El ID del foro debe ser un número positivo mayor que 0")
        Integer forumId,

        @NotNull(message = "La respuesta no puede ser nula")
        @Size(min = 1, message = "La respuesta no puede estar vacía")
        String response
) {
}
