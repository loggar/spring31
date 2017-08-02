package com.loggar.controller.sample;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loggar.user.member.Member;
import com.loggar.user.member.MemberDetail;

@Controller
@RequestMapping("/test/binder")
public class WebDataBinderController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("password");
		binder.setRequiredFields("id");
	}
	
	@RequestMapping("/test")
	public String test(@ModelAttribute Member member, BindingResult memberBindingResult) {
		if(memberBindingResult.hasErrors()) {
			return "error/bind";
		}
		
		return "./test";
	}
	
	@RequestMapping("/prefix")
	public void prefix(@ModelAttribute MemberDetail memberInfo) {
		// ...
	}
}
