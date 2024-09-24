package potato.dasi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import potato.dasi.persistence.LearningProgramRepository;

@SpringBootTest
public class EducationTest {
	@Autowired
	private LearningProgramRepository learningProgramRepo;
	
	@Test
	public void getLearingInfo() {
		System.out.println(learningProgramRepo.findById(2L));
	}
}
