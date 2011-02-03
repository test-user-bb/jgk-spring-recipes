package com.jgk.springrecipes.aop.pct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/com/jgk/springrecipes/aop/pct/PctTest-config.xml"})
public class PctTest {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	PojoWithSetterMethod pojoWithSetterMethod;
	
	@Test
	public void testIt() {
		pojoWithSetterMethod.setNothing();
		pojoWithSetterMethod.setDescription("HOWDY FRIEND");
		for (String name : applicationContext.getBeanDefinitionNames()){
			System.out.println(name);
		}
	}
}
