package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
