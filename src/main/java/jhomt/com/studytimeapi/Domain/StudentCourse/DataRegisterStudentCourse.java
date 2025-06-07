package jhomt.com.studytimeapi.Domain.StudentCourse;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataRegisterStudentCourse(
        @NotNull(message = "El ID del estudiante no puede ser nulo")
        @Min(value = 1, message = "El ID del estudiante debe ser un número positivo mayor que 0")
        Integer studentId,

        @NotNull(message = "El ID del curso no puede ser nulo")
        @Min(value = 1, message = "El ID del curso debe ser un número positivo mayor que 0")
        Integer courseId
) {
}