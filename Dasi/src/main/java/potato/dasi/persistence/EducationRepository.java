package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Education;

public interface EducationRepository extends JpaRepository<Education, Long> {

}
