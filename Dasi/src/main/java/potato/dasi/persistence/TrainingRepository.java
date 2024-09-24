package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {

}
