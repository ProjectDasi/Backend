package potato.dasi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.WorkLikes;

public interface WorkLikesRepository extends JpaRepository<WorkLikes, Long> {
	boolean existsByMemberIdAndWorkId(Long memberId, Long workId);
	boolean existsByMemberId(Long memberId);
	List<WorkLikes> findByMemberId(Long memberId);
	List<WorkLikes> findByMemberId(Long memberId, PageRequest pageRequest);
	Optional<WorkLikes> findByMemberIdAndWorkId(Long memberId, Long workId);
	List<WorkLikes> findByMemberIdAndWorkIdIn(Long memberId, List<Long> workId);
}
