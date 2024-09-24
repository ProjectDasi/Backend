package potato.dasi.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import potato.dasi.domain.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
	Page<Work> findAll(Pageable pageable);
	
	@Query("SELECT w FROM Work w WHERE " +
		       "(:regionId IS NULL OR w.region.id = :regionId) AND " +
//				"(COALESCE(:regionId, w.region.id) = w.region.id) AND " +
		       "(:searchKeyword IS NULL OR " +
		       "(w.title LIKE %:searchKeyword% OR " +
		       "w.workCategory LIKE %:searchKeyword% OR " +
		       "w.details LIKE %:searchKeyword%))")
		Page<Work> searchWorks(@Param("regionId") Long regionId,
		                       @Param("searchKeyword") String searchKeyword,
		                       Pageable pageable);

}
