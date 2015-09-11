# [Spring Security](http://static.springsource.org/spring-security/site/) | [ref](http://static.springsource.org/spring-security/site/reference.html) | [HTML](http://static.springsource.org/spring-security/site/docs/3.0.x/reference/springsecurity.html) | [javadoc/API](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html) #
## Security 2 questions ##
### Define the Spring security filter chain ###
  * Spring Security maintains a filter chain internally where each of the filters has a particular responsibility and filters are added or removed from the configuration depending on which services are required.
### Write XML code to configure Spring security intercept URLs ###
```
<?xml version="1.0" encoding="UTF-8"?>
<beans 
    xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:sec="http://www.springframework.org/schema/security"
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
						http://www.springframework.org/schema/security
						http://www.springframework.org/schema/security/spring-security-3.0.xsd
				              "							
              >
	<sec:http auto-config='true'>
		<sec:intercept-url pattern="/**" access="ROLE_USER" />
	</sec:http>

</beans>

```
### Write XML code to configure method level security ###
### Write code using `@Secured` and `@RolesAllowed` ###
### List authentication manager types ###
### Know whether any authenticate managers support password encoding ###
### State the tag used in a JSP to see if authorized to certain role ###
```
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="hasRole('ROLE_SUPERVISOR')">
   You have "ROLE_SUPERVISOR" (this text is surrounded by &lt;security:authorize&gt; tags).
</security:authorize>
```