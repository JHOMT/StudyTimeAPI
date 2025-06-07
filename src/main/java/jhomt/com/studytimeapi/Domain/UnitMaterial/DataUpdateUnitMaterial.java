package jhomt.com.studytimeapi.Domain.UnitMaterial;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jhomt.com.studytimeapi.Domain.Core.Utils.Base64ValidatorUtils;

public record DataUpdateUnitMaterial(
        @NotNull(message = "El ID no puede ser nulo")
        @Min(value = 1, message = "El ID debe ser un número positivo mayor que 0")
        Integer id,

        Integer unitId,
        MaterialType materialType,
        String materialName,
        String materialDescription,
        String materialBase64
) {
    public DataUpdateUnitMaterial {
        if (materialType != null || materialBase64 != null) {
            validateMaterialBase64(materialType, materialBase64);
        }
    }

    private void validateMaterialBase64(MaterialType materialType, String materialBase64) {
        if (materialType == MaterialType.VIDEO || materialType == MaterialType.AUDIO) {
            if (materialBase64 == null || materialBase64.isBlank()) {
                throw new IllegalArgumentException("El contenido del material (base64) no puede ser nulo o vacío para videos y audios");
            }
            if (!Base64ValidatorUtils.isValidBase64ForType(materialBase64, materialType)) {
                throw new IllegalArgumentException("El contenido base64 no corresponde al tipo de material esperado");
            }
        } else if (materialType == MaterialType.IMAGE || materialType == MaterialType.DOC) {
            if (materialBase64 != null && !materialBase64.isBlank()) {
                throw new IllegalArgumentException("El contenido del material (base64) debe ser nulo o vacío para textos e imágenes");
            }
        }
    }
}