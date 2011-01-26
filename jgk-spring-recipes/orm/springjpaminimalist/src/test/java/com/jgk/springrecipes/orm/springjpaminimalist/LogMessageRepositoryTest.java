package com.jgk.springrecipes.orm.springjpaminimalist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.springrecipes.orm.springjpaminimalist.domain.LogMessage;
import com.jgk.springrecipes.orm.springjpaminimalist.repository.LogMessageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/com/jgk/springrecipes/orm/springjpaminimalist/LogMessageRepositoryTest-config.xml")
public class LogMessageRepositoryTest {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	LogMessageRepository logMessageRepository;
	
	@Before
	public void onSetup() {
		for (String n : applicationContext.getBeanDefinitionNames()) {
			System.out.println(n);
		}
	}

	@Test
	public void jed() {
		String msg="HELLO";
		String author="Hemingway";
		LogMessage lm = LogMessage.createLogMessage(msg,author);
//		logMessageRepository.findAll();
		logMessageRepository.save(lm);
	}
}
