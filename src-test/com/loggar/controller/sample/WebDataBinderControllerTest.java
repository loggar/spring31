package com.loggar.controller.sample;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.loggar.support.controller.AbstractDispatcherServletTest;
import com.loggar.user.member.Member;
import com.loggar.user.member.MemberDetail;

public class WebDataBinderControllerTest extends AbstractDispatcherServletTest {
	@Before
	public void init() {
		setLocations("applicationContext-test.xml", "webmvc-config-test.xml", "view-config-test.xml");
	}

	@Test
	public void allowed() throws ServletException, IOException {
		initRequest("/test/binder/test")
				.addParameter("id", "1")
				.addParameter("name", "memberName")
				.addParameter("password", "memberPassword")
				.runService();
		
		Member member = (Member) getModelAndView().getModel().get("member");
		assertThat(member.getPassword(), is(nullValue()));
	}
	
	@Test
	public void required() throws ServletException, IOException {
		initRequest("/test/binder/test")
				.addParameter("name", "memberName")
				.addParameter("password", "memberPassword")
				.runService()
				.assertViewName("error/bind");
	}
	
	@Test
	public void prefix() throws ServletException, IOException {
		initRequest("/test/binder/prefix")
				.addParameter("id", "1")
				.addParameter("_isAdmin", "on")
				.addParameter("!enableFlag", "no")
				.runService();
		
		MemberDetail memberDetail = (MemberDetail) getModelAndView().getModel().get("memberDetail");
		assertThat(memberDetail.isAdmin(), is(false));
		assertThat(memberDetail.getEnableFlag(), is("no"));
	}
}
