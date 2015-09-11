## Sumary ##
  * Maven webapp
  * add dependencies
  * add logging config
  * add spring config
  * modify web.xml to add context
  * add spring web config
  * modify web.xml to add dispatcher servlet
  * add controllers
  * add views
  * add domain objects
  * add repository
  * add persistence.xml
  * add entity manager support
  * run it
### Maven webapp ###
  * use maven webapp archetype
```
 $ mvn archetype:create -DgroupId=com.jgk.springrecipes.app -DartifactId=release-webapp -DarchetypeArtifactId=maven-archetype-webapp
```

### add dependencies ###
  * spring, hibernate, jpa
```
  <properties>
	  <junit.version>4.8.2</junit.version>
	  <spring.version>3.0.5.RELEASE</spring.version>
	  <org.hibernate.version>3.5.6-Final</org.hibernate.version>
	  <slf4j-api.version>1.6.1</slf4j-api.version>
	  <slf4j-log4j12.version>1.6.1</slf4j-log4j12.version>
	  <log4j.version>1.2.12</log4j.version>
	  <hibernate-commons-annotations.version>3.2.0.Final</hibernate-commons-annotations.version>
	  <hibernate-jpa-2.0-api.version>1.0.0.Final</hibernate-jpa-2.0-api.version>
  </properties>

  <dependencies>
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-annotations</artifactId>
		<version>${org.hibernate.version}</version>		
	</dependency>
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-commons-annotations</artifactId>
		<version>${hibernate-commons-annotations.version}</version>		
	</dependency>
	<dependency>
		<groupId>org.hibernate</groupId>
		<artifactId>hibernate-entitymanager</artifactId>
		<version>${org.hibernate.version}</version>		
	</dependency>
	<dependency>
	  <groupId>org.hibernate.javax.persistence</groupId>
	  <artifactId>hibernate-jpa-2.0-api</artifactId>
	  <version>${hibernate-jpa-2.0-api.version}</version>
	</dependency>  
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.6.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.6.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.12</version>
      <scope>compile</scope>
    </dependency>
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>servlet-api</artifactId>
  		<version>2.5</version>
  		<type>jar</type>
  		<scope>provided</scope>
  	</dependency>
  	<dependency>
  		<groupId>javax.servlet.jsp</groupId>
  		<artifactId>jsp-api</artifactId>
  		<version>2.1</version>
  		<type>jar</type>
  		<scope>provided</scope>
  	</dependency>
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>jstl</artifactId>
  		<version>1.2</version>
  		<type>jar</type>
  	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-core</artifactId>
		<version>${spring.version}</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-web</artifactId>
		<version>${spring.version}</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-webmvc</artifactId>
		<version>${spring.version}</version>
		<type>jar</type>
		<scope>compile</scope>
	</dependency>

  </dependencies>

```
### add logging config ###
  * log4j.xml - place on the classpath
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
  <appender name="console" class="org.apache.log4j.ConsoleAppender"> 
    <param name="Target" value="System.out"/> 
    <layout class="org.apache.log4j.PatternLayout"> 
      <param name="ConversionPattern" value="%-5p %c{1} - %m%n"/> 
    </layout> 
  </appender> 

  <root> 
    <priority value ="error" /> 
    <appender-ref ref="console" /> 
  </root>
  
</log4j:configuration>
```
### add spring config ###
  * located in `/WEB-INF/my-webapp-config.xml`
```
<?xml version="1.0" encoding="UTF-8"?>
<beans 
    xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:p="http://www.springframework.org/schema/p"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				            http://www.springframework.org/schema/context
				            http://www.springframework.org/schema/context/spring-context-3.0.xsd
							http://www.springframework.org/schema/mvc
							http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
							http://www.springframework.org/schema/util
							http://www.springframework.org/schema/util/spring-util-3.0.xsd
				              "							
              >
              
    <context:component-scan base-package="com.jgk.springrecipes.mvc.release"/>
	<context:property-placeholder order="1" location="classpath:first.properties"/>
	<context:property-placeholder order="2" location="classpath:second.properties"/>
	<util:list id="wordList" list-class="java.util.LinkedList">
		<value>one</value>
		<value>two</value>
		<value>three</value>
		<value>${lastname}</value>
	</util:list>


	<bean id="backendJspViewResolver"
		class="org.springframework.web.servlet.view.UrlBasedViewResolver">
		<property name="order" value="5"/>
		<property name="viewClass"
			value="org.springframework.web.servlet.view.JstlView" />
		<property name="prefix" value="/WEB-INF/jsp/" />
		<property name="suffix" value=".jsp" />
	</bean>
	
	
</beans>
```
### modify web.xml to add context ###
  * located in `/WEB-INF`
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app 
	id="WebApp_ID" 
	version="2.5" 
	xmlns="http://java.sun.com/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
	
  <display-name>release-jpa</display-name>
   <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/my-webapp-config.xml</param-value>
  </context-param>
  <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
	
</web-app>
```
### add spring web config ###
  * located `/WEB-INF/release-servlet.xml`
```
<?xml version="1.0" encoding="UTF-8"?>
<beans 
    xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:p="http://www.springframework.org/schema/p"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				        http://www.springframework.org/schema/context
				        http://www.springframework.org/schema/context/spring-context-3.0.xsd
						http://www.springframework.org/schema/mvc
						http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
						http://www.springframework.org/schema/util
						http://www.springframework.org/schema/util/spring-util-3.0.xsd
				              "							
              >
              
    <!-- JSR-303 support will be detected on classpath and enabled automatically -->
	<context:component-scan base-package="com.jgk.springrecipes.mvc.release.controllers"/>   
    <mvc:annotation-driven />
	
</beans>

```
### modify web.xml to add dispatcher servlet ###
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app 
	id="WebApp_ID" 
	version="2.5" 
	xmlns="http://java.sun.com/xml/ns/javaee" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
	
	<display-name>release-jpa</display-name>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/my-webapp-config.xml</param-value>
	</context-param>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<servlet>
		<servlet-name>release</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>release</servlet-name>
		<url-pattern>/release/*</url-pattern>
	</servlet-mapping>
	<welcome-file-list>
		<welcome-file>index.html</welcome-file>
		<welcome-file>index.htm</welcome-file>
		<welcome-file>index.jsp</welcome-file>
		<welcome-file>default.html</welcome-file>
		<welcome-file>default.htm</welcome-file>
		<welcome-file>default.jsp</welcome-file>
	</welcome-file-list>
	
</web-app>
```
### add controllers ###
### add views ###
### add domain objects ###
### add repository ###
### add persistence.xml ###
### add entity manager support ###

### run it ###