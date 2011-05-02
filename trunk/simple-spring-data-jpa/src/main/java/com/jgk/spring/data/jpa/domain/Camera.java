package com.jgk.spring.data.jpa.domain;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Camera implements Serializable {
	
	private Long id;
	private String make;
	private String model;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
