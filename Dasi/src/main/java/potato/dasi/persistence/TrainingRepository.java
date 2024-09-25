package potato.dasi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Training;
import potato.dasi.domain.WorkExperience;

public interface TrainingRepository extends JpaRepository<Training, Long> {
	List<Training> findByResumeId(Long id);
}
