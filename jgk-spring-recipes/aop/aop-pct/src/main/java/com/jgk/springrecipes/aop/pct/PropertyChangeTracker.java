package com.jgk.springrecipes.aop.pct;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component(value="pctAspect")
public class PropertyChangeTracker {
	private Logger log = Logger.getLogger(PropertyChangeTracker.class);
	
	@Before("execution(void set*(*))")
	public void trackChange() {
		System.out.println("Property about to change");
		log.info("Property about to change");
	}
	
	
}
