package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository{
	
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findByUserid(String userid) {
		// TODO Auto-generated method stub
		return store.values().stream()
				.filter(member -> member.getUserid().equals(userid))
				.findAny();
	}

	@Override
	public Optional<Member> findByPassword(String password) {
		// TODO Auto-generated method stub
		return store.values().stream()
				.filter(member -> member.getPassword().equals(password))
				.findAny();
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny();
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
	
}
