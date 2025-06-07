package jhomt.com.studytimeapi.Domain.UnitMaterial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jhomt.com.studytimeapi.Domain.Core.Utils.Base64ValidatorUtils;

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

        public DataRegisterUnitMaterial {
                validateMaterialBase64(materialType, materialBase64);
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
                                throw new IllegalArgumentException("El contenido del material (base64) debe ser nulo o vacío para imágenes y documentos");
                        }
                } else {
                        throw new IllegalArgumentException("Tipo de material no soportado");
                }
        }
}