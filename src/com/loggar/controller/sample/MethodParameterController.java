package com.loggar.controller.sample;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/param")
public class MethodParameterController {
	@RequestMapping("/header")
	public void header(@RequestHeader(value = "Host", required = false, defaultValue = "") String host, @RequestHeader(value = "Keep-Alive", required = false, defaultValue = "0") long keepAlive, Model model) {
		model.addAttribute("hostFromHeader", host);
		model.addAttribute("aliveFromHeader", keepAlive);
	}

	@RequestMapping("/cookie")
	public void cookie(@CookieValue(value = "identi", required = false, defaultValue = "") String identi, Model model) {
		model.addAttribute("identiFromCookie", identi);
	}

	@RequestMapping("/value")
	public void value(@Value("#{systemProperties['os.name']}") String osName, @Value("${database.url}") String databaseUrl, Model model) {
		model.addAttribute("os.name", osName);
		model.addAttribute("database.url", databaseUrl);
	}

	@RequestMapping("/value_1")
	public void value_1(@Value("#{systemProperties}") Map<String, String> systemProperties, Model model) {
		model.addAllAttributes(systemProperties);
	}

	@RequestMapping("/value_2")
	public void value_2(@Value("#{systemEnvironment}") Map<String, String> systemEnvironment, Model model) {
		model.addAllAttributes(systemEnvironment);
	}
}
