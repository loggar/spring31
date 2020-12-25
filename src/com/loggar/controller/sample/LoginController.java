package com.loggar.controller.sample;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.loggar.user.login.LoginInput;
import com.loggar.user.login.LoginService;
import com.loggar.user.member.Member;
import com.loggar.user.member.MemberService;

@Controller
@RequestMapping("/test/user")
@SessionAttributes("loginInput")
public class LoginController {
	@Autowired private Validator loginValidator;
	@Autowired private MemberService memberService;
	@Autowired private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showform(ModelMap model) {
		model.addAttribute(new LoginInput());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute @Valid LoginInput loginInput, BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			return "./login";
		}

		this.loginValidator.validate(loginInput, result);

		if (result.hasErrors()) {
			return "./login";
		}

		Member member = memberService.getByIdenti(loginInput.getIdenti());
		member.setPassword("dummyPassword");

		loginService.login(member);
		status.setComplete();

		return "/test/user/loginDone";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		loginService.logout();

		return "/test/user/logoutDone";
	}

	@ExceptionHandler(IllegalStateException.class)
	public ModelAndView illegalLogout(IllegalStateException ex) {
		return new ModelAndView("error/logout").addObject("msg", ex.getMessage());
	}
}
