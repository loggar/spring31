package com.loggar.user.login;

import javax.inject.Inject;
import javax.inject.Provider;

import org.springframework.stereotype.Service;

import com.loggar.user.member.Member;

@Service
public class LoginServiceImpl implements LoginService {
	@Inject private Provider<LoginInfo> loginInfoProvider;
	
	public boolean login(Member member) {
		loginInfoProvider.get().save(member);
		
		return true;
	}
	
	public boolean logout() {
		loginInfoProvider.get().remove();
		
		return true;
	}
}
