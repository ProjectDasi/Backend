package potato.dasi.controller;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.LearningLikes;
import potato.dasi.domain.WorkLikes;
import potato.dasi.dto.DeleteReqDTO;
import potato.dasi.dto.LikeRequest;
import potato.dasi.service.LikeService;

@RestController
@RequiredArgsConstructor
public class LikeController {
	private final LikeService likeService;

	@PostMapping("/like/work/add")
	public ResponseEntity<?> addWorkLike(@RequestBody LikeRequest req) {
		boolean result = likeService.addWorkLike(req);

		if (result)
			return ResponseEntity.ok("찜목록에 추가되었습니다.");
		else
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
	}

	@PostMapping("/like/learning/add")
	public ResponseEntity<?> addLearningLike(@RequestBody LikeRequest req) {
		boolean result = likeService.addLearningLike(req);

		if (result)
			return ResponseEntity.ok("찜목록에 추가되었습니다.");
		else
			return ResponseEntity.badRequest().body("잘못된 요청입니다.");
	}

	@GetMapping("/like/work/{id}/{itemId}")
	public ResponseEntity<?> getWorkLikeExisted(@PathVariable Long id, @PathVariable Long itemId) {
		boolean result = likeService.getWorkLikeExisted(id, itemId);

		return ResponseEntity.ok(result);
	}

	@GetMapping("/like/learning/{id}/{itemId}")
	public ResponseEntity<?> getLearningLikeExisted(@PathVariable Long id, @PathVariable Long itemId) {
		boolean result = likeService.getLearningLikeExisted(id, itemId);

		return ResponseEntity.ok(result);
	}

	@GetMapping("/like/work/{id}")
	public ResponseEntity<?> getWorkLikedList(@PathVariable Long id) {
		List<WorkLikes> result = likeService.getWorkLikedList(id);

		if (result == null)
			return ResponseEntity.badRequest().body("잘못된 요청 입니다");
		else
			return ResponseEntity.ok(result);
	}

	@GetMapping("/like/learning/{id}")
	public ResponseEntity<?> getLearningLikedList(@PathVariable Long id) {
		List<LearningLikes> result = likeService.getLearningLikedList(id);

		if (result == null)
			return ResponseEntity.badRequest().body("잘못된 요청 입니다");
		else
			return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/like/work/{id}/{itemId}")
	public ResponseEntity<?> deleteWorkLike(@PathVariable Long id, @PathVariable Long itemId) {
		boolean result = likeService.deleteWorkLike(id, itemId);

		if(result)
			return ResponseEntity.ok("삭제 성공");
		else
			return ResponseEntity.badRequest().body("삭제 실패");
	}
	
	@DeleteMapping("/like/learning/{id}/{itemId}")
	public ResponseEntity<?> deleteLearningLike(@PathVariable Long id, @PathVariable Long itemId) {
		boolean result = likeService.deleteLearningLike(id, itemId);

		if(result)
			return ResponseEntity.ok("삭제 성공");
		else
			return ResponseEntity.badRequest().body("삭제 실패");
	}

	@DeleteMapping("/like/work/delete/{id}")	// 여러개 삭제
	public ResponseEntity<?> deleteWorkLikeList(@PathVariable Long id, @RequestBody DeleteReqDTO deleteDTO) {
		boolean result = likeService.deleteWorkLikeList(id, deleteDTO);

		if(result)
			return ResponseEntity.ok("삭제 성공");
		else
			return ResponseEntity.badRequest().body("삭제 실패");
	}
	
	@DeleteMapping("/like/learning/delete/{id}")	// 여러개 삭제
	public ResponseEntity<?> deleteLearningLikeList(@PathVariable Long id, @RequestBody DeleteReqDTO deleteDTO) {
		boolean result = likeService.deleteLearningLikeList(id, deleteDTO);

		if(result)
			return ResponseEntity.ok("삭제 성공");
		else
			return ResponseEntity.badRequest().body("삭제 실패");
	}

}
