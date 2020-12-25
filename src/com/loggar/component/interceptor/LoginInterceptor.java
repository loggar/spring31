package com.loggar.component.interceptor;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.loggar.user.login.LoginInfo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Inject private Provider<LoginInfo> loginInfoProvider;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (loginInfoProvider.get().isLoggedIn()) {
			return true;
		}

		response.sendRedirect(request.getContextPath() + "/test/accessdenied");
		return false;
	}
}
