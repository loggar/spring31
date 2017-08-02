package com.loggar.controller.sample;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.loggar.support.controller.AbstractDispatcherServletTest;
import com.loggar.user.member.Member;


public class LoginControllerTest extends AbstractDispatcherServletTest {
	private Member testMember;
	
	@Before
	public void init() throws ServletException, IOException {
		setLocations("applicationContext-test.xml", "webmvc-config-test.xml", "view-config-test.xml");
		
		runService("/test/member/deleteAll");
		initRequest("/test/member/add", "GET").runService();
		initRequest("/test/member/add", "POST", getSession())
				.addParameter("identi", "someNewIdenti")
				.addParameter("name", "someNewName")
				.addParameter("password", "someNewPw")
				.runService();
		
		runService("/test/member/list");
		
		@SuppressWarnings("unchecked")
		List<Member> memberList = (List<Member>) getModelAndView().getModel().get("memberList");
		
		try {
			testMember = memberList.get(0);
		} catch (Exception e) {
			System.out.println("Empty Test Member Data");
			System.exit(0);
		}
	}
	
	@Test
	public void login() throws ServletException, IOException {
		runService("/test/user/login", "GET");
		
		initRequest("/test/user/login", "POST", getSession())
				.addParameter("identi", testMember.getIdenti())
				.addParameter("password", testMember.getPassword())
				.runService()
				.assertViewName("/test/user/loginDone");
		
		runService("/test/user/logout", getSession()).assertViewName("/test/user/logoutDone");
		
		runService("/test/user/logout", getSession()).assertViewName("error/logout");
	}
}
