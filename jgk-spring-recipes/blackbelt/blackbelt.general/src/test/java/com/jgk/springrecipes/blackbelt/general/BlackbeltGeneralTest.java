package com.jgk.springrecipes.blackbelt.general;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * http://code.google.com/p/jgk-spring-recipes/wiki/SpringBlackBelt_General?ts=1303573764&updated=SpringBlackBelt_General
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/BlackbeltGeneralTest-config.xml"})
public class BlackbeltGeneralTest {
	@BeforeClass
	public static void onlyOnce() {
//		System.out.println("Before starting");
	}

	@Test
	public void testApp() {
		assertTrue(true);
	}
}
