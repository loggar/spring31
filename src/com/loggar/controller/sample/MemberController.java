package com.loggar.controller.sample;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.loggar.component.generic.GenericDomainController;
import com.loggar.user.member.Member;
import com.loggar.user.member.MemberService;

@Controller
@RequestMapping("/test/member")
@SessionAttributes("member")
public class MemberController extends GenericDomainController<Member, MemberService> {
	@Autowired Validator memberAddValidator;
	@Autowired Validator memberEditValidator;

	@PostConstruct
	private void setViewPath() {
		super.viewPath = "test";
	}
	
	@Override
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public void edit(@PathVariable int id, ModelMap model) {
		model.put("member", service.get(id));
	}

	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute @Valid Member member, BindingResult memberBindingResult, SessionStatus sessionStatus) {
		this.memberEditValidator.validate(member, memberBindingResult);
		if (memberBindingResult.hasErrors()) {
			return super.viewPath + "/member/edit";
		}

		this.service.edit(member);
		sessionStatus.setComplete();
		return "redirect:/test/member/list";
	}

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(ModelMap model) {
		model.put("member", new Member());
	}

	@Override
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute @Valid Member member, BindingResult memberBindingResult, SessionStatus sessionStatus) {
		this.memberAddValidator.validate(member, memberBindingResult);
		if (memberBindingResult.hasErrors()) {
			return super.viewPath + "/member/add";
		}

		this.service.add(member);
		sessionStatus.setComplete();
		return "redirect:/test/member/list";
	}

	@RequestMapping(value = "/countByIdenti", method = RequestMethod.GET)
	public void countByIdenti(@RequestParam String testIdenti, ModelMap model) {
		Member member = service.getByIdenti(testIdenti);

		int result;
		if (member == null)
			result = 0;
		else
			result = 1;

		model.put("countByIdnti", result);
	}
}
