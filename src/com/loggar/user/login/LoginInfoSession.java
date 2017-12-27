package com.loggar.user.login;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.loggar.user.member.Member;


/**
 * LoginInfoSession 의 인스턴스는 session-scope bean 으로, 싱글톤 빈이 아니며, 독립적인 상태를 저장하는데 사용한다.
 * 관습적인 spring-DI 방식으로 생성하면 어플리케이션-scope 의 싱글톤 빈으로 만들어지므로
 * session-scope bean 은 Provider 를 이용하는 DL 방식을 사용하여 주입한다.
 * Provider 를 사용하여 가져오는 session-scope bean 은 사용자 세션별로 다르게 만들어지므로, 각 세션에서 독립적으로 수정하여도 안전하다.
 * 
 * spring bean 의 scope 는 다음의 종류로 나뉘며 생성된 객체의 독립여부는 다음과 같다.
 * - Scope("application") Application Scope : 어플리케이션에서 싱글톤
 * - Scope("session"), Scope("globalSession") Session(서블릿), Global-Session Scope(포틀릿) : 각 세션별로 하나의 인스턴스
 * - Scope("request") Request Scope : 각 Request 마다 하나의 인스턴스
 * - Scope("prototype") Prototype Scope : 객체를 가져올 때마다 새로운 인스턴스
 * 
 */
@Component
@Scope("session")
public class LoginInfoSession implements LoginInfo {
	private Member currentMember;
	private Date loginTime;
	
	public Member getCurrentUser() {
		return this.currentMember;
	}

	public boolean isLoggedIn() {
		return (this.currentMember != null);
	}

	public void remove() {
		if (this.currentMember == null) throw new IllegalStateException("Member did not Login");
		this.currentMember = null;
		this.loginTime = null;
	}

	public void save(Member member) {
		this.currentMember = member;
		this.loginTime = new Date();
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginInfoSession [currentMember=").append(currentMember).append(", loginTime=").append(loginTime).append("]");
		return builder.toString();
	}
}
