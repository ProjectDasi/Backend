package potato.dasi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Faq;
import potato.dasi.persistence.FaqRepository;

@Service
@RequiredArgsConstructor
public class FaQService {
	private final FaqRepository faqRepository;

	public List<Faq> getAllFaQ() {
		List<Faq> faq = faqRepository.findAll();
		return faq;
	}
}
