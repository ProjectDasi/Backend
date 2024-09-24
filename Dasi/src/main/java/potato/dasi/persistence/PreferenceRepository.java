package potato.dasi.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import potato.dasi.domain.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
	List<Preference> findAllByMemberId(Long memberId);

	void deleteAllByMemberId(Long id);
}
