package com.jgk.simplespring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/com/jgk/simplespring/BasicSpringJunitTest-context.xml"})//({"/applicationContext.xml", "/applicationContext-test.xml"})
public class BasicSpringJunitTest {

	@Autowired ApplicationContext ac;
	@Autowired SimpleComponent sc;
	
	@Test public void tester() {
		System.out.println("HELLO");
		System.out.println(ac);
	}
	@Before public void before() {
		showBeanNames();
	}
	
	private void showBeanNames() {
		for ( String beanName : ac.getBeanDefinitionNames()) {
			System.out.println(beanName);
			
		}
	}
}
