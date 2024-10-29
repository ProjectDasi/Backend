package potato.dasi.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import potato.dasi.domain.LearningProgram;

public interface LearningProgramRepository extends JpaRepository<LearningProgram, Long> {
	Page<LearningProgram> findAll(Pageable pageable);

//	@Query("SELECT l FROM LearningProgram l WHERE " + ":searchKeyword IS NULL OR "
//			+ "(l.title LIKE %:searchKeyword% OR " + "l.details LIKE %:searchKeyword%)")
//	Page<LearningProgram> searchLearningPrograms(@Param("searchKeyword") String searchKeyword, Pageable pageable);

	@Query("SELECT l FROM LearningProgram l WHERE " + 
			"(:regionId IS NULL OR l.region.id = :regionId) AND " +
			"(:searchKeyword IS NULL OR " + 
			"(l.title LIKE %:searchKeyword% OR " + 
			"l.details LIKE %:searchKeyword%))")
	Page<LearningProgram> searchLearningPrograms(@Param("regionId") Long regionId, 
									  @Param("searchKeyword") String searchKeyword,
									  Pageable pageable);

}
