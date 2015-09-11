  * [javadoc](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/)
  * Embedded database usage - create programmatic implementation and a declarative implemenation.
    * JDBC Embedded - programmatic
    * JDBC Embedded - declarative
  * Spring config file with jdbc namespace:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:jdbc="http://www.springframework.org/schema/jdbc"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
							http://www.springframework.org/schema/jdbc
							http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
							
				              "							
              >
   <jdbc:embedded-database id="dataSource">
        <jdbc:script location="classpath:schema.sql"/>
        <jdbc:script location="classpath:test-data.sql"/>
    </jdbc:embedded-database>    
</beans>
```
  * [Spring JDBC Reference](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html)
  * Maven jdbc dependencies:
```
	<dependency>
		<groupId>org.hsqldb</groupId>
		<artifactId>hsqldb</artifactId>
		<version>2.0.0</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
	<dependency>
		<groupId>com.h2database</groupId>
		<artifactId>h2</artifactId>
		<version>1.2.147</version>
	</dependency>
	<dependency>
		<groupId>org.apache.derby</groupId>
		<artifactId>derby</artifactId>
		<version>10.7.1.1</version>
	</dependency>  

```
  * To get something going you need a bit of scaffolding.  Namely, you need some kind of database.  Spring 3 provides embedded databases.
  * config:
```
<jdbc:embedded-database id="dataSource">
        <jdbc:script location="classpath:schema.sql"/>
        <jdbc:script location="classpath:test-data.sql"/>
</jdbc:embedded-database>
```
  * config: explicit HSQL
```
<jdbc:embedded-database id="dataSource" type="HSQL">
        <jdbc:script location="classpath:schema.sql"/>
        <jdbc:script location="classpath:test-data.sql"/>
</jdbc:embedded-database>
```
  * config: explicit H2
```
<jdbc:embedded-database id="dataSource" type="H2">
        <jdbc:script location="classpath:schema.sql"/>
        <jdbc:script location="classpath:test-data.sql"/>
</jdbc:embedded-database>
```
  * config: explicit Derby
```
<jdbc:embedded-database id="dataSource" type="Derby">
        <jdbc:script location="classpath:schema.sql"/>
        <jdbc:script location="classpath:test-data.sql"/>
</jdbc:embedded-database>
```
  * default:
```
public class DataAccessUnitTestTemplate {
    private EmbeddedDatabase db;
    
    @Before
    public void setUp() {
        // creates a HSQL in-memory db populated from default scripts classpath:schema.sql and classpath:test-data.sql
        db = new EmbeddedDatabaseBuilder().addDefaultScripts().build();		
    }

    @Test
    public void testDataAccess() {
        JdbcTemplate template = new JdbcTemplate(db);
        template.query(...);
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}

```