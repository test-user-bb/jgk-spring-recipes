  * **PropertyPlaceholderConfigurer**
    * class to externalize the deployment details into a properties file
    * access from bean configuration file using special format – ${variable}
  * [Spring Converters Examples](http://www.mkyong.com/spring/spring-collections-list-set-map-and-properties-example/)
  * Spring TestContext Framework
    * The Spring TestContext Framework (located in the org.springframework.test.context package) provides generic, annotation-driven unit and integration testing support that is agnostic of the testing framework in use, whether JUnit 3.8.2, JUnit 4.5+, TestNG 5.10, and so on.
    * TestContext framework also places a great deal of importance on convention over configuration with reasonable defaults that can be overridden through annotation-based configuration.
  * Bunch 'o Annotations
    * `@Autowired`
    * `@Qualifier`
    * `@Resource (javax.annotation) if JSR-250 is present`
    * `@Inject (javax.inject) if JSR-330 is present`
    * `@Named (javax.inject) if JSR-330 is present`
    * `@Provider (javax.inject) if JSR-330 is present`
    * `@PersistenceContext (javax.persistence) if JPA is present`
    * `@PersistenceUnit (javax.persistence) if JPA is present`
    * `@Required`
    * `@Transactional`

  * Spring testing annotations:
    * `@ContextConfiguration`
    * `@DirtiesContext`
    * `@TestExecutionListeners`
    * `@TransactionConfiguration`
    * `@Rollback`
    * `@BeforeTransaction`
    * `@AfterTransaction`
    * `@IfProfileValue (junit-related)`
    * `@ProfileValueSourceConfiguration (junit-related)`
    * `@ExpectedException (junit-related)`
    * `@Timed (junit-related)`
    * `@Repeat (junit-related)`
  * [Spring Integration Testing](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/testing.html#integration-testing)
  * [Spring Integration Testing](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/testing.html#integration-testing) Goals
    * Spring IoC container caching between test execution.
    * Dependency Injection of test fixture instances.
    * Transaction management appropriate to integration testing.
    * Spring-specific support classes that are useful in writing integration tests.
  * when a cyclic dependency use setter injection
  * Automatic Value Type Conversion:
    * bean definition:
```
<bean id="person" class="com.jgk.people.Jed">
  <property name="favoriteNumber" value="42"/>
</bean>
```
    * translation:
```
Person jed = new Jed();
Integer favoriteNumber = 42;
person.setFavoriteNumber(favoriteNumber);
```
  * Lists:
```
	<bean id="jed" class="com.jgk.springrecipes.simplecontext.beans.PersonImpl">
 		<constructor-arg name="lastName" value="Clampett"></constructor-arg>
		<constructor-arg name="firstName" value="Jed"></constructor-arg>
		<property name="favoriteNumber" value="43"></property>
		<property name="children">
		  <list>
		  	<ref bean="jethro"/>
		  	<ref bean="elliemae"/>
		  	<bean class="com.jgk.springrecipes.simplecontext.beans.PersonImpl">
				<property name="favoriteNumber" value="87"></property>
		  	</bean>
		  </list>
		</property>
	</bean>
```
  * Spring manages the lifecycle of beans used in an application.
  * ApplicationContext is the container.  It holds bean implementations chosen for the current deployment.  As such, it conceals the implementation details.
  * Common way to get an ApplicationContext
    * ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml")
  * Types of Spring Configuration Information
    * XML
    * Annotations
    * JavaConfig
    * Factory Method
  * Spring Triangle:
    * Dependency Injection (DI)
    * Aspect-Oriented Programming (AOP)
    * Enterprise Service Abstractions
  * Spring Chronology:
    * Spring 3.0 (12/09)
      * requires Java 1.5+ and JUnit 4.7+
      * REST Support, JavaConfig, SpEL, even more annotations
    * Spring 2.5 (11/07, latest 2.5.6)
      * Requires Java 1.4+, supports JUnit 4
      * Annotation DI, @MVC controllers, XML namespaces
    * Spring 2.0 (10/06, latest 2.0.8)
      * JDK 1.3+
      * XML simplification
      * Asynchronous JMS
      * JPA
      * AspectJ support
  * Spring Testability:
    * easier to test pieces of application in isolation
    * decouples objects from environment
    * Junit annotations:  @Test, @Before, @After
  * Spring Data Access
    * manages resources (connections, transactions)
    * provides API helpers (templates)
    * consistent approach to data access
    * Example:  int count = jdbcTemplate.queryForInt("SELECT COUNT( `*` ) FROM PATIENT");
      * Gets connection (from pool)
      * Uses a transaction (if required)
      * Executes the statement
      * Process the result set
      * Handles exceptions
      * Releases the connection
    * Spring supports many data access technologies, including:
      * JDBC
      * Hibernate
      * JPA
      * iBatis
  * DAO = Repository:  represent your persistence source (database, file system, etc.)
  * Dependency Injection (DI)
    * enables different collaborators to be injected for different environments. (test, production, test database, real/production database)
    * enables testing of classes independent of their collaborators.
  * Constructor Injection advantages:
    * mandatory elements
    * immutable
    * natural to Java
  * Setter Injection advantages:
    * avoid long constructor parameter lists
    * can be inherited
    * clearer/easier to understand since the properties have names
    * constructor args do not have names
  * [Preparation Video](http://www.youtube.com/watch?v=biNx-t2busI)
  * In Spring a rollback of a transaction occurs in response to a RuntimeException, but if a checked exception is thrown then no rollback.  You can configure so a specific checked exception causes a rollback.