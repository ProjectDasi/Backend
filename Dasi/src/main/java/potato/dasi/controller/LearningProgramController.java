package potato.dasi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.LearningDetailDTO;
import potato.dasi.dto.LearningListDTO;
import potato.dasi.dto.LearningRecommendDTO;
import potato.dasi.dto.WorkListDTO;
import potato.dasi.dto.WorkRecommendDTO;
import potato.dasi.service.LearningProgramService;

@Controller
@RequiredArgsConstructor
public class LearningProgramController {
	private final LearningProgramService learningProgramService;
	
	@GetMapping("/learning/list")
	public ResponseEntity<?> getLearningListPaging(@RequestParam(defaultValue = "0") int page, 
									@RequestParam(defaultValue = "10") int size){
		Pageable pageable = PageRequest.of(page, size);
		
		Page<LearningListDTO> LearningList = learningProgramService.getLearningListPaging(pageable);
		
		if(LearningList.isEmpty())
			return ResponseEntity.badRequest().body("일자리 목록이 없습니다.");
		else
			return ResponseEntity.ok(LearningList);		
	}
	
	@GetMapping("/learning/detail")
	public ResponseEntity<?> getLearningDetail(@RequestParam String id){
		LearningDetailDTO learningDetail = learningProgramService.getLearningDetail(id);
		
		if(learningDetail == null)
			return ResponseEntity.badRequest().body("존재하지 않는 아이디 입니다.");
		else {
			learningProgramService.updateLearningViews(id);
			return ResponseEntity.ok(learningDetail);		
		}
			
	}
	
	@GetMapping("/learning/recommend")
	public ResponseEntity<?> getRecommand(@RequestParam String id) throws Exception{
		List<LearningRecommendDTO> recommend = learningProgramService.getRecommend(id);
		
		if(recommend == null)
			return ResponseEntity.badRequest().body("문제가 발생하였습니다.");
		else
			return ResponseEntity.ok(recommend);		
	}
	
	@GetMapping("/learning/search")
	public ResponseEntity<?> searchLearningkList(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "전체") String region,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = true, defaultValue = "false") boolean popularity){
		Pageable pageable = PageRequest.of(page, size);
        Page<LearningListDTO> learningList = learningProgramService.searchLearningList(pageable, region, keyword, popularity);
		
        if(learningList.isEmpty())
			return ResponseEntity.badRequest().body("교육 목록이 없습니다.");
		else
			return ResponseEntity.ok(learningList);	
	}
	
//	@GetMapping("/learning/search")
//	public ResponseEntity<?> searchLearningkList(@RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(required = false, defaultValue = "전체") String region,
//            @RequestParam(required = false) String keyword){
//		Pageable pageable = PageRequest.of(page, size);
//        Page<LearningListDTO> learningList = learningProgramService.searchLearningList(pageable, region, keyword);
//		
//        if(learningList.isEmpty())
//			return ResponseEntity.badRequest().body("교육 목록이 없습니다.");
//		else
//			return ResponseEntity.ok(learningList);	
//	}
	
	@GetMapping("/learning/popularity")
	public ResponseEntity<?> searchPopularLearningList(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
		Pageable pageable = PageRequest.of(page, size);
        Page<LearningListDTO> learningList = learningProgramService.searchPopularLearningList(pageable);
		
        if(learningList.isEmpty())
			return ResponseEntity.badRequest().body("교육 목록이 없습니다.");
		else
			return ResponseEntity.ok(learningList);	
	}
}
