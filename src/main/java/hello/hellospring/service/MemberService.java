package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@Transactional
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public Long join(Member member) {
		long start = System.currentTimeMillis();
		
		try {
			validateDuplicateMember(member);
			memberRepository.save(member);
			return member.getId();
		} finally {
			// TODO: handle exception
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("join " + timeMs + "ms");
		}
	}

	private void validateDuplicateMember(Member member) {
		// TODO Auto-generated method stub
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}
	
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
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
