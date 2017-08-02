package com.loggar.user.login;

import com.loggar.user.member.Member;

public interface LoginService {
	public boolean login(Member member);
	public boolean logout();
}
