package com.jgk.springrecipes.orm.jpa.data;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/com/jgk/springrecipes/orm/jpa/data/MySpringJpaDataTest-config.xml")
public class MySpringJpaDataTest {

	@Test
	public void testIt() {
		assertTrue(true);
	}
}
