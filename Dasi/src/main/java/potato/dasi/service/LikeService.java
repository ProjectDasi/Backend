package potato.dasi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.JobLikes;
import potato.dasi.domain.LearningLikes;
import potato.dasi.domain.LearningProgram;
import potato.dasi.domain.Member;
import potato.dasi.domain.Work;
import potato.dasi.dto.LikeRequest;
import potato.dasi.persistence.JobLikesRepository;
import potato.dasi.persistence.LearningLikesRepository;
import potato.dasi.persistence.LearningProgramRepository;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.WorkRepository;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final WorkRepository workRepository;
	private final LearningProgramRepository learningProgramRepository;
	private final MemberRepository memberRepository;
	private final JobLikesRepository jobLikesRepository;
	private final LearningLikesRepository learningLikesRepository;
	
	public boolean addWorkLike(LikeRequest req) {
		Member member = memberRepository.findById(Long.parseLong(req.getMemberId())).orElse(null);
		if(member == null)
			return false;
		
		Work work = workRepository.findById(Long.parseLong(req.getLikeItemId())).orElse(null);
		if(work == null)
			return false;
		
		JobLikes jobLikes = JobLikes.builder()
				.member(member)
				.work(work)
				.build();
		
		jobLikesRepository.save(jobLikes);
		
		return true;
	}
	
	public boolean addLearningLike(LikeRequest req) {
		Member member = memberRepository.findById(Long.parseLong(req.getMemberId())).orElse(null);
		if(member == null)
			return false;
		
		LearningProgram learning = learningProgramRepository.findById(Long.parseLong(req.getLikeItemId())).orElse(null);
		if(learning == null)
			return false;
		
		LearningLikes learningLikes = LearningLikes.builder()
				.member(member)
				.learningProgram(learning)
				.build();
		
		learningLikesRepository.save(learningLikes);
		
		return true;
	}

}
