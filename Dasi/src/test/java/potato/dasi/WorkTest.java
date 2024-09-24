package potato.dasi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import potato.dasi.domain.Work;
import potato.dasi.persistence.WorkRepository;

@SpringBootTest
public class WorkTest {
	@Autowired
	private WorkRepository workRepository;
	
	@Test
	public void getWorkList() {
		List<Work> workList = workRepository.findAll();
		
		for(Work work : workList) {
			System.out.println(work.getId() + ") " + work.getTitle());
		}
	}
}
