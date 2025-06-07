package jhomt.com.studytimeapi.Domain.Forum;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataUpdateForum(
        @NotNull(message = "El ID del foro no puede ser nulo")
        @Min(value = 1, message = "El ID del foro debe ser un número positivo mayor que 0")
        Integer id,

        String title,
        String description,
        Integer courseId
) {
}
