package com.loggar.controller.sample;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.loggar.support.controller.AbstractDispatcherServletTest;

public class LocaleResolverControllerTest extends AbstractDispatcherServletTest {
	@Before
	public void init() {
		setLocations("applicationContext-test.xml", "webmvc-config-test.xml", "view-config-test.xml");
	}
	
	@Test
	public void locale() throws ServletException, IOException {
		String localeSessionAttributeName = "org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE";
		Locale locale_ko = new Locale("ko");
		
		runService("/test/resolver/locale").assertSession(localeSessionAttributeName, null);
		runService("/test/resolver/locale", getSession()).assertSession(localeSessionAttributeName, null);
		initRequest("/test/resolver/locale", getSession()).addParameter("newLocale", "ko").runService().assertSession(localeSessionAttributeName, locale_ko);
	}
}
