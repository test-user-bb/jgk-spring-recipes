package com.jgk.spring.data.jpa;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/SimpleSpringDataWithJpaAndHibernateTest-config.xml"})
public class SimpleSpringDataWithJpaAndHibernateTest {
	
//	@Inject
//	ApplicationContext applicationContext;
	
	@Test
	public void testApp() {
		assertTrue(true);
//		assertNotNull(applicationContext);
//		System.out.println(applicationContext);
	}
}
