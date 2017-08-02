package com.loggar.user.login;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.loggar.user.member.Member;

public class LoginInfoSessionTest {
	@Test
	public void loginLogout() {
		LoginInfoSession sli = new LoginInfoSession();
		assertThat(sli.getCurrentUser(), is(nullValue()));
		assertThat(sli.isLoggedIn(), is(false));
		assertThat(sli.getLoginTime(), is(nullValue()));
		
		Member member = new Member();
		sli.save(member);
		assertThat(sli.isLoggedIn(), is(true));
		assertThat(sli.getCurrentUser(), is(member));
		assertThat(sli.getLoginTime(), is(notNullValue()));

		sli.remove();
		assertThat(sli.isLoggedIn(), is(false));
		assertThat(sli.getCurrentUser(), is(nullValue()));
		assertThat(sli.getLoginTime(), is(nullValue()));
	}
	
	@Test(expected=IllegalStateException.class)
	public void illegalRemove() {
		LoginInfoSession sli = new LoginInfoSession();
		sli.remove();
	}
	
}
