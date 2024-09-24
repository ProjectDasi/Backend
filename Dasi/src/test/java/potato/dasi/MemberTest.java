package potato.dasi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import potato.dasi.domain.Member;
import potato.dasi.domain.Region;
import potato.dasi.persistence.MemberRepository;
import potato.dasi.persistence.PreferenceRepository;
import potato.dasi.persistence.RegionRepository;

@SpringBootTest
public class MemberTest {
	private final MemberRepository memberRepository;
	private final RegionRepository regionRepository;
	private final PreferenceRepository preferenceRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
    public MemberTest(MemberRepository memberRepository, 
                      RegionRepository regionRepository,
                      PreferenceRepository preferenceRepository, 
                      PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.regionRepository = regionRepository;
        this.preferenceRepository = preferenceRepository;
        this.passwordEncoder = passwordEncoder;
    }
	
//	@Test
	public void addMember() {
		Region region = regionRepository.findById(1L).get();
		
		Member member = Member.builder()
				.name("이영인")
				.phone("010-5195-1766")
				.region(region)
				.loginId("youngin")
				.password(passwordEncoder.encode("in1766"))
				.build();
		
		memberRepository.save(member);
	}
	
	@Test
	public void getMember() {		
		System.out.println(memberRepository.findById(1L).get());
	}
	
}
