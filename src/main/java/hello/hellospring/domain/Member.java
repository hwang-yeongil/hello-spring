package hello.hellospring.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@SequenceGenerator(
		name="seq_member_gen",
		sequenceName = "seq_member",
		initialValue = 1,
		allocationSize = 1			
		)
public class Member {
	
	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_member_gen")
	private Long id;
	private String name;
	private String userid;
	private String password;
	private String role;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Builder
	public Member(Long id, String name, String userid, String password, String role) {
		this.id = id;
		this.name = name;
		this.userid = userid;
		this.password = password;
		this.role = role;
		
	}
}
