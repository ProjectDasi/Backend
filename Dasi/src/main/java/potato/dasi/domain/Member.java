package potato.dasi.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Pattern(regexp = "^(01[016789]|0[2-9]\\d{0,1})\\d{3,4}\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
	private String phone;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

	@Column(nullable = false)
	private String loginId;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	@Column(nullable = false)
	private Role role = Role.ROLE_MEMBER;

	@Temporal(value = TemporalType.DATE)
	private Date birth;

	@Builder.Default
	private boolean isAvailable = true;

	@Min(1)
	@Max(3)
	private Long computerAbility;
	
	private boolean workAbility;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Preference> preferenceList;
}
