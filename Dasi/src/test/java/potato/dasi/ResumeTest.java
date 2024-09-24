package potato.dasi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import potato.dasi.domain.Certification;
import potato.dasi.domain.Education;
import potato.dasi.domain.Resume;
import potato.dasi.domain.Training;
import potato.dasi.domain.WorkExperience;
import potato.dasi.persistence.ResumeRepository;

@SpringBootTest
public class ResumeTest {
	@Autowired
	private ResumeRepository resumeRepository;
	
	@Test
	public void getResume() {
		List<Resume> resumeList = resumeRepository.findAll();
		
		for(Resume resume : resumeList) {
			System.out.println(resume.toString());
			
			for(Certification cert : resume.getCertificationList()) {
				System.out.println(cert.toString());
			}
			
			for(Education edu : resume.getEducationList()) {
				System.out.println(edu.toString());
			}
			
			for(Training train : resume.getTrainingList()) {
				System.out.println(train.toString());
			}
			
			for(WorkExperience workExp : resume.getWorkExperienceList()) {
				System.out.println(workExp.toString());
			}
			
			System.out.println("-".repeat(30));
		}
	}
}
