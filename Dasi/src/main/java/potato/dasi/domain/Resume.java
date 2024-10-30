package potato.dasi.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class Resume {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	@Temporal(value = TemporalType.DATE)
	private Date birthDate;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateDate;
	
	// @Column(nullable=false)처리?
	private String name;	
	private String address;
	private String phone;	// 전화번호 형식 지정?
	private String email;	// 이메일 형식 지정?
	private String emergencyContact;
	private String emergencyRelationship;
	
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Education> educationList;
	
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Certification> certificationList;
	
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Training> trainingList;
	
	@OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WorkExperience> workExperienceList;
}
