package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import hello.hellospring.domain.Member;

public class JpaMemberRepository implements MemberRepository{
	
	

	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		em.persist(member);
		return member;		
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}
	
	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
		return result.stream().findAny();
	}
	@Override
	public Optional<Member> findByUserid(String userid) {
		// TODO Auto-generated method stub
		List<Member> result = em.createQuery("select m from Member m where m.userid = :userid", Member.class)
				.setParameter("userid", userid)
				.getResultList();
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByPassword(String password) {
		// TODO Auto-generated method stub
		List<Member> result = em.createQuery("select m from Member m where m.password = :password", Member.class)
				.setParameter("password", password)
				.getResultList();
		return result.stream().findAny();
	}
	

}
