package com.loggar.component.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loggar.user.member.Member;
import com.loggar.user.member.MemberService;


@Component("memberAddValidator")
public class MemberAddValidator implements Validator {
	@Autowired private MemberService memberService; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member formMember = (Member)target;
		if (this.memberService.getByIdenti(formMember.getIdenti()) != null) errors.rejectValue("identi", "field.duplicate");
	}
}
