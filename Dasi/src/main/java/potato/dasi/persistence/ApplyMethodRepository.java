package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.ApplyMethod;

public interface ApplyMethodRepository extends JpaRepository<ApplyMethod, Long> {

}
