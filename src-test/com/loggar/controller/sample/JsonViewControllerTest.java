package com.loggar.controller.sample;

import static org.junit.Assert.assertNull;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.loggar.support.controller.AbstractDispatcherServletTest;

public class JsonViewControllerTest extends AbstractDispatcherServletTest {
	@Before
	public void init() {
		setLocations("applicationContext-test.xml", "webmvc-config-test.xml", "view-config-test.xml");
	}

//	@Test
	public void get() throws ServletException, IOException {
		ModelAndView modelAndView = initRequest("/test/json/get.json").addParameter("id", "member1_id").runService().getModelAndView();
		assertNull(modelAndView.getView());
	}

	@Test
	public void getList() throws ServletException, IOException {
		assertNull(runService("/test/json/getList.json").getModelAndView().getView());
	}
}
