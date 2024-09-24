package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Certification;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

}
