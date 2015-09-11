### articles ###
  * [springframework mvc fileupload](http://static.springsource.org/spring/docs/3.0.0.M3/spring-framework-reference/html/ch16s08.html)
## requirements ##
  * upload an image
  * generate a new name for image (perhaps numbered)
  * generate a thumbnail for image
  * store image(s) to system (file? db? other?)
  * store info about images (size, dimensions, original name, date/time)
## setup ##
### project name: jgk-spring-recipes-fileupload ###
### fileupload-webapp-config.xml ###
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
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
              
    <context:component-scan base-package="com.jgk.springrecipes.mvc.jed"></context:component-scan>
	<context:property-placeholder order="1" location="classpath:first.properties"/>
	<context:property-placeholder order="2" location="classpath:second.properties"/>
	<util:list id="wordList" list-class="java.util.LinkedList">
		<value>one</value>
		<value>two</value>
		<value>three</value>
		<value>${lastname}</value>
	</util:list>


<!--	<bean id="backendJspViewResolver"-->
<!--		class="org.springframework.web.servlet.view.UrlBasedViewResolver">-->
<!--		<property name="order" value="5"/>-->
<!--		<property name="viewClass"-->
<!--			value="org.springframework.web.servlet.view.JstlView" />-->
<!--		<property name="prefix" value="/WEB-INF/jsp/" />-->
<!--		<property name="suffix" value=".jsp" />-->
<!--	</bean>-->
	
	
</beans>

```
### fileupload-servlet.xml ###
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:p="http://www.springframework.org/schema/p"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				              http://www.springframework.org/schema/context
				              http://www.springframework.org/schema/context/spring-context-3.0.xsd
							http://www.springframework.org/schema/mvc
							http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
							http://www.springframework.org/schema/util
							http://www.springframework.org/schema/util/spring-util-3.0.xsd
				              ">

	<!-- JSR-303 support will be detected on classpath and enabled automatically -->
	<context:component-scan base-package="com.jgk.springrecipes.fileupload.controllers" />
	<mvc:annotation-driven />
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver" />

	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
		<property name="viewClass"
			value="org.springframework.web.servlet.view.JstlView" />

	</bean>


</beans>

```
### web.xml ###
```
<?xml version="1.0" encoding="UTF-8"?>

<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">

  <display-name>Archetype Created Web Application</display-name>
     <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/fileupload-webapp-config.xml</param-value>
  </context-param>
  <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  	<servlet>
		<servlet-name>fileupload</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>
  	<servlet-mapping>
  		<servlet-name>fileupload</servlet-name>
  		<url-pattern>/fileupload/*</url-pattern>
  	</servlet-mapping>
  <welcome-file-list>
  	<welcome-file>index.jsp</welcome-file>
  	<welcome-file>index.html</welcome-file>
  </welcome-file-list>
  
</web-app>

```
### pom.xml ###
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.jgk.springrecipes.fileupload</groupId>
	<artifactId>jgk-spring-recipes-fileupload</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>jgk-spring-recipes-fileupload Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<properties>
		<maven.compiler.source>1.6</maven.compiler.source>
		<maven.compiler.target>1.6</maven.compiler.target>
		<maven.compiler.plugin.version>2.3.2</maven.compiler.plugin.version>
		<commons-fileupload.version>1.2.1</commons-fileupload.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<servlet-api.version>2.5</servlet-api.version>
		<spring.version>3.0.5.RELEASE</spring.version>
		<jsp-api.version>2.2</jsp-api.version>
		<jstl.version>1.2</jstl.version>
		<junit.version>4.8.2</junit.version>
		<slf4j-api.version>1.5.10</slf4j-api.version>
		<slf4j-log4j12.version>1.5.10</slf4j-log4j12.version>
		<jcl-over-slf4j.version>1.5.10</jcl-over-slf4j.version>
		<log4j.version>1.2.12</log4j.version>

	</properties>

	<dependencies>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${slf4j-api.version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${slf4j-log4j12.version}</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>${log4j.version}</version>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit.version}</version>
			<type>jar</type>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
			<type>jar</type>
			<scope>runtime</scope>
			<exclusions>
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>${spring.version}</version>
			<type>jar</type>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>${servlet-api.version}</version>
			<type>jar</type>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>${commons-fileupload.version}</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
	</dependencies>
	<build>
		<finalName>jgk-spring-recipes-fileupload</finalName>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>${maven.compiler.plugin.version}</version>
				<configuration>
					<showWarnings>true</showWarnings>
					<source>${maven.compiler.source}</source>
					<target>${maven.compiler.target}</target>
				</configuration>
			</plugin>
		</plugins>
		
	</build>
</project>

```