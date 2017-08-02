package com.loggar.controller.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.loggar.user.member.Member;

@Controller
@RequestMapping("/test/xml")
public class XmlViewController {
	
	@RequestMapping(value="/get", method=RequestMethod .GET)
	public void get(@RequestParam String id, ModelMap model) {
		Member member1 = new Member(1, id, "member1_name", "member1_pw");
		
		model.put("member1", member1);
	}
	
	@RequestMapping(value="/getList", method=RequestMethod .GET)
	public void getList(ModelMap model) {
		
		Member member1 = new Member(1, "member1", "member1_name", "member1_pw");
		Member member2 = new Member(2, "member2", "member2_name", "member2_pw");
		
		List <Member> memberList = new ArrayList<Member>();
		memberList.add(member1);
		memberList.add(member2);
		
		model.put("memberList", memberList);
	}
}
