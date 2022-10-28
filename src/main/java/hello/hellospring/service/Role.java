package hello.hellospring.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN("ADMIN"),
	USERS("USERS");
	
	private String value;
}
