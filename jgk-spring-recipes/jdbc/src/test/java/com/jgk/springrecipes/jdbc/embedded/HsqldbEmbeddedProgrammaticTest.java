package com.jgk.springrecipes.jdbc.embedded;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class HsqldbEmbeddedProgrammaticTest {
	
	
	@Test
	public void basis() {
		System.out.println("BASIS");
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
				.addScript("/com/jgk/springrecipes/jdbc/embedded/hsqldb-schema.sql")
				.addScript("/com/jgk/springrecipes/jdbc/embedded/hsqldb-test-data.sql").build();
		assertNotNull(db);
	    // do stuff against the db (EmbeddedDatabase extends javax.sql.DataSource)
	    db.shutdown();
	    
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
		System.out.println("BEFORE");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("AFTER");
	}

}
