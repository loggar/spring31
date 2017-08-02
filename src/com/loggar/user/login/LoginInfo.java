package com.loggar.user.login;

import java.util.Date;

import com.loggar.user.member.Member;

public interface LoginInfo {
	void save(Member member);
	void remove();
	boolean isLoggedIn();
	Member getCurrentUser();
	Date getLoginTime();
}
