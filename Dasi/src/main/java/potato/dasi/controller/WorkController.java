package potato.dasi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.WorkDetailDTO;
import potato.dasi.dto.WorkListDTO;
import potato.dasi.dto.WorkRecommendDTO;
import potato.dasi.service.WorkService;

@RequiredArgsConstructor
@Controller
public class WorkController {
	private final WorkService workService;
	
//	@CrossOrigin(origins = "http://192.168.0.23:3000")
	@GetMapping("/work/list")
	public ResponseEntity<?> getWorkListPaging(@RequestParam(defaultValue = "0") int page, 
									@RequestParam(defaultValue = "10") int size){
		System.out.println("일자리 요청");
		Pageable pageable = PageRequest.of(page, size);
		
		Page<WorkListDTO> workList = workService.getWorkListPaging(pageable);
		
		if(workList.isEmpty())
			return ResponseEntity.badRequest().body("일자리 목록이 없습니다.");
		else
			return ResponseEntity.ok(workList);		
	}
	
	@GetMapping("/work/detail")
	public ResponseEntity<?> getWorkDetail(@RequestParam String id){
		WorkDetailDTO workDetail = workService.getWorkDetail(id);
		
		if(workDetail == null)
			return ResponseEntity.badRequest().body("존재하지 않는 아이디 입니다.");
		else
			return ResponseEntity.ok(workDetail);		
	}
	
	@GetMapping("/work/recommend")
	public ResponseEntity<?> getRecommend(@RequestParam String id) throws Exception{
		List<WorkRecommendDTO> recommend = workService.getRecommend(id);
		
		if(recommend == null)
			return ResponseEntity.badRequest().body("문제가 발생하였습니다.");
		else
			return ResponseEntity.ok(recommend);		
	}
	
	@GetMapping("/work/search")
	public ResponseEntity<?> searchWorkList(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "전체") String region,
            @RequestParam(required = false) String keyword){
		Pageable pageable = PageRequest.of(page, size);
        Page<WorkListDTO> workList = workService.searchWorkList(pageable, region, keyword);
		
        if(workList.isEmpty())
			return ResponseEntity.badRequest().body("일자리 목록이 없습니다.");
		else
			return ResponseEntity.ok(workList);	
	}
}
