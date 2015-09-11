  * [Plan](SpringRESTSecurity#Plan.md)

# References #
  * [Spring 3.x Security](http://static.springsource.org/spring-security/site/docs/3.1.x/reference/springsecurity-single.html) | [download Spring](http://www.springsource.org/download/community) | [javadoc](http://static.springsource.org/spring-security/site/docs/3.1.x/apidocs/)
  * [OWASP](http://www.owasp.org) - has top 10 vulnerabilities]
  * [Part 1 of 5 Series on RESTful Spring Security ](http://www.baeldung.com/2011/10/20/bootstraping-a-web-application-with-spring-3-1-and-java-based-configuration-part-1/)
    * [Part 1 - Bootstrapping a web app in Spring 3.1 + JavaConfig](http://www.baeldung.com/2011/10/20/bootstraping-a-web-application-with-spring-3-1-and-java-based-configuration-part-1/)
    * [Part 2 - RESTful web service in spring 3.1 + JavaConfig](http://www.baeldung.com/2011/10/25/building-a-restful-web-service-with-spring-3-1-and-java-based-configuration-part-2/)
    * [Part 3 - Securing restful web service with spring 3.1 security](http://www.baeldung.com/2011/10/31/securing-a-restful-web-service-with-spring-security-3-1-part-3/)
    * [Part 4 - RESTful web service discoverability](http://www.baeldung.com/2011/11/06/restful-web-service-discoverability-part-4/)
    * [Part 5 - RESTful discoverability](http://www.baeldung.com/2011/11/13/rest-service-discoverability-with-spring-part-5/)
  * [STS - Spring Source Tool Suite](http://www.springsource.com/products/sts)
## Main Security Processes ##
  * **Authentication** - process of establishing a principal is who they claim to be (a “principal” generally means a user, device or some other system which can perform an action in your application).
  * **Authorization** - deciding whether a principal should be allowed access to a resource.
  * [RESTAssured](http://code.google.com/p/rest-assured/wiki/Usage#Static_imports)

Spring Security Authentication technologies support:
  * HTTP BASIC authentication headers
  * HTTP Digest authentication headers
  * HTTP X.509 client certificate exchange
  * LDAP
  * Form-based authentication (for simple user interface needs)
  * OpenID authentication
  * Authentication based on pre-established request headers (such as Computer Associates Siteminder)
  * JA-SIG Central Authentication Service (otherwise known as CAS, which is a popular open source single sign-on system)
  * Transparent authentication context propagation for Remote Method Invocation (RMI) and HttpInvoker (a Spring remoting protocol)
  * Automatic "remember-me" authentication (so you can tick a box to avoid re-authentication for a predetermined period of time)
  * Anonymous authentication (allowing every unauthenticated call to automatically assume a particular security identity)
  * Run-as authentication (which is useful if one call should proceed with a different security identity)
  * Java Authentication and Authorization Service (JAAS)
  * JEE container autentication
  * Kerberos
  * Third Party (Java Open Source Single Sign On (JOSSO),OpenNMS Network Management Platform,
AppFuse,AndroMDA,Mule ESB,Direct Web Request (DWR),Grails,Tapestry,JTrac,Jasypt,Roller,Elastic Path,Atlassian Crowd)
  * Your own authentication systems

### Source ###
  * to get the source code for spring security:
```
git clone https://github.com/SpringSource/spring-security
```
  * or using git protocol:
```
git clone git://github.com/SpringSource/spring-security.git
```

### sample security config ###
```
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:beans="http://www.springframework.org/schema/beans" xmlns:sec="http://www.springframework.org/schema/security"
	xsi:schemaLocation="
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.1.xsd
		http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security-3.1.xsd">

<!-- stuff goes here --->

</beans:beans>
```

## Plan ##
  * Create a project to showcase each of the mechanisms in Spring security.
    * autoconfig (form provided by spring default)
    * autoconfig with basic authentication (form provided by browser)
    * autoconfig with form (form provided by us, we provided a login.jsp form)
    * form login
    * basic authentication
    * ldap?
    * custom
    * restful