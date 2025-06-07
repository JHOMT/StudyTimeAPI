package jhomt.com.studytimeapi.Domain.UnitMaterial;

import jhomt.com.studytimeapi.Domain.Core.Utils.DateUtils;

public record DataListUnitMaterial(
        Integer id,
        Integer unitId,
        MaterialType materialType,
        String materialName,
        String uploadedAt
) {
    public DataListUnitMaterial(UnitMaterial unitMaterial) {
        this(
                unitMaterial.getId(),
                unitMaterial.getUnit() != null ? unitMaterial.getUnit().getId() : null,
                unitMaterial.getMaterialType(),
                unitMaterial.getMaterialName(),
                DateUtils.formatDate(unitMaterial.getUploadedAt())
        );
    }
}
