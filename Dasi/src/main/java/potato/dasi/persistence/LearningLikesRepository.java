package potato.dasi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.LearningLikes;

public interface LearningLikesRepository extends JpaRepository<LearningLikes, Long> {
	boolean existsByMemberIdAndLearningProgramId(Long memberId, Long learningId);
	List<LearningLikes> findByMemberId(Long memberId);
	Optional<LearningLikes> findByMemberIdAndLearningProgramId(Long memberId, Long learningId);
	List<LearningLikes> findByMemberIdAndLearningProgramIdIn(Long memberId, List<Long> learningId);
}
