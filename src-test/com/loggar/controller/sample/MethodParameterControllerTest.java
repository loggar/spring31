package com.loggar.controller.sample;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.loggar.support.controller.AbstractDispatcherServletTest;

public class MethodParameterControllerTest extends AbstractDispatcherServletTest {
	@Before
	public void init() {
		setLocations("applicationContext-test.xml", "webmvc-config-test.xml", "view-config-test.xml");
	}
	
	@Test
	public void header() throws ServletException, IOException {
		runService("/test/param/header.html").assertModel("aliveFromHeader", 0L);
	}
	
	@Test
	public void cookie() throws ServletException, IOException {
		runService("/test/param/cookie").assertModel("identiFromCookie", "");
	}
	
	@Test
	public void value() throws ServletException, IOException {
		runService("/test/param/value");
		runService("/test/param/value_1");
		runService("/test/param/value_2");
	}
}
