# [Spring JMX](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jmx.html) #

## JMX 2 questions ##
### Know the role of the `MBeanExporter` ###
  * core class in Spring's JMX framework is the `MBeanExporter`. This class is responsible for taking your Spring beans and registering them with a JMX `MBeanServer`.
  * `MBeanExporter` is activated by the following:
```
<context:mbean-export/>
```
### Write XML code to export a POJO as an MBean ###
```
 <bean id="someService" class="com.jgk.springrecipes.jmx.services.SomeServiceImpl"/>
 <bean class="org.springframework.jmx.export.MBeanExporter">
   <property name="beans">
      <map>
          <entry key="service:name=someService" value-ref="someService"/>
      </map>
   </property>
 </bean>
```
### Write XML code to register an existing MBean with the MBeanServer ###
```
<context:mbean-export/>
```

### Know purpose of `@ManagedResource`, `@ManagedOperation` and `@ManagedAttribute` ###
  * `@ManagedResource` - marks a bean for export to JMX.
  * `@ManagedOperation` - Each method you wish to expose as an operation must be marked with the `@ManagedOperation` annotation.
  * `@ManagedAttribute` - each property you wish to expose must be marked with the ManagedAttribute annotation.
  * Sample:
```
@ManagedResource(description="some desc of JMX mbean")
public class MyBeanImpl implements MyBean {
  @ManagedAttribute(description="some desc")
  public int getSomeInfoAboutTheBean() {
     // ...
     return 3;
  }
 @ManagedOperation(description="more descriptive info here")
 public void dosomething() {
    // code to do something
 }
 @ManagedAttribute(defaultValue="monkeyBurger", persistPeriod=300)
  public String getName() {
    return name;
  }
  @ManagedOperation(description="Add two numbers")
  @ManagedOperationParameters({
    @ManagedOperationParameter(name = "x", description = "The first number"),
    @ManagedOperationParameter(name = "y", description = "The second number")})
  public int add(int x, int y) {
    return x + y;
  }

}
```
### XML to create `MBeanServer` ###
  * Namespace:
```
<context:mbean-server/>
```
  * Traditional:
```
<bean id="mbeanServer"
     class="org.springframework.jmx.support.MBeanServerFactoryBean">
  <property name="locateExistingServerIfPossible" value="true"/>
</bean>
```