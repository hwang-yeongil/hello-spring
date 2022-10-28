package hello.hellospring.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import hello.hellospring.controller.MemberForm;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService{
	
	private MemberRepository memberRepository;
	
	@Transactional
	public Long signUp(MemberForm memberForm) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
		memberForm.setRole("USERS");
		return memberRepository.save(memberForm.toEntity()).getId();
	}

	@Override
	 public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        // 로그인을 하기 위해 가입된 user정보를 조회하는 메서드
        Optional<Member> memberWrapper = memberRepository.findByUserid(userid);
        Member member = memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(userid)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        	
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USERS.getValue()));
        }

        // 아이디, 비밀번호, 권한리스트를 매개변수로 User를 만들어 반환해준다.
        return new User(member.getUserid(), member.getPassword(), authorities);
    }
	
	
//	public MemberService(MemberRepository memberRepository) {
//		this.memberRepository = memberRepository;
//	}
//	
//	public Long join(Member member) {
//		long start = System.currentTimeMillis();
//		
//		try {
//			validateDuplicateMember(member);
//			memberRepository.save(member);
//			return member.getId();
//		} finally {
//			// TODO: handle exception
//			long finish = System.currentTimeMillis();
//			long timeMs = finish - start;
//			System.out.println("join " + timeMs + "ms");
//		}
//	}
//
//	private void validateDuplicateMember(Member member) {
//		// TODO Auto-generated method stub
//		memberRepository.findByName(member.getName())
//			.ifPresent(m -> {
//				throw new IllegalStateException("이미 존재하는 회원입니다.");
//			});
//	}
//	
	public List<Member> findMembers(){
		long start = System.currentTimeMillis();
		
		try {
			
			return memberRepository.findAll();
		} finally {
			// TODO: handle finally clause
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("findMembers " + timeMs + "ms");
		}
	}
//	
//	public Optional<Member> findOne(Long memberId){
//		return memberRepository.findById(memberId);
//	}
}
