package com.loggar.controller.sample;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping("/test/resolver")
public class LocaleResolverController {
@Autowired LocaleResolver localeResolver;
	
	/**
	 * newLocale as a request parameter
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/locale", params="!newLocale")
	public void locale(HttpServletRequest request, ModelMap model) {
		model.put("currentLocale", localeResolver.resolveLocale(request));
	}
	
	@RequestMapping("/locale")
	public void locale(HttpServletRequest request, HttpServletResponse response, @RequestParam String newLocale) {
		localeResolver.setLocale(request, response, new Locale(newLocale));
	}
}
