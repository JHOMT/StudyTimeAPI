package jhomt.com.studytimeapi.Domain.StudentForum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataUpdateStudentForum(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        Integer studentId,
        Integer forumId,
        String response,
        Integer pointsAwarded
) {
}