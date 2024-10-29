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
import potato.dasi.domain.Member;
import potato.dasi.domain.Region;
import potato.dasi.domain.Work;
import potato.dasi.dto.RecommendReqDTO;
import potato.dasi.dto.WorkDetailDTO;
import potato.dasi.dto.WorkListDTO;
import potato.dasi.dto.WorkRecommendDTO;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.RegionRepository;
import potato.dasi.persistence.WorkRepository;

@Service
@RequiredArgsConstructor
public class WorkService {
	private final WorkRepository workRepository;
	private final MemberRepository memberRepository;
	private final ObjectMapper objectMapper;
	private final RestTemplate restTemplate;
	private final RegionRepository regionRepository;

	public Page<WorkListDTO> getWorkListPaging(Pageable pageable) {
		Pageable sortBySignUpDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("signupDate").descending());

		Page<Work> workListPage = workRepository.findAll(sortBySignUpDesc);

//		int totalElements = (int) workListPage.getTotalElements();
//      AtomicInteger start = new AtomicInteger(totalElements - (int) sortByIdDesc.getOffset());

		return workListPage.map(work -> WorkListDTO.convertToDTO(work));
	}

	public WorkDetailDTO getWorkDetail(String id) {
		Optional<Work> work = workRepository.findById(Long.parseLong(id));

		if (work.isEmpty())
			return null;

		WorkDetailDTO workDetail = WorkDetailDTO.convertToDTO(work.get());

		return workDetail;
	}

	public List<WorkRecommendDTO> getRecommend(String id) throws Exception {
		Member member = memberRepository.findById(Long.parseLong(id)).orElse(null);

		if (member == null)
			return null;

		List<String> preference = member.getPreferenceList().stream().map(pref -> pref.getPreferenceType().getType())
				.collect(Collectors.toList());

		RecommendReqDTO reqDTO = RecommendReqDTO.builder().regionId(member.getRegion().getId()).preference(preference)
				.build();

		System.out.println(reqDTO);

//		List<String> preference = new ArrayList<>();
//		preference.add("현실형");
//		preference.add("관습형");
//		
//		Region region = regionRepository.findById(5L).get();
//		RecommendReqDTO reqDTO = RecommendReqDTO.builder()
//				 .regionId(5L)
//				 .preference(preference)
//				 .build();

		// JSON 문자열로 변환
		String jsonPayload = objectMapper.writeValueAsString(reqDTO);

		// HTTP 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HTTP 엔터티 생성
		HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

		// POST 요청 보내기
		ResponseEntity<String> response = restTemplate.exchange("http://172.21.40.4:5000/get_job_id", HttpMethod.POST,
				requestEntity, String.class);

		// 응답확인
		String res = response.getBody();
		System.out.println(res);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(res);
		JsonNode itemsNode = root.path("job_ids");

		List<Long> workIds = objectMapper.readValue(itemsNode.toString(), new TypeReference<List<Long>>() {
		});
//		for(Long item: workIds) {
//			System.out.println(item);
//		}

		List<Work> recoWorkList = workRepository.findAllById(workIds);
//		for(Work item: recoWorkList) {
//			System.out.println(item);
//		}

		return recoWorkList.stream().map(work -> WorkRecommendDTO.convertToDTO(work)).collect(Collectors.toList());
	}

	public Page<WorkListDTO> searchWorkList(Pageable pageable, String subRegion, String keyword,
			boolean qualification) {
		Region region = regionRepository.findBySubregion(subRegion).orElse(null);
		Long regionId = null;

		if (region == null || region.getId().equals(1L))
			regionId = null;
		else
			regionId = region.getId();

		Pageable sortedByCreatedDateDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").descending());
		Page<Work> workListPage = workRepository.searchWorks(regionId, keyword, qualification, sortedByCreatedDateDesc);

		return workListPage.map(work -> WorkListDTO.convertToDTO(work));
	}

//	public Page<WorkListDTO> searchWorkList(Pageable pageable, String subRegion, String keyword) {
//		Region region = regionRepository.findBySubregion(subRegion).orElse(null);
//		Long regionId = null;
//		
//		if(region == null || region.getId().equals(1L))
//			regionId = null;
//		else
//			regionId = region.getId();
//		
//		
//		Pageable sortedByCreatedDateDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
//				Sort.by("id").descending());
//		Page<Work> workListPage = workRepository.searchWorks(regionId, keyword, sortedByCreatedDateDesc);
//		
//		return workListPage.map(work -> WorkListDTO.convertToDTO(work));
//	}

	public Page<WorkListDTO> searchQualificationWorkList(Pageable pageable) {
		Pageable sortBySignUpDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("signupDate").descending());
		Page<Work> workListPage = workRepository.findByPreferredQualificationsIsNotNull(sortBySignUpDesc);

		return workListPage.map(work -> WorkListDTO.convertToDTO(work));
	}

	public void updateWorkView(String id) {
		Optional<Work> workOptional = workRepository.findById(Long.parseLong(id));

		if (workOptional.isPresent()) {
	        Work work = workOptional.get();
	        work.setViews(work.getViews() + 1);
	        workRepository.save(work);
	    }
	}

}
