package com.loggar.component.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptorApplicationBlockExample extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String redirectUrl = "/auth/underConstruction";
		boolean block = false;
		if (block) {
			HttpSession session = request.getSession(false);
			if (session != null)
				session.setMaxInactiveInterval(1); // make all session expired.

			if (request.getRequestURI().equals(redirectUrl)) {
				return true;
			} else {
				response.sendRedirect(redirectUrl);
				return false;
			}
		}

		return true;
	}
}
