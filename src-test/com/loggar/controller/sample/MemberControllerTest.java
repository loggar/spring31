package com.loggar.controller.sample;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;

import com.loggar.support.controller.AbstractDispatcherServletTest;
import com.loggar.user.member.Member;


public class MemberControllerTest extends AbstractDispatcherServletTest {
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
	public void list() throws ServletException, IOException {
		runService("/test/member/list").assertViewName("test/member/list");
	}
	
//	@Test
	public void view() throws ServletException, IOException {
		runService("/test/member/view/" + testMember.getId()).assertViewName("test/member/view");
	}
	
//	@Test
	public void edit() throws ServletException, IOException {
		initRequest("/test/member/edit/" + testMember.getId(), "GET").runService();

		initRequest("/test/member/edit", "POST", getSession())
				.addParameter("password", "someNewPw")
				.runService()
				.assertViewName("redirect:/test/member/list");

		assertThat(((Member) getModelAndView().getModel().get("member")).getId(), is(testMember.getId()));
		assertThat(getSession().getAttribute("member"), is(nullValue()));
	}

//	@Test
	public void edit_bind_valid() throws ServletException, IOException {
		initRequest("/test/member/edit/" + testMember.getId(), "GET").runService();

		initRequest("/test/member/edit", "POST", getSession())
				.addParameter("password", "ss")
				.runService()
				.assertViewName("test/member/edit"); /* return to edit view */

		assertThat(((Member) getModelAndView().getModel().get("member")).getId(), is(testMember.getId()));
		assertThat(getSession().getAttribute("member"), not(nullValue()));
	}

//	@Test
	public void remove() throws ServletException, IOException {
		runService("/test/member/delete/" + testMember.getId()).assertViewName("redirect:./list");
	}
}
