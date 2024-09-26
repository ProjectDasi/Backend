package potato.dasi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.ResumeReqDTO;
import potato.dasi.persistence.CertificationRepository;
import potato.dasi.persistence.EducationRepository;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.PreferenceRepository;
import potato.dasi.persistence.ResumeRepository;
import potato.dasi.persistence.TrainingRepository;
import potato.dasi.persistence.WorkExperienceRepository;

@Service
@RequiredArgsConstructor
public class ResumeService {
	private final ResumeRepository resumeRepository;
	private final MemberRepository memberRepository;
	private final WorkExperienceRepository workExperienceRepository;
	private final CertificationRepository certificationRepository;
	private final TrainingRepository trainingRepository;
	private final EducationRepository educationRepository;
	private final PreferenceRepository preferenceRepository;
	
	
	public ResumeReqDTO scanResume(ResumeReqDTO resume) {
		// TODO Auto-generated method stub
		return resume;
	}
}
