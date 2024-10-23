package potato.dasi.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String source;
	
	@Column(nullable = false)
	private String company;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Date signupDate;
	
	@Column(nullable = false)
	private Date dueDate;
	
	private String subtitle;
	private String salary;
	private String regionName;
	private String career;
	private String education;
	private String workType;
	private String workCategory;
	private String link;
	private String contact;
	private String workHours;
	private String details;
	private String email;
	private String certification;
	private String preferenceType;
	private Long views;
	private String preferredQualifications;
	
	@ManyToOne
	@JoinColumn(name = "preference_id")
	private Preference preference;
	
	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;
}
