package com.jgk.springrecipes.orm.jpa.joinedsubclass.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="VEHICLE_HERE")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Vehicle {
	
	@Id
	@GeneratedValue
	@Column(name="VEHICLE_ID")
	protected Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Version protected Integer version;
	

}
