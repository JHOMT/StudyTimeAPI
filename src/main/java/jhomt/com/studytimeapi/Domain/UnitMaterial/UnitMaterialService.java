package jhomt.com.studytimeapi.Domain.UnitMaterial;

import jhomt.com.studytimeapi.Domain.ServiceGlobal.ValidationsIDsGlobalService;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnitMaterialService {

    private final UnitMaterialRepository unitMaterialRepository;
    private final ValidationsIDsGlobalService validationsIDsGlobalService;

    public UnitMaterialService(UnitMaterialRepository unitMaterialRepository, ValidationsIDsGlobalService validationsIDsGlobalService) {
        this.unitMaterialRepository = unitMaterialRepository;
        this.validationsIDsGlobalService = validationsIDsGlobalService;
    }

    @Transactional
    public DataListUnitMaterial registerUnitMaterial(DataRegisterUnitMaterial dataRegisterUnitMaterial) {
        Unit unit = validationsIDsGlobalService.findUnitById(dataRegisterUnitMaterial.unitId());

        UnitMaterial unitMaterial = new UnitMaterial(dataRegisterUnitMaterial);
        unitMaterial.setUnit(unit);

        unitMaterial = unitMaterialRepository.save(unitMaterial);
        return new DataListUnitMaterial(unitMaterial);
    }

    @Transactional
    public DataListUnitMaterial updateUnitMaterial(DataUpdateUnitMaterial dataUpdateUnitMaterial) {
        UnitMaterial unitMaterial = unitMaterialRepository.findById(dataUpdateUnitMaterial.id())
                .orElseThrow(() -> new RuntimeException("Material de la unidad no encontrado"));

        unitMaterial.update(dataUpdateUnitMaterial);

        if (dataUpdateUnitMaterial.unitId() != null) {
            Unit unit = validationsIDsGlobalService.findUnitById(dataUpdateUnitMaterial.unitId());
            unitMaterial.setUnit(unit);
        }

        unitMaterial = unitMaterialRepository.save(unitMaterial);
        return new DataListUnitMaterial(unitMaterial);
    }

    public List<DataListUnitMaterial> listUnitMaterialsByUnitIdAll(Integer unitId) {
        Unit unit = validationsIDsGlobalService.findUnitById(unitId);
        return unit.getUnitMaterials()
                .stream()
                .map(DataListUnitMaterial::new)
                .toList();
    }

    public DataListUnitMaterialInfo listUnitMaterialsByUnitIdInfo(Integer unitMaterialId) {
        UnitMaterial unitMaterial = validationsIDsGlobalService.findUnitMaterialById(unitMaterialId);
        return new DataListUnitMaterialInfo(unitMaterial);
    }
}
