package com.jgk.springrecipes.blackbelt.dataaccess;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:/DataAccessTest-config.xml")
abstract public class DataAccessTestFixture {
	protected boolean legalHost;
	

	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	
	@Before
	public void checkHost() {
		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
//			System.out.println("host name: " + hostName);
//			System.out.println("host address: " + hostAddress);
			legalHost=hostAddress.startsWith("192.168.6.");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
