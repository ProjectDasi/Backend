package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.PreferenceType;

public interface PreferenceTypeRepository extends JpaRepository<PreferenceType, Long> {

}
