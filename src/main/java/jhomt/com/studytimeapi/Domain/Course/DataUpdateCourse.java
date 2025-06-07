package jhomt.com.studytimeapi.Domain.Course;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataUpdateCourse(

        @NotNull(message = "El id es requerido")
        @Min(value = 1, message = "El id debe ser un número positivo mayor que 0")
        Integer id,

        String title,
        String description,
        Integer typeId
) {
}
