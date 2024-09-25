package potato.dasi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long>{
	List<WorkExperience> findByResumeId(Long id);
}
