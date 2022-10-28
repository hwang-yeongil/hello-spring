package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
//	@Autowired
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	@GetMapping("/members/new")
	public String createForm(Model model) {
		model.addAttribute("member",new MemberForm());
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		memberService.signUp(form);
//		Member member = new Member();
//		member.setName(form.getName());
//		member.setUserid(form.getUserid());
//		member.setPassword(form.getPassword());
//		member.setRole("USERS");
//		
//		memberService.join(member);
//		
		return "redirect:/";
	}
	
	@PostMapping("/members/login")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
//	@GetMapping(value = "/mem")
//	public String aaa() {
//		return "mem";
//	}
}
