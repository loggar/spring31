package com.loggar._00_01_jnuit;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * JUnit create new instance for every Test
 * 
 */
public class JUnitInstanceTest {
	static Set<JUnitInstanceTest> testObjects = new HashSet<JUnitInstanceTest>();

	@Test
	public void test1() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
	}

	@Test
	public void test2() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
	}

	@Test
	public void test3() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
	}
}
