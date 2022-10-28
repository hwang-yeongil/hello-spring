package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository {
	Optional<Member> findByUserid(String userid);
	
	Member save(Member member);
//	Optional<Member> findById(Long id);
//	Optional<Member> findByName(String name);
//	Optional<Member> findByPassword(String password);
//	Optional<Member> findByRole(String role);
	List<Member> findAll();
}
