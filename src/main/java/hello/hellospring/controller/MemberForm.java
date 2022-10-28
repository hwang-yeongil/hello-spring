package hello.hellospring.controller;

import java.sql.Date;

import hello.hellospring.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class MemberForm {
	private Long id;
	private String name;
	private String userid;
	private String password;
	private String role;
	
	public Member toEntity() {
		return Member.builder().id(id).name(name).userid(userid).password(password).role(role).build();
	}
	@Builder
	public MemberForm(Long id, String name, String userid, String password, String role) {
		this.id = id;
		this.name = name;
		this.userid = userid;
		this.password = password;
		this.role = role;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
