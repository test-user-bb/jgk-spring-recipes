package com.jgk.springrecipes.jdbc.embedded;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class HsqldbEmbeddedProgrammaticTest {
	private EmbeddedDatabase db;
	
	@Test
	public void basis() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(db);
		Integer rowCount = jdbcTemplate.queryForInt("select count(*) from t_person");
		assertNotNull(rowCount);
		assertEquals(Integer.valueOf(3), rowCount);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("BEFORE CLASS");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AFTER CLASS");
	}

	@Before
	public void setUp() throws Exception {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		db = builder.setType(EmbeddedDatabaseType.HSQL)
				.addScript("/com/jgk/springrecipes/jdbc/embedded/hsqldb-schema.sql")
				.addScript("/com/jgk/springrecipes/jdbc/embedded/hsqldb-test-data.sql").build();
		assertNotNull(db);
		assertTrue(db instanceof DataSource);
	}

	@After
	public void tearDown() throws Exception {
	    // do stuff against the db (EmbeddedDatabase extends javax.sql.DataSource)
	    db.shutdown();
	}

}
