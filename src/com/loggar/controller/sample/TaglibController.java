package com.loggar.controller.sample;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import com.loggar.user.member.Level;
import com.loggar.user.member.Member;
import com.loggar.user.member.MemberDetail;
import com.loggar.user.member.MemberService;


@Controller
@RequestMapping("/test/taglib")
public class TaglibController {
	@Autowired LocaleResolver localeResolver;
	@Autowired MemberService memberService;
	
	@RequestMapping("/taglib_test")
	public void memberList(HttpServletRequest request, Model model) {
		List<Member> memberList = memberService.getAll();
		
		if(memberList.size() > 0) {
			Member member = memberList.get(0);
			
			MemberDetail memberDetail  = new MemberDetail();
			memberDetail.setId(member.getId());
			memberDetail.setLevel(Level.BASIC);
			memberDetail.setDate(new Date());
			memberDetail.setMoney(new BigDecimal(5000000));
			memberDetail.setPoint(123);
			memberDetail.setAdmin(false);
			memberDetail.setEnableFlag("no");
			
			model.addAttribute(member);
			model.addAttribute(memberDetail);
		}
		
		model.addAttribute(memberList);
		model.addAttribute("currentLocale", localeResolver.resolveLocale(request));
	}

}
