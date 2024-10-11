package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.LearningLikes;

public interface LearningLikesRepository extends JpaRepository<LearningLikes, Long> {

}
