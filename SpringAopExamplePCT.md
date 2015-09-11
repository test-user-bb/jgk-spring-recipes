## Artifacts ##
  * Java
    * Pojo component
    * Aspect pojo
    * test
  * Configuration
    * Spring config
    * log4j

## Java ##
### Pojo Component ###
```
@Component
public class PojoWithSetterMethod {

	private String description;
	
	public void setNothing() {
		System.out.println("SETTING NOTHING");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

```
### Pojo Aspect ###
```
@Aspect
@Component(value="pctAspect")
public class PropertyChangeTracker {
	private Logger log = Logger.getLogger(PropertyChangeTracker.class);
	
	@Before("execution(void set*(*)) or execution(void set*()) ")
	public void trackChange() {
		System.out.println("Property about to change");
		log.info("Property about to change");
	}
}

```
### Test ###
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/com/jgk/springrecipes/aop/pct/PctTest-config.xml"})
public class PctTest {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	PojoWithSetterMethod pojoWithSetterMethod;
	
	@Test
	public void testIt() {
		pojoWithSetterMethod.setNothing();
		pojoWithSetterMethod.setDescription("HOWDY FRIEND");
	}
}

```
## Configuration ##
### Spring config ###
```
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"       
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
							http://www.springframework.org/schema/aop
							http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
			              http://www.springframework.org/schema/context
			              http://www.springframework.org/schema/context/spring-context-3.0.xsd							
				              ">

	
	<context:component-scan base-package="com.jgk.springrecipes"></context:component-scan>
	<context:annotation-config />
	<aop:aspectj-autoproxy>
		<aop:include name="pctAspect" />
	</aop:aspectj-autoproxy>

</beans>
```
### log4j config ###
```
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
  <appender name="console" class="org.apache.log4j.ConsoleAppender"> 
    <param name="Target" value="System.out"/> 
    <layout class="org.apache.log4j.PatternLayout"> 
      <param name="ConversionPattern" value="%-5p %c{1} - %m%n"/> 
    </layout> 
  </appender> 

  <root> 
    <priority value ="info" /> 
    <appender-ref ref="console" /> 
  </root>
  
</log4j:configuration>
```