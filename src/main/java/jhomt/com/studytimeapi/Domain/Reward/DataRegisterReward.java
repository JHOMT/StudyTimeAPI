package jhomt.com.studytimeapi.Domain.Reward;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisterReward(
        @NotNull(message = "El nombre no puede ser nulo")
        @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        String name,

        @NotNull(message = "Los puntos necesarios no pueden ser nulos")
        @Min(value = 1, message = "Los puntos necesarios deben ser un número positivo mayor que 0")
        Integer pointsNeeded
) {
}
