package com.loggar.util.http;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;

import com.loggar.util.common.StringUtil;

public class DebugHttpUtil {
	public static <K, V> String debugMap(Map<K, V> map) {
		Set<K> keySet = map.keySet();

		StringBuilder sb = new StringBuilder();
		sb.append(StringUtil.SYSTEM_LINE_SEPARATER);
		for (Object key : keySet) {
			sb.append(key).append("=").append(map.get(key)).append(StringUtil.SYSTEM_LINE_SEPARATER);
		}
		sb.append("}");

		return sb.toString();
	}

	public static String debugMap(ModelMap modelMap) {
		return debugMap((Map<String, Object>) modelMap);
	}

	public static String debugRequestParameters(HttpServletRequest request) {
		Enumeration<String> paramNames = request.getParameterNames();

		StringBuilder builder = new StringBuilder();
		builder.append("{");
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			builder.append(paramName + "=").append(request.getParameter(paramName));
			if (paramNames.hasMoreElements())
				builder.append(", ");
		}
		builder.append("}");

		return builder.toString();
	}

	public static String debugHttpSession(HttpSession session) {
		Enumeration<String> sessrionNames = session.getAttributeNames();

		StringBuilder builder = new StringBuilder();
		builder.append("{");
		while (sessrionNames.hasMoreElements()) {
			String sessionName = sessrionNames.nextElement();
			builder.append(sessionName + "=").append(session.getAttribute(sessionName));
			if (sessrionNames.hasMoreElements())
				builder.append(", ");
		}
		builder.append("}");

		return builder.toString();
	}

	public static String debugHttpSession(HttpServletRequest request) {
		return debugHttpSession(request.getSession());
	}

	public static String debugCookie(Cookie[] cookies) {
		if (cookies == null)
			return null;
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		for (int i = 0; i < cookies.length; i++) {
			builder.append(cookies[i].getName()).append("=").append(cookies[i].getValue());
			if (i < cookies.length - 1)
				builder.append(", ");
		}
		builder.append("}");

		return builder.toString();
	}
}
