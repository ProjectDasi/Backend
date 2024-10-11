package potato.dasi.service;

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
		Pageable sortByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());

		Page<LearningProgram> learningListPage = learningProgramRepository.findAll(sortByIdDesc);

		return learningListPage.map(learning -> LearningListDTO.convertToDTO(learning));
	}

	public LearningDetailDTO getLearningDetail(String id) {
		Optional<LearningProgram> learning = learningProgramRepository.findById(Long.parseLong(id));

		if (learning.isEmpty())
			return null;

		LearningDetailDTO learningDetail = LearningDetailDTO.convertToDTO(learning.get());

		return learningDetail;
	}

//	public Page<LearningListDTO> searchLearningList(Pageable pageable, String keyword) {
//		Pageable sortedByCreatedDateDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//				Sort.by("id").descending());
//		Page<LearningProgram> learningListPage = learningProgramRepository.searchLearningPrograms(keyword,
//				sortedByCreatedDateDesc);
//
//		return learningListPage.map(learning -> LearningListDTO.convertToDTO(learning));
//	}
	
	public Page<LearningListDTO> searchLearningList(Pageable pageable, String subRegion, String keyword) {
		Region region = regionRepository.findBySubregion(subRegion).orElse(null);
		Long regionId = null;
		String teachingMethod = null;
		
		if(region == null || region.getId().equals(1L))
			regionId = null;
		else
			regionId = region.getId();
		
		Pageable sortedByCreatedDateDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());
		Page<LearningProgram> learningListPage = learningProgramRepository.searchLearningPrograms(regionId, keyword,
				sortedByCreatedDateDesc);

		return learningListPage.map(learning -> LearningListDTO.convertToDTO(learning));
	}

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

		// POST 요청 보내기
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:5000/get_education_id", HttpMethod.POST,
				requestEntity, String.class);

		// 응답확인
		String res = response.getBody();
		System.out.println(res);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(res);
		JsonNode itemsNode = root.path("education_ids");

		List<Long> learningIds = objectMapper.readValue(itemsNode.toString(), new TypeReference<List<Long>>() {
		});
		List<LearningProgram> recoLearningList = learningProgramRepository.findAllById(learningIds);

		return recoLearningList.stream().map(learning -> LearningRecommendDTO.convertToDTO(learning)).collect(Collectors.toList());
	}
}
