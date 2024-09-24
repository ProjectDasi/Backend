package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Faq;

public interface FaqRepository extends JpaRepository<Faq, Long> {

}
