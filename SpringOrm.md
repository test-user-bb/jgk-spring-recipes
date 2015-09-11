  * [Spring ORM](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/orm.html)
  * [javadoc](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/)
  * The recommended integration style is to code DAOs against plain Hibernate, JPA, and JDO APIs. The older style of using Spring's DAO templates is no longer recommended.
  * Dependencies:
```
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-orm</artifactId>
		<version>3.0.5.RELEASE</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
```

### Hibernate ###
  * [Spring Hibernate](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/orm.html#orm-hibernate)
  * As of Spring 3.0, Spring requires Hibernate 3.2 or later.
  * [Hibernate Tutorial First App](http://docs.jboss.org/hibernate/core/3.5/reference/en-US/html/tutorial.html#tutorial-firstapp-setup)

  * Hibernate Maven Dependency
```
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate</artifactId>
		<version>3.5.0</version>
		<classifier>Final</classifier>
	</dependency>
  	<dependency>
  		<groupId>org.javassist</groupId>
  		<artifactId>javassist</artifactId>
  		<version>3.14.0-GA</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>
	<dependency>
		<groupId>javax.transaction</groupId>
		<artifactId>jta</artifactId>
		<version>1.1</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
	<dependency>
		<groupId>dom4j</groupId>
		<artifactId>dom4j</artifactId>
		<version>1.6.1</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
	<dependency>
		<groupId>org.slf4j</groupId>
		<artifactId>slf4j-log4j12</artifactId>
		<version>1.6.1</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
	<dependency>
		<groupId>commons-collections</groupId>
		<artifactId>commons-collections</artifactId>
		<version>3.2.1</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>

```

  * When I added Hibernate 3.5.0-Final dependency I discovered I needed all of the following:
```
```