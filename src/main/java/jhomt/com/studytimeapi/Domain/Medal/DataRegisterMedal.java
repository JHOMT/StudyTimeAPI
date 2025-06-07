package jhomt.com.studytimeapi.Domain.Medal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisterMedal(
        @NotNull(message = "El nombre no puede ser nulo")
        @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        String name,

        @NotNull(message = "La descripción no puede ser nula")
        @Size(min = 1, max = 500, message = "La descripción debe tener entre 1 y 500 caracteres")
        String description,

        @NotNull(message = "Los criterios no pueden ser nulos")
        @Size(min = 1, max = 1000, message = "Los criterios deben tener entre 1 y 1000 caracteres")
        String criteria
) {
}
