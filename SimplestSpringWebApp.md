## pieces ##
  * pom.xml - since using maven [HERE](HERE.md)
  * web.xml - web application descriptor
  * spring-config.xml - spring configuration

### pom.xml ###
  * look [here](PomXmlJee5WithSpring31Servlet25WithJSTL.md)
### web.xml ###
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
	<display-name>web-spring31-hib4</display-name>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/classes/spring-simple-config.xml</param-value>
	</context-param>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	<servlet>
		<description>this is optional, retrieves environment information during application startup</description>
		<display-name>Startup Servlet</display-name>
		<servlet-name>startupservlet</servlet-name>
		<servlet-class>com.jgk.spring31hib4.servlets.StartupServlet</servlet-class>
		<init-param>
			<description>who put this together?</description>
			<param-name>author</param-name>
			<param-value>John G. Kroubalkian</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<session-config>
		<session-timeout>30</session-timeout>
	</session-config>
	<welcome-file-list>
		<welcome-file>index.jsp</welcome-file>
	</welcome-file-list>
</web-app>

```
### spring-config.xml ###
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:jdbc="http://www.springframework.org/schema/jdbc"
        xmlns:context="http://www.springframework.org/schema/context"
        xmlns:tx="http://www.springframework.org/schema/tx"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
                                                        http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
                                                        http://www.springframework.org/schema/jdbc
                                                        http://www.springframework.org/schema/jdbc/spring-jdbc-3.1.xsd
                                                        http://www.springframework.org/schema/tx
                                                        http://www.springframework.org/schema/tx/spring-tx-3.1.xsd
                                                        http://www.springframework.org/schema/context
                                                        http://www.springframework.org/schema/context/spring-context-3.1.xsd">
                                                        
                                                        
</beans>                                                        
```