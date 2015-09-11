# Introduction #
  * [Docs](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jmx.html)
## Spring JMX ##
  * Motivation:
    * JMX API is difficult and complex
    * Goal of Spring's JMX support is to simplify the use of JMX while hiding the complexity of the API
  * Goals:
    * Configuration JMX infrastructure
      * Declaratively using:
        * context namespace or
        * FactoryBeans
    * Exposing Spring beans as MBeans
      * Annotation based metadata
      * Declaratively using Spring bean definitions
    * Consuming JMX managed beans
      * transparently using a proxy-based mechanism
    * To locate or create and MBeanServer declaratively:
      * Use context namespace
```
           <context:mbean-server/>
```
      * Or declare MBeanServer explicitly:
```
           <bean id="mbeanServer"
                 class="org.springframework.jmx.support.MBeanServerFactoryBean">
              <property name="locateExistingServerIfPossible" value="true"/>
           </bean>
```
    * Spring JMX Annotations (`org.springframework.jmx.export.annotation.*`):
```
        @ManagedResource - class-level
        @ManagedAttribute - method-level, becomes JMX attribute
        @ManagedOperation - method-level, becomes JMX operation
        @ManagedMetric
        @ManagedNotification
        @ManagedNotifications
        @ManagedOperationParameter
        @ManagedOperationParameters
```
    * How to export a bean as a JMX MBean using Annotations:
      * Annotate your class (@ManagedResource) and methods (@ManagedAttribute, @ManagedOperation) to be exposed
      * Activate exporter in Spring configuration:
```
        <context:mbean-export/>
```
      * More detail:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:jdbc="http://www.springframework.org/schema/jdbc"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
              http://www.springframework.org/schema/jdbc
              http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
              http://www.springframework.org/schema/context
              http://www.springframework.org/schema/context/spring-context-3.0.xsd">

	<context:mbean-export/>

   ...more configuration...

</beans>
```
      * ObjectName(s) derived from fully qualified class name or passwed as attribute to `@ManagedResource` as in:  ` @ManagedResource(objectName = "statistics:name=monitorFactory") `
    * ` MBeanExporter ` (from Spring)
      * Transparently exposes an existing POJO bean to the MBeanServer
        * so you don't have to write all the MBean registration code
      * Out-of-the-box you do not need to create an explicit MBean management interface or create an ` ObjectName ` instance.
        * Spring JMX uses reflection to manage all properties and methods
        * Spring JMX uses map key as the ` ObjectName `
      * To export a bean as an MBean (no annotations used):
        * Use an existing POJO Bean:
```
         <bean id="myService" class="com.jgk.springrecipes.jmx.services.MyServiceImpl"/>
```
        * Use the ` MBeanExporter ` to export it:
```
     <bean class="org.springframework.jmx.export.MBeanExporter">
        <property name="beans">
              <map>
                    <entry key="service:name=myService" value-ref="myService"/>
              </map>
        </property>
     </bean>
```
      * Automatically exporting preexisting MBeans [article on StatisticsService](http://raibledesigns.com/wiki/Wiki.jsp?page=HibernateJMX), some beans are MBeans:
        * Hibernate's ` org.hibernate.jmx.StatisticsService `
```
    <context:mbean-export/>
    <bean id="statisticsService" class="org.hibernate.jmx.StatisticsService">
        <property name="sessionFactory" ref="mySessionFactory"/>
    </bean>
	<bean id="mySessionFactory"
		class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		<property name="dataSource" ref="myDataSource" />
		<property name="mappingResources">
			<list>
				<value>com/jgk/springrecipes/orm/hibernate/domain/Event.hbm.xml</value>
			</list>
		</property>
		<property name="hibernateProperties">
			<value>
				hibernate.dialect=org.hibernate.dialect.HSQLDialect
				hibernate.show_sql=true
				<!-- Drop and re-create the database schema on startup -->
				hibernate.hbm2ddl.auto=update
				<!-- Need the following for statistics -->
                                hibernate.generate_statistics=true
      		</value>
		</property>
	</bean>

```
        * Log4j's ` org.apache.log4j.jmx.LoggerDynamicMBean `
          * The following xml exposes the logger for org.springframework.jmx as a JMX MBean. [article](http://www.olivergierke.de/wordpress/2010/10/spring-logging-and-jmx/)
```
    <context:mbean-export/>

    <bean class="org.apache.log4j.jmx.LoggerDynamicMBean">

        <constructor-arg>

             <bean class="org.apache.log4j.Logger" factory-method="getLogger">
                  <constructor-arg value="org.springframework.jmx"/>
             </bean>

        </constructor-arg>

    </bean>

```
      * Summarized Spring JMX:
        * Easy export of spring managed beans to JMX MBeanServer
        * Use ` <context:mbean-server/> ` to create MBean server
        * Use Spring annotations to declare JMX metadata
        * Use ` <context:mbean-export/> ` to automatically export preexisting MBean(s)


## JMX Learning ##
  * JMX Architecture
    * MBeanServer acts as broker for communication between:
      * Multiple local MBean(s)
      * Remote clients and remote MBean(s)
      * Clients available ([jconsole](http://download.oracle.com/javase/1.5.0/docs/guide/management/jconsole.html), jvisualvm, Hyperic)
  * MBean
    * an object with management metadata
    * an MBean is an object with additional management metadata
      * Attributes (properties)
      * Operations (methods)
    * MBean management metadata can be defined statically with a Java interface or defined dynamically at runtime.
      * Simple MBean - statically defined MBean with a Java interface
      * Dynamic MBean - metadata defined dynamically at runtime
  * Goals of JMX - Java Management Extensions:
    * A standard, dynamic way of managing and monitoring resources such as applications, devices, and services.
    * Databases.
    * Message services.
    * WAR modules.
    * EJB modules.
    * Application components.
    * Application servers.
  * Things you can do with JMX:
    * Retrieve info about and application at runtime
    * Dynamically reconfiguration application (while it's running)
    * Trigger operations inside an application


## JMX Info ##
  * [JMX](http://java.sun.com/javase/technologies/core/mntr-mgmt/javamanagement/)
  * [JMX Spec (JSR-000003)](http://jcp.org/aboutJava/communityprocess/final/jsr003/index3.html)
  * [JMX Remote API](http://jcp.org/aboutJava/communityprocess/final/jsr160/index.html)
  * [MX4J (open source JMX implementation](http://mx4j.sourceforge.net/)
  * [JMX: Getting Started](http://java.sun.com/developer/technicalArticles/J2SE/jmx.html)