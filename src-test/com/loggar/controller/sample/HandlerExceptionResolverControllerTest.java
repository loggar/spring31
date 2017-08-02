package com.loggar.controller.sample;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.loggar.support.controller.AbstractDispatcherServletTest;

public class HandlerExceptionResolverControllerTest extends AbstractDispatcherServletTest {
	@Before
	public void init() {
		setLocations("applicationContext-test.xml", "webmvc-config-test.xml", "view-config-test.xml");
	}

//	@Test
	public void exception_annotation() throws ServletException, IOException {
		runService("/test/resolver/exception/annotation").assertViewName("error/nullpointer");
	}
	
	/*
	 * org.springframework.web.servlet.handler.SimpleMappingExceptionResolver 
	 * 에 등록된 view 로 핸들링 되지 않는다 - runService 테스트 환경이 아닌 실제 web 테스트 환경에서 확인 해 볼것.
	 */
	@Test
	public void exception_simple() throws ServletException, IOException {
		runService("/test/resolver/exception/simple").assertViewName("error/arithmetic");
	}
}
