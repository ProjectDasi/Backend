package potato.dasi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Education;
import potato.dasi.domain.WorkExperience;

public interface EducationRepository extends JpaRepository<Education, Long> {
	List<Education> findByResumeId(Long id);
}
