package jhomt.com.studytimeapi.Domain.UnitMaterial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DataRegisterUnitMaterial(
        @NotNull(message = "El ID de la unidad no puede ser nulo")
        Integer unitId,

        @NotNull(message = "El tipo de material no puede ser nulo")
        MaterialType materialType,

        @NotNull(message = "El nombre del material no puede ser nulo")
        @Size(max = 255, message = "El nombre del material no puede exceder los 255 caracteres")
        String materialName,

        @NotNull(message = "La descripción del material no puede ser nula")
        @Size(max = 255, message = "La descripción del material no puede exceder los 255 caracteres")
        String materialDescription,

        @NotNull(message = "El contenido del material (base64) no puede ser nulo")
        String materialBase64
) {
}
