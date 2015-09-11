## [JavaConfig](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-java) 2 questions ##
### Identify the purpose of the `@Bean` annotation ###
  * @Bean-annotated methods that define instantiation, configuration, and initialization logic for objects to be managed by the Spring IoC container.
### Identify the purpose of the `@Configuration` annotation ###
  * Annotating a class with the @Configuration indicates that the class can be used by the Spring IoC container as a source of bean definitions.
### Write a method using `@Bean` ###
```
@Configuration
public class MyConfig {
	
	@Bean
	public SomeBeanToBeUsed someBeanToBeUsed() {
		SomeBeanToBeUsed someBeanToBeUsed = new SomeBeanToBeUsed();
		someBeanToBeUsed.setYetSomeOtherBean(yetSomeOtherBean());
		return someBeanToBeUsed;
	}
	@Bean
	public YetSomeOtherBean yetSomeOtherBean() {
		return new YetSomeOtherBean();
	}
}
```
### Write code using one `@Bean` method that calls another `@Bean` method ###
```
@Configuration
public class MyConfig {
	
	@Bean
	public SomeBeanToBeUsed someBeanToBeUsed() {
		SomeBeanToBeUsed someBeanToBeUsed = new SomeBeanToBeUsed();
		someBeanToBeUsed.setYetSomeOtherBean(yetSomeOtherBean());
		return someBeanToBeUsed;
	}
	@Bean
	public YetSomeOtherBean yetSomeOtherBean() {
		return new YetSomeOtherBean();
	}
}

```
### What if you do this on a class? ###
```
Caused by: java.lang.IllegalStateException: CGLIB is required to process @Configuration classes. Either add CGLIB to the classpath or remove the following @Configuration bean definitions: [myConfig]

```
### Maven Dependencies ###
```
  <properties>
    <cglib.version>2.2</cglib.version>
  </properties>
  <dependencies>
	<dependency>
		<groupId>cglib</groupId>
		<artifactId>cglib-nodep</artifactId>
		<version>${cglib.version}</version>
	</dependency>
  </dependencies>
```
### NOTES ###
  * Beware that, in order for JavaConfig to work, you must include the CGLIB jar in your list of dependencies.
  * There are a few restrictions due to the fact that CGLIB dynamically adds features at startup-time:
    * Configuration classes should not be final
    * They should have a constructor with no arguments