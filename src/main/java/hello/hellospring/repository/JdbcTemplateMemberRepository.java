package hello.hellospring.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import hello.hellospring.domain.Member;

public class JdbcTemplateMemberRepository implements MemberRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public JdbcTemplateMemberRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate();
	}

	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", member.getName());
		parameters.put("userid", member.getUserid());
		parameters.put("password", member.getPassword());
		
		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		member.setId(key.longValue());
		
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from member", memberRowMapper());
	}
	
	private RowMapper<Member> memberRowMapper(){
		return (rs, rowNum) -> {
			Member member = new Member();
			member.setId(rs.getLong("id"));
			member.setName(rs.getString("name"));
			return member;
		};
	}

	@Override
	public Optional<Member> findByUserid(String userid) {
		// TODO Auto-generated method stub
		List<Member> result = jdbcTemplate.query("select * from member where userid = ?", memberRowMapper(), userid);
		return result.stream().findAny();
	}

	@Override
	public Optional<Member> findByPassword(String password) {
		// TODO Auto-generated method stub
		List<Member> result = jdbcTemplate.query("select * from member where password = ?", memberRowMapper(), password);
		return result.stream().findAny();
	}
	@Override
	public Optional<Member> findByRole(String role) {
		// TODO Auto-generated method stub
		List<Member> result = jdbcTemplate.query("select * from member where role = ?", memberRowMapper(), role);
		return result.stream().findAny();
	}
	
}
