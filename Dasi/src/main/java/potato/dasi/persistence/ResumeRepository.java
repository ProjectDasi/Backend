package potato.dasi.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
	Optional<Resume> findByMemberId(Long id);
	boolean existsByMemberId(Long id);
}
