package com.loggar.controller.sample;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/test/RequestContextUtils")
public class RequestContextUtilTestController {
	@RequestMapping("/sample")
	public void sample(HttpServletRequest request) {
		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		System.out.println(locale);
	}
}
