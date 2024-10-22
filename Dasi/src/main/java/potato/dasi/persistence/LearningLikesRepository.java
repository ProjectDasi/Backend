package potato.dasi.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.LearningLikes;
import potato.dasi.domain.WorkLikes;

public interface LearningLikesRepository extends JpaRepository<LearningLikes, Long> {
	boolean existsByMemberIdAndLearningProgramId(Long memberId, Long learningId);
	List<LearningLikes> findByMemberId(Long memberId);
}
