package potato.dasi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Certification;
import potato.dasi.domain.WorkExperience;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
	List<Certification> findByResumeId(Long id);
}
