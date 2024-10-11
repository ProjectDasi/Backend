package potato.dasi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.JobLikes;

public interface JobLikesRepository extends JpaRepository<JobLikes, Long> {

}
