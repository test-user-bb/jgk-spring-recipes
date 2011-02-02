package com.jgk.springrecipes.jpa.hades.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="USERS")
@NamedQuery(name="User.findByLastname",query="from User u where u.lastname = ?1")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	private String username;
	private String lastname;
	private Integer age;


}
