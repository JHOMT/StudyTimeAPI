package jhomt.com.studytimeapi.Domain.GlobalConfiguration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalConfigurationRepository extends JpaRepository<GlobalConfiguration, Integer> {
}
