package jhomt.com.studytimeapi.Domain.UnitMaterial;

import jhomt.com.studytimeapi.Domain.Core.Utils.DateUtils;

public record DataListUnitMaterialInfo(
        Integer id,
        Integer unitId,
        MaterialType materialType,
        String materialName,
        String materialDescription,
        String materialBase64,
        String uploadedAt
) {
    public DataListUnitMaterialInfo(UnitMaterial unitMaterial) {
        this(
                unitMaterial.getId(),
                unitMaterial.getUnit() != null ? unitMaterial.getUnit().getId() : null,
                unitMaterial.getMaterialType(),
                unitMaterial.getMaterialName(),
                unitMaterial.getMaterialDescription(),
                unitMaterial.getMaterialBase64(),
                DateUtils.formatDate(unitMaterial.getUploadedAt())
        );
    }
}
