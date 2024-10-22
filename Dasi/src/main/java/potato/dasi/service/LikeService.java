package potato.dasi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.WorkLikes;
import potato.dasi.domain.LearningLikes;
import potato.dasi.domain.LearningProgram;
import potato.dasi.domain.Member;
import potato.dasi.domain.Work;
import potato.dasi.dto.LikeRequest;
import potato.dasi.persistence.WorkLikesRepository;
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
	private final WorkLikesRepository workLikesRepository;
	private final LearningLikesRepository learningLikesRepository;
	
	public boolean addWorkLike(LikeRequest req) {
		Member member = memberRepository.findById(req.getMemberId()).orElse(null);
		if(member == null)
			return false;
		
		Work work = workRepository.findById(req.getLikeItemId()).orElse(null);
		if(work == null)
			return false;
		
		WorkLikes workLikes = WorkLikes.builder()
				.member(member)
				.work(work)
				.build();
		
		workLikesRepository.save(workLikes);
		
		return true;
	}
	
	public boolean addLearningLike(LikeRequest req) {
		Member member = memberRepository.findById(req.getMemberId()).orElse(null);
		if(member == null)
			return false;
		
		LearningProgram learning = learningProgramRepository.findById(req.getLikeItemId()).orElse(null);
		if(learning == null)
			return false;
		
		LearningLikes learningLikes = LearningLikes.builder()
				.member(member)
				.learningProgram(learning)
				.build();
		
		learningLikesRepository.save(learningLikes);
		
		return true;
	}

	public boolean getWorkLikeExisted(Long id, Long itemId) {
		boolean isExisted = workLikesRepository.existsByMemberIdAndWorkId(id, itemId);
		return isExisted;
	}
	
	public boolean getLearningLikeExisted(Long id, Long itemId) {
		boolean isExisted = learningLikesRepository.existsByMemberIdAndLearningProgramId(id, itemId);
		return isExisted;
	}

	public List<WorkLikes> getWorkLikedList(Long id) {		
		Member member = memberRepository.findById(id).orElse(null);
		if(member == null)
			return null;
		
		List<WorkLikes> workLikedList = workLikesRepository.findByMemberId(id);
		return workLikedList;
	}

	public List<LearningLikes> getLearningLikedList(Long id) {
		Member member = memberRepository.findById(id).orElse(null);
		if(member == null)
			return null;
		
		List<LearningLikes> learningLikedList = learningLikesRepository.findByMemberId(id);
		return learningLikedList;
	}

}
