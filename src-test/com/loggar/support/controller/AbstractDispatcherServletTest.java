package com.loggar.support.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractDispatcherServletTest implements AfterRunService {
	final Logger logger = LoggerFactory.getLogger(AbstractDispatcherServletTest.class);
	
	protected MockHttpServletRequest request;
	protected MockHttpServletResponse response;
	protected MockServletConfig config = new MockServletConfig("test-spring-dispatcherServlet");
	protected MockHttpSession session;

	private ConfigurableDispatcherServlet dispatcherServlet;
	private Class<?>[] classes;
	private String[] locations;
	private String[] relativeLocations;
	private String servletPath;
	
	private String defaultMediaType_of_requestURL = "html";

	@After
	public void closeServletContext() {
		/*
		 * 한개의 TEST 가 끝날 때 마다 ApplicationContext(ConfigurableApplicationContext) 를 종료해준다.
		 * 종료 후 ApplicationContext 는 다음 runService() 호출시 다시 제작된다.
		 */
		if (this.dispatcherServlet != null) {
			((ConfigurableApplicationContext) dispatcherServlet.getWebApplicationContext()).close();
		}
	}
	
	public AbstractDispatcherServletTest setLocations(String... locations) {
		this.locations = locations;
		return this;
	}

	public AbstractDispatcherServletTest setRelativeLocations(String... relativeLocations) {
		this.relativeLocations = relativeLocations;
		return this;
	}

	public AbstractDispatcherServletTest setClasses(Class<?>... classes) {
		this.classes = classes;
		return this;
	}

	public AbstractDispatcherServletTest setServletPath(String servletPath) {
		if (this.request == null) this.servletPath = servletPath;
		else this.request.setServletPath(servletPath);
		return this;
	}

	public AbstractDispatcherServletTest initRequest(String requestUri, String method) {
		String targetRequestUri = requestUri;
		
		/* 확장자가 지정되지 않고 테스트되는 URI 에 대하여 자동으로 defaultMediaType 추가 */
		if(defaultMediaType_of_requestURL.trim().length() > 0) {
			String[] splitRequestUri = requestUri.split("\\.");
			if(splitRequestUri.length < 2) {
				targetRequestUri = splitRequestUri[splitRequestUri.length-1] + "." + defaultMediaType_of_requestURL;
			}
		}
		
		this.request = new MockHttpServletRequest(method, targetRequestUri);
		this.response = new MockHttpServletResponse();
		if (this.servletPath != null) this.setServletPath(this.servletPath);
		return this;
	}

	/**
	 * 이전의 dispatcher.service() 수행후의 session 을 지속하는 새로운 initRequest
	 * Session 테스트를 위함.
	 * 
	 * @param requestUri
	 * @param method
	 * @param s
	 *        기존 runService() 후의 getSession() 이 반환한 HttpSession 객체.
	 * @return
	 */
	public AbstractDispatcherServletTest initRequest(String requestUri, String method, HttpSession s) {
		initRequest(requestUri, method);
		if (s != null) this.request.setSession(s);
		return this;
	}

	public AbstractDispatcherServletTest initRequest(String requestUri, RequestMethod method) {
		return this.initRequest(requestUri, method.toString());
	}

	public AbstractDispatcherServletTest initRequest(String requestUri, RequestMethod method, HttpSession s) {
		return this.initRequest(requestUri, method.toString(), s);
	}

	public AbstractDispatcherServletTest initRequest(String requestUri) {
		initRequest(requestUri, RequestMethod.GET);
		return this;
	}

	public AbstractDispatcherServletTest initRequest(String requestUri, HttpSession s) {
		initRequest(requestUri, RequestMethod.GET, s);
		return this;
	}

	public AbstractDispatcherServletTest addParameter(String name, String value) {
		if (this.request == null)
			throw new IllegalStateException("request 가 초기화되지 않았습니다.");
		this.request.addParameter(name, value);
		return this;
	}

	public AbstractDispatcherServletTest buildDispatcherServlet() throws ServletException {
		if (this.classes == null && this.locations == null && this.relativeLocations == null)
			throw new IllegalStateException("classes와 locations 중 하나는 설정해야 합니다");
		this.dispatcherServlet = new ConfigurableDispatcherServlet();
		this.dispatcherServlet.setClasses(this.classes);
		this.dispatcherServlet.setLocations(this.locations);
		if (this.relativeLocations != null)
			this.dispatcherServlet.setRelativeLocations(getClass(), this.relativeLocations);
		this.dispatcherServlet.init(this.config);

		return this;
	}

	public AfterRunService runService() throws ServletException, IOException {
		if (this.dispatcherServlet == null) buildDispatcherServlet();
		if (this.request == null) throw new IllegalStateException("request 가 준비되지 않았습니다");
		if (this.response.isCommitted()) throw new IllegalStateException("response 가 이미 commit 되었습니다.");

		this.dispatcherServlet.service(this.request, this.response);
		
//		logger.debug("MockHttpServletResponse.getContentAsString()=" + this.response.getContentAsString());
		return this;
	}

	public AfterRunService runService(String requestUri) throws ServletException, IOException {
		initRequest(requestUri);
		runService();
		return this;
	}

	public AfterRunService runService(String requestUri, HttpSession s) throws ServletException, IOException {
		initRequest(requestUri, s);
		runService();
		return this;
	}

	public AfterRunService runService(String requestUri, String method) throws ServletException, IOException {
		initRequest(requestUri, method);
		runService();
		return this;
	}

	public AfterRunService runService(String requestUri, String method, HttpSession s) throws ServletException, IOException {
		initRequest(requestUri, method, s);
		runService();
		return this;
	}

	public WebApplicationContext getContext() {
		if (this.dispatcherServlet == null)
			throw new IllegalStateException("DispatcherServlet 이 준비되지 않았습니다");
		return this.dispatcherServlet.getWebApplicationContext();
	}

	public <T> T getBean(Class<T> beanType) {
		if (this.dispatcherServlet == null)
			throw new IllegalStateException("DispatcherServlet 이 준비되지 않았습니다");
		return this.getContext().getBean(beanType);
	}

	public ModelAndView getModelAndView() {
		return this.dispatcherServlet.getModelAndView();
	}
	
	public HttpSession getSession() {
		if (this.request == null) throw new IllegalStateException("request 가 준비되지 않았습니다");
		return this.request.getSession();
	}

	public AfterRunService assertModel(String name, Object value) {
		assertThat(this.getModelAndView().getModel().get(name), is(value));
		return this;
	}

	public AfterRunService assertViewName(String viewName) {
		assertThat(this.getModelAndView().getViewName(), is(viewName));
		return this;
	}

	public AfterRunService assertSession(String name, Object value) {
		if(getSession() == null) {
			throw new IllegalStateException("session 이 존재하지 않습니다.");
		}
		
		assertThat(getSession().getAttribute(name), is(value));
		return this;
	}

	public String getContentAsString() throws UnsupportedEncodingException {
		return this.response.getContentAsString();
	}
}
