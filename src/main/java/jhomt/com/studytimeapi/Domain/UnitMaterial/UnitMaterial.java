package jhomt.com.studytimeapi.Domain.UnitMaterial;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jhomt.com.studytimeapi.Domain.Core.EntityUpdater;
import jhomt.com.studytimeapi.Domain.Unit.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unit_materials")
public class UnitMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Enumerated(EnumType.STRING)
    @Column(name = "material_type", nullable = false)
    private MaterialType materialType;

    @Column(name = "name", nullable = false)
    private String materialName;

    @Column(name = "description")
    private String materialDescription;

    @Lob
    @Column(name = "material_base64", nullable = false)
    private String materialBase64;

    @Column(name = "uploaded_at")
    private Instant uploadedAt = Instant.now();


    public UnitMaterial(DataRegisterUnitMaterial registerUnitMaterial) {
        this.materialType = registerUnitMaterial.materialType();
        this.materialName = registerUnitMaterial.materialName();
        this.materialDescription = registerUnitMaterial.materialDescription();
        this.materialBase64 = registerUnitMaterial.materialBase64();
    }

    public void update(DataUpdateUnitMaterial updateUnitMaterial) {
        EntityUpdater.updateIfNotNull(updateUnitMaterial.materialType(), this::setMaterialType);
        EntityUpdater.updateIfNotNull(updateUnitMaterial.materialName(), this::setMaterialName);
        EntityUpdater.updateIfNotNull(updateUnitMaterial.materialDescription(), this::setMaterialDescription);
        EntityUpdater.updateIfNotNull(updateUnitMaterial.materialBase64(), this::setMaterialBase64);
    }
}