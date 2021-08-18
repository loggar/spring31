package com.loggar.component.validator;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loggar.user.login.LoginInfo;
import com.loggar.user.login.LoginInput;
import com.loggar.user.member.Member;
import com.loggar.user.member.MemberService;

/**
 * login password check
 *
 */
@Component("loginValidator")
public class LoginValidator implements Validator {
	@Autowired private MemberService memberService;
	@Inject private Provider<LoginInfo> loginInfoProvider;

	public boolean supports(Class<?> clazz) {
		return LoginInput.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		LoginInput login = (LoginInput) target;
		Member member = memberService.getByIdenti(login.getIdenti());
		if (member == null || !member.getPassword().equals(login.getPassword())) {
			errors.reject("invalidLogin", "Invalid Login");
		} else {
			/* update currentMember with LoginInfo(session-scope bean) */
			loginInfoProvider.get().save(member);
		}
	}
}
