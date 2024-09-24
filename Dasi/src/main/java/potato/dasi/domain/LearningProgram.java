package potato.dasi.domain;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class LearningProgram {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String source;
	private String title;
	private String organization;
	private Date applicationStart;
	private Date applicationEnd;
	private Date progressStart;
	private Date progressEnd;
	private String situation;
	private String phone;
	private String manager;
	private String charge;
	private String email;
	private String link;
	private String details;
	private String instructor;
	private String tuition;
	private String teachingMethod;
	private String capacity;
	private String place;
	private String support;
	private String viewDetailsLink;
	private String preferenceType;
	
	@ManyToOne
	@JoinColumn(name = "preference_id")
	private Preference preference;
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
	
	@OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApplyMethod> applyMethod;
}
