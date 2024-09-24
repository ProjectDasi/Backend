package potato.dasi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import potato.dasi.domain.Faq;
import potato.dasi.persistence.FaqRepository;

@SpringBootTest
public class FaQTest {
	@Autowired
	private FaqRepository faqRepository;
	
	@Test
	public void getAll(){
		List<Faq> faqs = faqRepository.findAll(); 
		
		for(Faq faq : faqs) {
			System.out.println(faq);
		}
	}
}
