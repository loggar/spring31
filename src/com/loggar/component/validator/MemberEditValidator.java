package com.loggar.component.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loggar.user.member.Member;
import com.loggar.user.member.MemberService;


@Component("memberEditValidator")
public class MemberEditValidator implements Validator {
	@Autowired private MemberService memberService; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Member.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Member formMember = (Member)target;
		Member member = this.memberService.get(formMember.getId());
		
		if(!member.getIdenti().equals(formMember.getIdenti())) {
			if (this.memberService.getByIdenti(formMember.getIdenti()) != null) errors.rejectValue("identi", "filed.duplicate");
		}
	}
}
