package com.jgk.springrecipes.aop.pct;

import org.springframework.stereotype.Component;

@Component
public class PojoWithSetterMethod {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
