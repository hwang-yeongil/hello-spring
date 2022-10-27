package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Test
	public void signIn() throws Exception{
		Member member = new Member();
//		member.setId(222L);
		member.setName("hello");
		member.setUserid("hello");
		member.setPassword("1234");
		
		Long saveId = memberService.join(member);
		
		Member findMember = memberRepository.findById(saveId).get();
		assertEquals(member.getName(), findMember.getName());
	}
	
	@Test
	public void signInException() throws Exception {
		Member member1 = new Member();
		member1.setName("spring");
		member1.setUserid("spring");
		member1.setPassword("1234");
		
		Member member2 = new Member();
		member2.setName("spring");
		member2.setUserid("spring");
		member2.setPassword("1234");
		
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
	}
}
