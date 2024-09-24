package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long>{

}
