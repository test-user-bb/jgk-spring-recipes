package com.jgk.springrecipes.blackbelt.dataaccess;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:/DataAccessTest-config.xml")
abstract public class DataAccessTestFixture {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
}
