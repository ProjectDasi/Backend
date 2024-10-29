package potato.dasi.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import potato.dasi.util.MultiDateDeserializer;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class WorkExperience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "resume_id")
	@ToString.Exclude
	private Resume resume;
	
	@Temporal(value = TemporalType.DATE)
	@JsonDeserialize(using = MultiDateDeserializer.class)
	private Date workStart;

	@Temporal(value = TemporalType.DATE)
	@JsonDeserialize(using = MultiDateDeserializer.class)
	private Date workEnd;
	
	private String company;
	private String workDescription;
}
