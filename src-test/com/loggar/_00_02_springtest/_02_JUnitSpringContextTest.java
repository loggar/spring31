package com.loggar._00_02_springtest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.either;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JUnit 이 "@Autowired" 로 DI 받은 ApplicationContext 가 Singleton 인지 TEST
 * 
 * @author Loggar
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-junit.xml")
public class _02_JUnitSpringContextTest {
	@Autowired ApplicationContext context;

	static Set<_02_JUnitSpringContextTest> testObjects = new HashSet<_02_JUnitSpringContextTest>();
	static ApplicationContext contextObject = null;

	@Test
	public void test1() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);

		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}

	@Test
	public void test2() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);

		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}

	@Test
	public void test3() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);

		assertThat(contextObject, either(is(nullValue())).or(is(_02_JUnitSpringContextTest.contextObject)));
		contextObject = this.context;
	}
}
