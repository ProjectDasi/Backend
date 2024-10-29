package potato.dasi.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import potato.dasi.domain.LearningProgram;
import potato.dasi.domain.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
	Page<Work> findAll(Pageable pageable);
	
	@Query("SELECT w FROM Work w WHERE " +
		       "(:regionId IS NULL OR w.region.id = :regionId) AND " +
		       "(COALESCE(:qualification, false) = false OR w.preferredQualifications IS NOT NULL) AND " +
		       "(:searchKeyword IS NULL OR " +
		       "(w.title LIKE %:searchKeyword% OR " +
		       "w.workCategory LIKE %:searchKeyword% OR " +
		       "w.details LIKE %:searchKeyword%))")
		Page<Work> searchWorks(@Param("regionId") Long regionId,
		                       @Param("searchKeyword") String searchKeyword,
		                       @Param("qualification") boolean qualification,
		                       Pageable pageable);

//	@Query("SELECT w FROM Work w WHERE " +
//			"(:regionId IS NULL OR w.region.id = :regionId) AND " +
////				"(COALESCE(:regionId, w.region.id) = w.region.id) AND " +
//"(:searchKeyword IS NULL OR " +
//"(w.title LIKE %:searchKeyword% OR " +
//"w.workCategory LIKE %:searchKeyword% OR " +
//			"w.details LIKE %:searchKeyword%))")
//	Page<Work> searchWorks(@Param("regionId") Long regionId,
//			@Param("searchKeyword") String searchKeyword,
//			Pageable pageable);
	
	
	Page<Work> findByPreferredQualificationsIsNotNull(Pageable pageable);

}
