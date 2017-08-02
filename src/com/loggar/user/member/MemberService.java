package com.loggar.user.member;

import java.util.List;

import com.loggar.component.generic.GenericService;


public interface MemberService extends GenericService<Member> {
	int updates(List<Member> list);
	Member getByIdenti(String identi);
	void login(Member member);
}
