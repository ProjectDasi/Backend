package potato.dasi.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
	Optional<Region> findBySubregion(String region);
}
