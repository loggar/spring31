package com.loggar.component.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.loggar.util.http.DebugHttpUtil;

public class DebugInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(DebugInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/* request */
		logger.debug("DebugInterceptor.preHandlerequest-uri=" + request.getRequestURI());
		logger.debug("method=" + request.getMethod());

		/* session */
		logger.debug("session-attributes=" + DebugHttpUtil.debugHttpSession(request.getSession()));

		/* cookie */
		logger.debug("request-cookies=" + DebugHttpUtil.debugCookie(request.getCookies()));

		/* request parameters */
		logger.debug("request-parameters=" + DebugHttpUtil.debugRequestParameters(request));

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/* debug model */
		if (modelAndView == null) {
			logger.debug("modelAndView=null");
		} else {
			logger.debug("modelAndView-model=" + modelAndView.getModel());
			logger.debug("modelAndView-view=" + modelAndView.getViewName());
		}

		/* session */
		logger.debug("session-attributes=" + DebugHttpUtil.debugHttpSession(request.getSession()));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		/* contentType */
		logger.debug("HttpServletResponse.getContentType()=" + response.getContentType());
		logger.debug("Exception : " + ex);
	}

}
