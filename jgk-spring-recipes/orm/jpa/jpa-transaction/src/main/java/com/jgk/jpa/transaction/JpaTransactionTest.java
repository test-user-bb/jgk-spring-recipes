package com.jgk.jpa.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:/com/jgk/jpa/transaction/JpaTransactionTest-context.xml")
public class JpaTransactionTest {
	
	@Test
	public void doit() {
	}

}
