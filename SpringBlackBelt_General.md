## General 3 questions ##

### Define Dependency Injection and Inversion of Control ###
  * _**Dependency Injection** is a process whereby objects define their dependencies, that is, the other objects they work with, only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse, hence the name **Inversion of Control (IoC)**, of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes, or a mechanism such as the Service Locator pattern._
  * Dependency Injection [HERE](http://static.springsource.org/spring/docs/3.0.x/reference/beans.html)
  * Inversion of Control (IoC) [HERE](http://static.springsource.org/spring/docs/3.0.x/reference/beans.html)
### List the advantages of Dependency Injection ###
  * Container manages the lifecycle of the application. (e.g. all beans are fully initialized before they are used)
  * Beans are created in the correct order based on their dependencies.
  * Each bean is bound to a unique id.  The id of the bean, by convention, reflects the service the bean provides to clients.
  * The container (`ApplicationContext`) encapsulates the bean implementations chosen for a given deployment.  In other words, the container _conceals implementation details_.

### Be able to write XML code for constructor injection ###
```
<bean id="someBean" class="com.jgk.springrecipes.SomeBean">
  <constructor-arg ref="someOtherBean"/>
</bean>
<bean id="someOtherBean" class="com.jgk.springrecipes.SomeOtherBean"/>
```
### Be able to write XML code for setter injection ###
```
	<bean id="someBeanWithProperties" class="com.jgk.springrecipes.blackbelt.general.beans.SomeBeanWithProperties">
	   <property name="someOtherBean" ref="yetAnotherBean"/>
	   <property name="someInteger" value="3"/>
	   <property name="someText" value="Hello, jgk-spring-recipes"/>
	</bean>
	<bean id="yetAnotherBean" class="com.jgk.springrecipes.blackbelt.general.beans.SomeOtherBean"/>	
```
### List the (non-web) scopes for beans ###
  * singleton
  * prototype
### Identify the default scope for beans ###
  * singleton