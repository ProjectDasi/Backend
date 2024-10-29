package potato.dasi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.LearningLikes;

public interface LearningLikesRepository extends JpaRepository<LearningLikes, Long> {
	boolean existsByMemberIdAndLearningProgramId(Long memberId, Long learningId);
	boolean existsByMemberId(Long memberId);
	List<LearningLikes> findByMemberId(Long memberId);
	List<LearningLikes> findByMemberId(Long memberId, PageRequest pageRequest);
	Optional<LearningLikes> findByMemberIdAndLearningProgramId(Long memberId, Long learningId);
	List<LearningLikes> findByMemberIdAndLearningProgramIdIn(Long memberId, List<Long> learningId);
}
