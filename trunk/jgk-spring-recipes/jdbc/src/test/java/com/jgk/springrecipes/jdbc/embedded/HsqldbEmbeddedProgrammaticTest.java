package com.jgk.springrecipes.jdbc.embedded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.jgk.springrecipes.jdbc.Person;

public class HsqldbEmbeddedProgrammaticTest {
	private EmbeddedDatabase db;
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void checkList() {
		List<Map<String, Object>> list=getList("t_person");
		assertNotNull(list);
		assertTrue(!list.isEmpty());
		//System.out.println(list);
	}
	public List<Map<String, Object>> getList(String tableName) {
	    return this.jdbcTemplate.queryForList("select * from "+tableName);
	}
	@Test
	public void checkDDL() {
		this.jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");
		assertEquals(Integer.valueOf(0),getTableCount("mytable"));
	}
	
	@Test
	public void checkUpdate() {
		Integer rowCount = jdbcTemplate.queryForInt("select count(*) from t_person");
		assertNotNull(rowCount);
		String lastName="Bodine";
		String newFirstName="Helen";
		Person beforePerson = getPersonByLastName(lastName);
		this.jdbcTemplate.update(
		        "update t_person set first_name = ? where last_name = ?", 
		        newFirstName, lastName);
		Person afterPerson = getPersonByLastName(lastName);
		assertEquals(newFirstName, afterPerson.getFirstName());
	}
	
	Integer getTableCount(String tableName) {
		Integer rowCount = jdbcTemplate.queryForInt("select count(*) from "+tableName);
		assertNotNull(rowCount);
		return rowCount;
		
	}
	Integer getPersonCount() {
		return getTableCount("t_person");
	}
	
	Person getPersonByLastName(String lastName) {
		Person person = this.jdbcTemplate.queryForObject(
		        "select * from t_person where last_name = ?",
		        new Object[]{lastName},
		        new PersonRowMapper());
		return person;

	}
	
	@Test
	public void checkDelete() {
		Integer before = getPersonCount();
		this.jdbcTemplate.update(
		        "delete from t_person where id = ?",
		        Integer.valueOf(2));
		Integer after = getPersonCount();
		System.out.println("before,after:"+before+","+after);
	}
	@Test
	public void checkInsertUsingUpdate() {
		Integer rowCount = jdbcTemplate.queryForInt("select count(*) from t_person");
		assertNotNull(rowCount);
		this.jdbcTemplate.update(
		        "insert into t_person (first_name, last_name) values (?, ?)", 
		        "Mr.", "Drysdale");
		rowCount = jdbcTemplate.queryForInt("select count(*) from t_person");
		assertNotNull(rowCount);
	}
	
	@Test
	public void basis() {
		Integer rowCount = jdbcTemplate.queryForInt("select count(*) from t_person");
		assertNotNull(rowCount);
		assertEquals(Integer.valueOf(3), rowCount);
		Integer countOfPersonsNamedClampett = this.jdbcTemplate.queryForInt(
		        "select count(*) from t_person where last_name = ?", "Clampett");
		
		String lastName = this.jdbcTemplate.queryForObject(
		        "select last_name from t_person where first_name = ?", 
		        new Object[]{"Jed"}, String.class);
		
		Person person = this.jdbcTemplate.queryForObject(
		        "select * from t_person where last_name = ?",
//		        "select id, first_name, last_name from t_person where last_name = ?",
		        new Object[]{"Bodine"},
		        new RowMapper<Person>() {
		            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Person person = new Person();
		            	int cols = rs.getMetaData().getColumnCount();
//		            	System.out.println("No. columns: "+cols);
//		            	for (int i = 0; i < cols; i++) {
//							System.out.println(rs.getMetaData().getColumnName(i+1));
//						}
		                person.setFirstName(rs.getString("first_name"));
		                person.setLastName(rs.getString("last_name"));
//		                System.out.println("id="+rs.getInt("id"));
		                return person;
		            }
		        });
		List<Person> persons = this.jdbcTemplate.query(
		        "select first_name, last_name from t_person",
		        new RowMapper<Person>() {
		            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		            	Person person = new Person();
		                person.setFirstName(rs.getString("first_name"));
		                person.setLastName(rs.getString("last_name"));
		                return person;
		            }
		        });
		List<Person> personsWithMapper = this.jdbcTemplate.query(
		        "select first_name, last_name from t_person",new PersonRowMapper());
	}
	
	static class PersonRowMapper implements RowMapper<Person> {

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Person person = new Person();
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            return person;
		}
		
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		System.out.println("BEFORE CLASS");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
//		System.out.println("AFTER CLASS");
	}

	@Before
	public void setUp() throws Exception {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		db = builder.setType(EmbeddedDatabaseType.HSQL)
				.addScript("/com/jgk/springrecipes/jdbc/embedded/hsqldb-schema.sql")
				.addScript("/com/jgk/springrecipes/jdbc/embedded/hsqldb-test-data.sql").build();
		assertNotNull(db);
		assertTrue(db instanceof DataSource);
		jdbcTemplate = new JdbcTemplate(db);
		assertNotNull(jdbcTemplate);

	}

	@After
	public void tearDown() throws Exception {
	    // do stuff against the db (EmbeddedDatabase extends javax.sql.DataSource)
	    db.shutdown();
	}

}
