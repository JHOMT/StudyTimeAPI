package jhomt.com.studytimeapi.Domain.UnitMaterial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitMaterialRepository extends JpaRepository<UnitMaterial, Integer> {
}
