package potato.dasi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import potato.dasi.dto.LikeRequest;
import potato.dasi.service.LikeService;

@RestController
@RequiredArgsConstructor
public class LikeController {
	private final LikeService likeService;
	
	@PostMapping("/like/work/add")
	public ResponseEntity<?> addWorkLike(@RequestBody LikeRequest req){
		boolean result = likeService.addWorkLike(req);
		
		if(result)
			return ResponseEntity.ok("찜목록에 추가되었습니다.");
		else
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
	}
	
	@PostMapping("/like/learning/add")
	public ResponseEntity<?> addLearningLike(@RequestBody LikeRequest req){
		boolean result = likeService.addLearningLike(req);
		
		if(result)
			return ResponseEntity.ok("찜목록에 추가되었습니다.");
		else
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
	}
}
