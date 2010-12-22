package com.jgk.springrecipes.jdbc;

import java.util.List;

import javax.sql.DataSource;

public class JdbcPersonRepository implements PersonRepository {
	private DataSource dataSource;
	public JdbcPersonRepository(DataSource _dataSource) {
		this.dataSource=_dataSource;
	}
	@Override
	public List<Person> findByLastName(String lastName) {
		return null;
	}
	
}
