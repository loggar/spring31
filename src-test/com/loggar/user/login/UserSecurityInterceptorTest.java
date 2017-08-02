package com.loggar.user.login;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static com.loggar.support.inject.FieldInjectUtils.inject;

import javax.inject.Provider;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.loggar.component.interceptor.LoginInterceptor;



public class UserSecurityInterceptorTest {
	Provider<LoginInfo> loginInfoProvider;
	LoginInfo loginInfo;
	LoginInterceptor interceptor;
	
	@Before
	@SuppressWarnings("unchecked")
	public void before() {
		loginInfo = mock(LoginInfo.class);
		loginInfoProvider = mock(Provider.class);
		when(loginInfoProvider.get()).thenReturn(loginInfo);
		interceptor = new LoginInterceptor();
		inject(interceptor, Provider.class, loginInfoProvider);
	}
	
	@Test
	public void securityPass() throws Exception {
		when(loginInfo.isLoggedIn()).thenReturn(true);
		assertThat(interceptor.preHandle(null, null, null), is(true));
	}
	
	@Test
	public void securityFail() throws Exception {
		when(loginInfo.isLoggedIn()).thenReturn(false);
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		assertThat(interceptor.preHandle(request, response, null), is(false));
		assertThat(response.getRedirectedUrl(), is("/test/accessdenied"));
	}
}
