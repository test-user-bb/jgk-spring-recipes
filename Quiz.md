  * @Required vs. @Autowired
    * [@Required](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/beans/factory/annotation/Required.html) and [@Autowired](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html) have some overlapping capabilities.
    * I think the difference in behavior can be traced to when they were introduced.  @Required is older (since 2.0) than @Autowired (since 2.5).
    * @Required marks a method (only a method) as being required. This is usually on a setter method.
    * @Autowired is more powerful.  It can be applied to constructor, field, setter method or even a config method.
    * See the [javadoc](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/)

  * @Required is useful when you are using xml to configure beans.  In this context, a bean is configured with xml.  As such, a bean may have a method annotated with @Required.  That means that the xml configured bean is expected to have a `<property/>` clause with a property satisfying the setter method.
    * An example may help in understanding:
      * Enveloping class using @Required:
```
public class SomeXmlConfiguredSpringBeanObject {

	Runtimer runtimer;
	@Required
	public void setRuntimer(Runtimer runtimer) {
		this.runtimer = runtimer;
	}
}

```
      * Spring configuration in xml:
```
	<bean id="sxc" class="com.jgk.simplespring.SomeXmlConfiguredSpringBeanObject">
		<property name="runtimer" ref="runtimer"></property>
	</bean>
```
    * If you load the application context with this setup, the sxc bean will successfully be created since the **runtimer** property is satisfied.  However, if you comment out the `<property/>` clause and reload the application context you will get: `org.springframework.beans.factory.BeanInitializationException: Property 'runtimer' is required for bean 'sxc'`
  * How does Spring respond if a **@Required** annotation is not satisfied?
    * _`BeanInitializationException`_
```
Caused by: org.springframework.beans.factory.BeanInitializationException: Property 'personName' is required for bean 'simpleBeanWithRequiredAnnotation'
	at org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor.postProcessPropertyValues(RequiredAnnotationBeanPostProcessor.java:149)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1074)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:517)

```
  * How do you tell Spring framework to recognized the **@Required** annotation?
    * Two ways:
      * Register a bean with `class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor"`
      * Use the xml namespace element:  

&lt;context:annotation-config/&gt;


  * How is the @Required annotation used?
    * Here's a bean class:
```
public class SimpleBeanWithRequiredAnnotation {
	
	private String personName;

	@Required
    public void setPersonName(String thePersonName) {
        this.personName = thePersonName;
    }
}
```
  * What `BeanPostProcessor` provided by the Spring framework enforces that properties are set?
    * **@Required**
  * NOTE: an init method may have any visibility, but must have **no arguments**.
  * NOTE: when using _init-method_ attribute, you can only have a single _init-method_.
  * NOTE: if you use `@PostConstruct` annotation, you can have multiple bean initialization opportunities.
  * What is the purpose of 

&lt;context:annotation-config/&gt;

_?
    * Enables the following bean post processors:
      * `RequiredAnnotationBeanPostProcessor`
      * `CommonAnnotationBeanPostProcessor`
  * How to get Spring to recognize common annotations?
    * Method 1:
```
   <bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"/>
```
    * Method 2:
```
   <context:annotation-config/> 
```
  * Bean Initialization
    * Bean can register for one or more initialization callbacks.
      * JSR-250 `@PostConstruct`
      * <bean ... init-method=""/>
      * Implement Spring's `InitializingBean` interface (ties you to Spring)
  * What is autowiring?
    * A process by which Spring detects dependencies between a bean and its properties based on introspection (Java reflection).
    * Dependencies are inferred without the need for explicit XML configuration.
    * Autowiring can be implemented in XML or Annotations.
    * Autowiring is disabled by default.
  * Use this XML element to enable component scanning:
```
    <context:component-scan base-package="..."/>
```
  * How do you inform Spring of the need to need to process annotations?
```
   <context:annotation-config/>
```
  * Component scanning eliminates the need for this in an XML file:
    *_

&lt;bean&gt;


  * Two styles of autowiring are:
    * by name
    * by type
  * Autowiring eliminates the need for what 2 XML tags?
    * 

&lt;constructor-arg&gt;


    * 

&lt;property&gt;


  * What do you use to programmatically create an `ApplicationContext`?
    * JavaConfig
  * **@Bean** annotation is `JavaConfig`'s equivalent of what XML tag?
```
    <bean>
```
  * Are Mocks and Stubs the Same Thing?
    * No
      * Use stubs for state verification
      * A stub allows a unit test to define responses to method calls (return values, exceptions) to simulate any object on which the component being tested might depend.
      * Use mocks for behavior verification.
      * A mock allows a unit test to verify behavior. (e.g. _Did a method execute?_)
  * How do you enable component scanning?
```
 <context:component-scan base-package="..."/>
```
  * How do you tell the Spring configuration to process annotations?
    * Use the following:
```
<context:annotation-config/>
```
  * What is a special feature of the **`@Repository`** annotation not available to the **`@Component`** annotation?
    * Automatic conversion of `SQLException`s
  * Accessing Static Constants?
```
<bean id="myBean" class="com.jgk.beans.PresidentBean">
   <property name="dayOfWeek">
      <util:constant static-field="java.util.Calendar.MONDAY"/>
   </property>
</bean>
```
    * In the pre-2.0 days, you needed to use:
```
<bean id="myBean" class="com.jgk.beans.PresidentBean">
   <property name="dayOfWeek">
      <bean id="java.util.Calendar.MONDAY"
         class="org.springframework.beans.factory.config.FieldRetrievingFactoryBean"/>
   </property>
</bean>
```
  * util-namespace
    * shortens declaration of different types of collections
```
xmlns:util="http://www.springframework.org/schema/util"
xsi:schemaLocation="http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-2.0.xsd"
```
  * p-namespace
    * shortens property declarations
  * Differences between **id** and **name** attribute of a bean tag:
    * id requires XML syntax
    * id only holds a single value
    * name can incorporate non-standard characters
  * What is the purpose of bean definition inheritance?
    * To reuse/recycle common properties
  * 12 steps in the life of a Spring bean
    * Instantiate ApplicationContext.
    * Parse XML.
    * Bean Definitions loaded into ApplicationContext.
    * BeanFactoryPostProcessor invoked.
    * Instantiate bean.
    * Dependency injection wires bean dependencies.
    * BeanPostProcessor initializes bean.
    * BeanPostProcessor injects proxies.
    * Get bean.
    * Invoke business method on bean.
    * Close ApplicationContext.
    * Clean up bean.
  * Singletons in Spring:
    * Only one instance is created no matter how many times ctx.getBean() is called.
    * Should be stateless.
  * What is scope?
    * Specifies the lifecycle of a bean:
      * When bean is created
      * When bean is destroyed
  * ApplicationContext Destruction cycle:
    * Any unreferenced beans acquired from the ApplicationContext will be destroyed.
    * Referenced beans/proxies will continue to exist and be functional.
    * ApplicationContext itself will be destroyed.
    * Unable to request additional services from the ApplicationContext.
  * What is a proxy?
    * A surrogate
    * An object that substitutes for another object.  Exposes the same interface as the object it substitutes for.
    * Is exposed to the client in lieu of the object it substitutes for.
    * Provides services before/after the object is substitutes for. (AROUND)
  * Benefits of the Proxy Pattern?
    * Wraps services transparently around a business component.
    * NOTE: This is how AOP is implemented in Spring.
  * What are the costs of the Proxy Pattern?
    * Extra layer of processing is added to method calls.
  * Chronology of post processors:
    * BeanFactoryPostProcessor executes before bean instances have been created.
    * BeanPostProcessor executes after bean instances have been created.
  * NOTE: Information is available to a BeanPostProcessor that is not available to a BeanFactoryPostProcessor.
  * How does a BeanPostProcessors differ from its predecessor, a BeanFactoryPostProcessor?
    * BeanFactoryPostProcessor configures the factory (initializes the meta data).
    * BeanPostProcessor configures the instance (initializes data).
  * What is a callback method?
    * A method invoked on a bean by its container following key events in the life cycle of the bean.
    * Allows the bean to synchronize application logic with the infrastructure on which the bean depends.
    * A manifestation of inversion of control (IoC).
  * What is lazy instantiation?
    * Instantiation happens when the object is first used
  * What is eager instantiation?
    * Instantiation occurs upon declaration
    * Good when you KNOW the object will be used
  * Spring configuration file header:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				              http://www.springframework.org/schema/context
				              http://www.springframework.org/schema/context/spring-context-3.0.xsd"							

</beans>
```
  * Common DataSource Properties:
    * url
    * user
    * password
    * dialect
  * Advantages of using annotations?
    * An alternative to XML for configuration.
    * Metadata is bundled with the class.
    * Provides syntactic separation of configuration from application logic.
  * Disadvantages/Costs of using annotations?
    * Need to recompile the source code every time an annotation is modified.
  * What are java annotations?
    * A special form of syntax used to declare metadata in a Java source file.
    * Annotations are used by frameworks as a way of conveniently applying behaviours to user-defined classes and methods that would otherwise be declared in some external source (such as an XML configuration file) or programmatically (with API calls).
  * This annotation will generate an exception at deployment time if you have not injected a necessary property:
```
     @Required
```
  * How to shorten the declaration of a PropertyPlaceholderConfigurer?
```
	<context:property-placeholder location="classpath:myspringproperties.properties"/>
```
  * Full Spring Lifecycle:
    * Allocate ApplicationContext
    * Parse XML
    * Create BeanDefinition Objects
    * Add BeanDefinitions to ApplicationContext
    * Invoke BeanFactoryPostProcessor methods
    * Instantiate Bean
    * Perform dependency injection on bean to initialize dependencies
    * Invoke BeanPostProcessor methods
    * Get bean from ApplicationContext
    * Use bean
    * When application is shutdown beans are destroyed
  * Main phases of spring application:
    * Initialization
    * Use
    * Destruction
  * JSR-250
    * Web-Page  http://jcp.org/en/jsr/detail?id=250
    * Create standard annotations to be used instead of XML configuration.
      * @Generated	Marks sources that have been generated
      * @Resource	Declares a reference to a resource, e.g. a database
      * @Resources	Container for multiple Resource annotations
      * @PostConstruct	Is used on methods that need to get executed after dependency injection is done to perform any initialization.
      * @PreDestroy	Is used on methods that are called before the instance is removed from the container
      * @RunAs	Defines the role of the application during execution in a Java EE container
      * @RolesAllowed	Specifies the security roles permitted to access method(s) in an application.
      * @PermitAll	Specifies that all security roles are permitted to access the annotated method, or all methods in the annotated class.
      * @DenyAll	Specifies that no security roles are allowed to invoke the specified method(s).
      * @DeclareRoles	Used to specify the security roles by the application.
  * For most namespaces you must provide these 3 things:
    * Namespace name   (xmlns:context="http://www.springframework.org/schema/context")
    * Namespace location (xsi:schemaLocation="http://www.springframework.org/schema/context")
    * Namespace xsd file (http://www.springframework.org/schema/context/spring-context-3.0.xsd)
  * What is the purpose of using xml namespaces in bean configuration files?
    * To reduce the amount of xml configuration required to wire beans together.
  * What is PropertyPlaceholderConfigurer an example of?
    * A BeanFactoryPostProcessor
  * What is Bean Factory Post Processing?
    * Applies transformations to groups of bean definitions _before_ any objects are actually created.
    * Implemented using BeanFactoryPostProcessor
  * How do you make a bean that is simply a File?
```
  Answer:  use constructor-arg
	 <bean id="someFile" class="java.io.File">
	    <constructor-arg value="/tmp/passwd"/>
	 </bean>
```
  * How do you make a bean that is simply a String?
```
  Answer:  use constructor-arg
	 <bean id="someString" class="java.lang.String">
	    <constructor-arg value="Jed Clampett as some string"/>
	 </bean>
```
  * How do you make a bean that is simply an Integer?
```
  Answer:  use constructor-arg
	 <bean id="someNumber" class="java.lang.Integer">
	    <constructor-arg value="543"/>
	 </bean>
```
  * Example of Constructor injection:
```
		<bean id="person" class="PersonImpl">
			<constructor-arg value="32"/>
		</bean>
```
  * What is disambiguation?
    * The process of declaring Spring properties in an unambiguous manner.
  * What Types of Conversions is Spring Capable of Out of the Box?
    * Java primitives
    * Byte arrays
    * String arrays
    * Java wrapper classes
    * Java classes
    * String
    * java.lang.Class
    * java.net.URL
    * java.io.File
    * java.util.Locale
    * java.util.Properties
    * java.util.Collection
  * Url type conversion example:
```
  <property name="url" value="http://www.kroubalkian.com"/>  
```
  * String array conversion example:
```
     <property name="teamNames"
				  value="Blue Dolphins,Blue Dragons,Killer Bees, Pink Bonnets"/>
```
  * When does Spring create and wire your components together?
    * when you instantiate the ApplicationContext (or BeanFactory)
  * What is the effect of wiring for both constructor injection and setter injection? _(silly to do this)_
    * Both injections occur in the following order:
      1. constructor injection
      1. setter injection
  * The name of the class representing a Spring Container.
    * ApplicationContext
  * The 3 dimensions of the Spring Triangle.
    * Dependency Injection.
    * Aspect Oriented Programming.
    * Enterprise Extensions.
  * Spring uses this to generate code to wire beans together.
    * Dependency Injection.
  * The 3 techniques for wiring component together.
    * Constructor injection.
    * Setter injection.
    * Factory method injection.
  * The XML element for constructor injection.
    * constructor-arg
  * The XML element for setter injection.
    * property
  * An XML element for factory injection.
    * factory-method
  * This distinguishes a prototype.
    * One instance per getBean invocation.
  * Used to inject a reference to a named bean.
    * ref=...
  * Used to inject a scalar value.
    * value=...
  * Use this XML attribute when a constructor has been overloaded to support parameters of different types.
    * type=...
  * Use this XML attribute when a constructor has been overloaded to support multiple parameters of the same type.
    * index=...
  * The 3 types of `ApplicationContext`s.
    * `Classpath`
    * `FileSystem`
    * `WebApplication`