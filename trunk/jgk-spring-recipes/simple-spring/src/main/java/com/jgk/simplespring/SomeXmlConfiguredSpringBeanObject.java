package com.jgk.simplespring;

import org.springframework.beans.factory.annotation.Required;


public class SomeXmlConfiguredSpringBeanObject {

	Runtimer runtimer;
	@Required
	public void setRuntimer(Runtimer runtimer) {
		this.runtimer = runtimer;
	}
}
