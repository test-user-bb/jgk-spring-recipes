package com.jgk.springrecipes.blackbelt.dataaccess.dao.jdbctemplate;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jgk.springrecipes.blackbelt.dataaccess.dao.UsernameDao;
import com.jgk.springrecipes.blackbelt.dataaccess.domain.Username;
import com.jgk.springrecipes.blackbelt.dataaccess.domain.UsernameRowMapper;

@Repository(value="usernameRepository")
public class JdbcTemplateBackedUsernameRepository implements UsernameDao {

	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	@Override
	public List<Username> findAll() {
		return getUsernameObjects();
	}
	
	private List<Username> getUsernameObjects() {
		List<Username> usernames = jdbcTemplate.query(
		        "select username, password, password_reminder, interim_name, person_id, user_type from username ",
		        new UsernameRowMapper()
		        );		
		return usernames;
	}

}
