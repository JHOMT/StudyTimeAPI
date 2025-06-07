package jhomt.com.studytimeapi.Domain.Reward;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DataUpdateReward(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        String name,
        Integer pointsNeeded
) {
}
