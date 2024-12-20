package potato.dasi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import potato.dasi.domain.LearningProgram;
import potato.dasi.domain.Member;
import potato.dasi.domain.Region;
import potato.dasi.dto.LearningDetailDTO;
import potato.dasi.dto.LearningListDTO;
import potato.dasi.dto.LearningRecommendDTO;
import potato.dasi.dto.RecommendReqDTO;
import potato.dasi.persistence.LearningProgramRepository;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.RegionRepository;

@Service
@RequiredArgsConstructor
public class LearningProgramService {
	private final LearningProgramRepository learningProgramRepository;
	private final MemberRepository memberRepository;
	private final RegionRepository regionRepository;
	private final ObjectMapper objectMapper;
	private final RestTemplate restTemplate;

	public Page<LearningListDTO> getLearningListPaging(Pageable pageable) {
//		Pageable sortByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//				Sort.by("id").descending());
		Pageable sortByApplicationStartDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("applicationStart").descending());

		Page<LearningProgram> learningListPage = learningProgramRepository.findAll(sortByApplicationStartDesc);

		return learningListPage.map(learning -> LearningListDTO.convertToDTO(learning));
	}

	public LearningDetailDTO getLearningDetail(String id) {
		Optional<LearningProgram> learning = learningProgramRepository.findById(Long.parseLong(id));

		if (learning.isEmpty())
			return null;

		LearningDetailDTO learningDetail = LearningDetailDTO.convertToDTO(learning.get());

		return learningDetail;
	}

//	public Page<LearningListDTO> searchLearningList(Pageable pageable, String subRegion, String keyword) {
//		Region region = regionRepository.findBySubregion(subRegion).orElse(null);
//		Long regionId = null;
//		String teachingMethod = null;
//		
//		if(region == null || region.getId().equals(1L))
//			regionId = null;
//		else
//			regionId = region.getId();
//		
//		Pageable sortedByCreatedDateDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//				Sort.by("id").descending());
//		Page<LearningProgram> learningListPage = learningProgramRepository.searchLearningPrograms(regionId, keyword,
//				sortedByCreatedDateDesc);
//
//		return learningListPage.map(learning -> LearningListDTO.convertToDTO(learning));
//	}

	public List<LearningRecommendDTO> getRecommend(String id) throws Exception {
		Member member = memberRepository.findById(Long.parseLong(id)).orElse(null);

		if (member == null)
			return null;

		List<String> preference = member.getPreferenceList().stream().map(pref -> pref.getPreferenceType().getType())
				.collect(Collectors.toList());

		RecommendReqDTO reqDTO = RecommendReqDTO.builder().regionId(member.getRegion().getId()).preference(preference)
				.build();

		System.out.println(reqDTO);

		// JSON 문자열로 변환
		String jsonPayload = objectMapper.writeValueAsString(reqDTO);

		// HTTP 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HTTP 엔터티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

		try {
			// POST 요청 보내기
			ResponseEntity<String> response = restTemplate.exchange("http://172.21.31.88:5000/get_education_id",
					HttpMethod.POST, requestEntity, String.class);

			// 응답 확인
			String res = response.getBody();
			System.out.println(res);

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(res);
			JsonNode itemsNode = root.path("education_ids");

			// education_ids가 존재하지 않을 경우 빈 리스트 반환
			if (itemsNode.isMissingNode() || !itemsNode.isArray() || itemsNode.isEmpty()) {
				return Collections.emptyList();
			}

			// education_ids가 존재하는 경우 리스트로 변환
			List<Long> learningIds = objectMapper.readValue(itemsNode.toString(), new TypeReference<List<Long>>() {
			});
			List<LearningProgram> recoLearningList = learningProgramRepository.findAllById(learningIds);

			return recoLearningList.stream().map(learning -> LearningRecommendDTO.convertToDTO(learning))
					.collect(Collectors.toList());

		} catch (HttpClientErrorException.NotFound e) {
			// 404 오류가 발생하면 빈 리스트 반환
			System.out.println("No matching education programs found: " + e.getMessage());
			return Collections.emptyList();
		} catch (Exception e) {
			// 다른 예외는 그대로 던짐
			throw new RuntimeException("Error while fetching recommendations", e);
		}
	}

	public Page<LearningListDTO> searchPopularLearningList(Pageable pageable) {
		Pageable sortedByViewsDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("views").descending());
		Page<LearningProgram> learningListPage = learningProgramRepository.findAll(sortedByViewsDesc);

		return learningListPage.map(learning -> LearningListDTO.convertToDTO(learning));
	}

	public Page<LearningListDTO> searchLearningList(Pageable pageable, String subRegion, String keyword,
			boolean popularity) {
		Region region = regionRepository.findBySubregion(subRegion).orElse(null);
		Long regionId = null;
		String teachingMethod = null;

		if (region == null || region.getId().equals(1L))
			regionId = null;
		else
			regionId = region.getId();

		Pageable sortedByDesc = null;
		System.out.println(popularity);

		if (popularity)
			sortedByDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
					Sort.by("views").descending());
		else
			sortedByDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
					Sort.by("applicationStart").descending());
		Page<LearningProgram> learningListPage = learningProgramRepository.searchLearningPrograms(regionId, keyword,
				sortedByDesc);

		return learningListPage.map(learning -> LearningListDTO.convertToDTO(learning));
	}

	public void updateLearningViews(String id) {
		Optional<LearningProgram> learningOptional = learningProgramRepository.findById(Long.parseLong(id));

		if (learningOptional.isPresent()) {
			LearningProgram learning = learningOptional.get();
			learning.setViews(learning.getViews() + 1);
			learningProgramRepository.save(learning);
		}
//		if (!learning.isEmpty()) {
//			Long views = learning.get().getViews();
//			views += 1;
//			
//			learning.get().setViews(views);
//			learningProgramRepository.save(learning.get());
//		}		
	}
}
