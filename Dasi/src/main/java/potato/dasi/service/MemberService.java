package potato.dasi.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import potato.dasi.domain.Member;
import potato.dasi.domain.Preference;
import potato.dasi.domain.PreferenceType;
import potato.dasi.domain.Region;
import potato.dasi.dto.FindInfoDTO;
import potato.dasi.dto.MyProfileDTO;
import potato.dasi.dto.SignUpDTO;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.PreferenceRepository;
import potato.dasi.persistence.PreferenceTypeRepository;
import potato.dasi.persistence.RegionRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PreferenceRepository preferenceRepository;
	private final RegionRepository regionRepository;
	private final PasswordEncoder passwordEncoder;
	private final PreferenceTypeRepository preferenceTypeRepository;
	
	public Member signUp(SignUpDTO signUpInfo) {
		Region region = regionRepository.findBySubregion(signUpInfo.getRegion()).orElse(null);
		
		if(region == null)
			return null;
		
		Member newMember = Member.builder()
				.name(signUpInfo.getName())
				.phone(signUpInfo.getPhone())
				.region(region)
				.loginId(signUpInfo.getLoginId())
				.password(passwordEncoder.encode(signUpInfo.getPassword()))
				.birth(signUpInfo.getBirth())
				.build();
		
		// 새로운 회원 저장
        Member savedMember = memberRepository.save(newMember);
        List<Preference> prelist = new ArrayList<>();
        for(String pId: signUpInfo.getPreferenceId()) {
        	PreferenceType type = preferenceTypeRepository.findById(Long.parseLong(pId)).get();
        	 // Preference 엔티티 생성 및 저장
            Preference preference = Preference.builder()
                    .member(savedMember)  // 저장된 Member의 ID를 사용하여 연관 설정
                    .preferenceType(type) // 필요에 따라 필드 설정
                    .build();

            preferenceRepository.save(preference);
            
            prelist.add(preference);
        }
        
        savedMember.setPreferenceList(prelist);
       
        return savedMember;
	}

	public MyProfileDTO getProfile(String id) {
		Member member = memberRepository.findById(Long.parseLong(id)).orElse(null);
		
		if(member == null)
			return null;
		
		MyProfileDTO profileDTO = MyProfileDTO.converToDTO(member);
		
		return profileDTO;
	}

	@Transactional
	public Member editProfile(String id, MyProfileDTO profile) {
		Member member = memberRepository.findById(Long.parseLong(id)).orElse(null);
		
		if(member == null)
			return null;
		
		if(profile.getRegion() != null) {
			Region region = regionRepository.findBySubregion(profile.getRegion()).orElse(null);
			if(region != null)
				member.setRegion(region);
		}
		
		if(profile.getName() != null)
			member.setName(profile.getName());
		if(profile.getPhone() != null)
			member.setPhone(profile.getPhone());
		if(profile.getLoginId() != null)
			member.setLoginId(profile.getLoginId());
		if(profile.getBirth() != null)
			member.setBirth(profile.getBirth());
		if(profile.getPassword() != null)
			member.setPassword(passwordEncoder.encode(profile.getPassword()));
		
	    // 기존 Preference 삭제
	    preferenceRepository.deleteAllByMemberId(member.getId());

	    // 새로운 Preference 리스트를 생성 및 저장
	    List<Preference> updatedPreferences = profile.getPreference().stream()
	        .map(pref -> {
	            PreferenceType type = preferenceTypeRepository.findById(pref.getId()).orElse(null);
	            return Preference.builder()
	                .member(member)
	                .preferenceType(type)
	                .build();
	        })
	        .collect(Collectors.toList());
	    
	    preferenceRepository.saveAll(updatedPreferences);
	    member.setPreferenceList(updatedPreferences);

	    return memberRepository.save(member);
	}

	public FindInfoDTO findId(FindInfoDTO findInfo) {
		Member member = memberRepository.findByPhone(findInfo.getPhone()).orElse(null);
		
		if(member == null)
			return null;
		
		FindInfoDTO result = FindInfoDTO.convertToDTO(member);
		return result;
	}	

}
