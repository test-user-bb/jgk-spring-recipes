  * [javadoc](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/)
  * [Getting Started](http://static.springsource.org/spring-security/site/start-here.html)
  * There is no real conceptual difference between a user who is “anonymously authenticated” and an unauthenticated user.
## source from git ##
```
git clone git://git.springsource.org/spring-security/spring-security.git
```
## Scaffolding ##
  * You need some minimal stuff in your configuration:
```
	<security:http auto-config='true' use-expressions="true">
		<security:intercept-url pattern="/sessionTimeout.jsp*" filters="none" />
		<security:intercept-url pattern="/admin/**" access="hasAnyRole('ROLE_ADMIN','ROLE_AD')" />
		<security:intercept-url pattern="/manager/**" access="hasAnyRole('ROLE_ADMIN','ROLE_manager')" />
		<security:session-management invalid-session-url="/sessionTimeout.jsp" />
		<security:intercept-url pattern="/**" access="hasAnyRole('ROLE_USER','ROLE_USER','ROLE_PR','ROLE_AD','ROLE_manager','ROLE_PT')" />
	</security:http>

```
  * Minimal web.xml:
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee   http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

  <display-name>Archetype Created Web Application</display-name>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/my-webapp-config.xml</param-value>
	</context-param>
  
	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	
</web-app>
```
## Other ##
  * To use the spring provided security tags in .jsp files you need the following:
    * In each .jsp file:
```
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

```
    * In the pom.xml:
```
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
			<version>${org.springframework.version}</version>
		</dependency>		

```
  * Tags
```
<sec:authorize access="hasRole('supervisor')">
This content will only be visible to users who have
the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
</sec:authorize>

<sec:authorize url="/admin">
This content will only be visible to users who are authorized to send requests to the "/admin" URL.
</sec:authorize>

<sec:authentication property="principal.username" /> will render the name of the current user.


```
## jdbc ##
  * Using jdbc
    * You can use multiple authentication providers!  This is cool, since if user is not matched on first it will continue hitting subsequent authentication providers.  In the following example there are 3 authentication providers specified.  The first two are actually doing the same thing since the underlying database (dataSource) is the same, and the database uses the standard Spring security tables (USERS, AUTHORITIES):
```
	<security:authentication-manager>
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="someDataSource" />
		</security:authentication-provider>
		<security:authentication-provider user-service-ref='myUserDetailsService'/>
		<security:authentication-provider>
			<security:user-service>
				<security:user name="helen" password="helen" authorities="ROLE_USER"/>
				<security:user name="ted" password="ted" authorities="ROLE_USER"/>
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>
	<bean id="myUserDetailsService" class="org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl">
		<property name="dataSource" ref="someDataSource"/> 
	</bean>	
	<bean id="someDataSource" class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close">
		<property name="maxActive">
			<value>-1</value>
		</property>
		<property name="driverClassName">
			<value>oracle.jdbc.driver.OracleDriver</value>
		</property>
		<property name="url">
			<value>jdbc:oracle:thin:@myhost:1521:myoraclesid</value>
		</property>
		<property name="username">
			<value>joeuser</value>
		</property>
		<property name="password">
			<value>joepassword</value>
		</property>
	</bean>

```
    * Standard tables used by Spring:
![http://i299.photobucket.com/albums/mm283/javapda/Spring/spring-security-tutorial-database.png](http://i299.photobucket.com/albums/mm283/javapda/Spring/spring-security-tutorial-database.png)
```
     !-- Configure the authentication provider -->
     <security:authentication-provider>
         <security:jdbc-user-service data-source-ref="dataSource" />
     </security:authentication-provider>
```
```
CREATE TABLE users
(
  username varchar2(50) NOT NULL,
  "password" varchar2(50) NOT NULL,
  enabled integer NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (username)
);
 
CREATE TABLE authorities
(
  username varchar2(50) NOT NULL,
  authority varchar2(50) NOT NULL,
  CONSTRAINT fk_authorities_users 
  FOREIGN key (username)
      REFERENCES users (username) 
); 

CREATE UNIQUE INDEX ix_auth_username
  ON authorities
  (username, authority);
```
  * What if your database already has some tables to support this?  No problem.
```
	<security:authentication-manager>
		<security:authentication-provider>
			<security:jdbc-user-service data-source-ref="someDataSource"
				authorities-by-username-query="select username, 'ROLE_' || user_type as authority from username where username = ?"
				 users-by-username-query="select username, password, 1 from username where username = ?" />
		</security:authentication-provider>
	</security:authentication-manager>

```
  * [Mike Weisner - Video : Spring Security 3](http://www.infoq.com/presentations/Spring-Security-3)
    * Email: [mweisner@vmware.com](mailto:mweisner@vmware.com)
    * Security Framework for Enterprise
    * Can be used for Java applications
    * Libraries:
      * Core - spring-security-core.jar
      * Web - spring-security-web.jar
      * Config - spring-security-config.jar
      * LDAP - spring-security-ldap.jar
      * ACL - spring-security-acl.jar
      * CAS - spring-security-cas-client.jar
      * OpenID - spring-security-openid.jar
    * Steps needed:
      1. create application context
      1. within the application context create an authentication manager - responsible for holding all the user
information
      1. authorization configuration
  * Moving from Acegi to spring-security:
    * put stuff here
    * [Matt Raible - Upgrading to Spring Security 2.0](http://raibledesigns.com/rd/entry/upgrading_to_spring_security_2)
  * Spring Security Videos:
    * [Spring Security with Ben Alex at Orodev 2008](http://www.viddler.com/explore/oredev/videos/22/)
    * [MyEclipse Spring Security](http://www.youtube.com/watch?v=JdwczC5IZH4)
  * [ProviderManager](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/authentication/ProviderManager.html)
  * [Authentication](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html?org/springframework/security/core/Authentication.html)
  * [AuthenticationManager](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/authentication/AuthenticationManager.html)
  * What type of [UserDetailsService](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html?org/springframework/security/core/Authentication.html) implementations exist?
    * CachingUserDetailsService
    * [InMemoryDaoImpl](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html?org/springframework/security/core/Authentication.html) - Retrieves user details from an in-memory list created in the application context. Username lookups are case-insensitive.
    * [JdbcDaoImpl](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html?org/springframework/security/core/Authentication.html) - uses jdbc to get info from database, specifies tables (Users, Authorities) and columns
    * [JdbcUserDetailsManager](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html?org/springframework/security/core/Authentication.html) - CRUD interface to JdbcDaoImpl
    * [LdapUserDetailsManager](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html?org/springframework/security/core/Authentication.html)
    * [LdapUserDetailsService](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/index.html?org/springframework/security/core/Authentication.html)
    * UserDetailsServiceWrapper - _deprecated_
  * How do you get information about the currently authenticated user?
```
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
if (principal instanceof UserDetails) {
   String username = ((UserDetails)principal).getUsername();
} else {
   String username = principal.toString();
}
```
  * [SecurityContextHolder](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/core/context/SecurityContextHolder.html)
  * [FAQ](http://static.springsource.org/spring-security/site/faq.html)
  * Spring Security Basics (3 steps)
    1. add spring security jars to your classpath
    1. Add **DelegatingFilterProxy** to your web.xml
    1. create basic security configuration xml file:
```
    <global-method-security/> (recommended)
    <http auto-config="true"/> (recommended)
    <intercept-url .../> (one or more)
    <*-details-service> (e.g. LDAP, DB, XML, custom)
```
  * Spring EL Annotations (4 annotations)
    * @PreAuthorize
    * @PostAuthorize
    * @PreFilter
    * @PostFilter
  * Spring EL Expressions:
    * hasRole(String)
    * hasAnyRole(String)
    * hasPermission(String)
    * isAnonymous
    * isRememberMe
    * isFullyAuthenticated
    * authentication
    * permitAll
    * denyAll
    * access to the arguments and return object
  * Examples of Spring EL Annotations:
```
...add the following to your security configuration xml file:
<security:global-method-security expression-annotations="enabled"/>
...
  public interface MyBean {
     @PreAuthorize("hasRole('ROLE_SUPERVISOR') or (hasRole('ROLE_TELLER') and (#account.balance + #account >= -#account.overdraft))")
     void post(Account account, float amount);
  }
  
```
  * AspectJ
```
<security:global-method-security>
  <security:protect-pointcut access="ROLE_USER" expression="execution(* *.get*(..))"/>
  <security:protect-pointcut access="ROLE_ADMIN" expression="execution(* *.set*(..))"/>
</security:global-method-security>

```
  * You can apply method-level security directly in a bean configuration:
```
<bean id="jed" class="com.jgk.springrecipes.stuff.BigStuff">
   <security:intercept-methods>
     <security:protect access="ROLE_ADMIN" method="set*"/>
     <security:protect access="ROLE_USER" method="get*"/>
   </security:intercept-methods>
</bean>
```
  * [Security Annotations](http://java.sun.com/developer/technicalArticles/J2EE/security_annotation/):
```
javax.annotation.security.PermitAll
javax.annotation.security.DenyAll
javax.annotation.security.RolesAllowed
javax.annotation.security.DeclareRoles
javax.annotation.security.RunAs
```
  * Spring's @Secured
    * _Note: JSR250 security annotations are the standard, so should be preferred over Spring @Secured_
    * Useful since it can be applied to Interfaces AND classes, whereas the JSR250 security annotations may only be applied to classes.
  * How do you enable JSR250 security?
```
  <security:global-method-security jsr250-annotations="enabled">
```
  * global-method-security
```
<security:global-method-security />
global-method-security
Provides method security for all beans registered in 
 the Spring application context. Specifically, beans will 
 be scanned for matches with the ordered list of 
 "protect-pointcut" sub-elements, Spring Security 
 annotations and/or. Where there is a match, the 
 beans will automatically be proxied and security 
 authorization applied to the methods accordingly. If 
 you use and enable all four sources of method 
 security metadata (ie "protect-pointcut" declarations, 
 expression annotations, @Secured and also JSR250 
 security annotations), the metadata sources will be 
 queried in that order. In practical terms, this enables 
 you to use XML to override method security metadata 
 expressed in annotations. If using annotations, the 
 order of precedence is EL-based (@PreAuthorize etc.), 
 @Secured and finally JSR-250.
```
  * Official Java EE and Java 6 way of doing method security:
```
  @DenyAll
  @RolesAllowed("ROLE_USER")
```
  * Method Authorization
    * Web authorization relies on pattern matching
    * No pattern match means no authorization
    * Client/UI technology agnostic
    * Flexible - esp. with Expression Language
  * Better to move this protection to .xml configuration so you do not need to have this similar code in many different places.
    * So in a configuration file (e.g. security-config.xml) you might include:
```
   <security:http auto-config="true">
       <security:intercept-url pattern="/special_user_page.jsp" access="ROLE_SPECIAL_USER"/>
   </security:http>
```
  * In a special\_user\_page.jsp file you can check user in role and throw a spring exception:
```
     <% 
         if (!request.isUserInRole("ROLE_SPECIAL_USER")) {
             throw new AccessDeniedException("You must be a SPECIAL_USER to use this feature.");
         }
     %>
```
  * How to check if user is actually part of a role?
> > ` request.isUserInRole("ROLE_USER"); `
  * Authorization with Spring Security:
    * web authorization
    * method authorization
  * Basic Authentication uses Base64 encoding.  Very simple and not-secure data.  So, best to use over a secure channel (https).
  * Basic Authentication is integrated with Spring's  [org.springframework.remoting.httpinvoker.Authentication.SimpleHttpInvokerRequestExecutor](http://static.springsource.org/spring/docs/2.5.x/api/org/springframework/remoting/httpinvoker/SimpleHttpInvokerRequestExecutor.html).
  * Basic Authentication uses HTTP header:
```
   Name: "Authorization"
   Value: "Basic" + " " + Base64(username + ":" + password)
```
  * If using jdbc then you need the following Maven dependency:
```
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework.version}</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>

```
  * Minimum Maven dependencies:
    * org.springframework.security:spring-security-core:3.0.5.RELEASE
    * org.springframework.security:spring-security-config:3.0.5.RELEASE
    * org.springframework.security:spring-security-web:3.0.5.RELEASE
    * org.springframework:spring-web:3.0.5.RELEASE
```
  	<dependency>
  		<groupId>org.springframework.security</groupId>
  		<artifactId>spring-security-core</artifactId>
  		<version>3.0.5.RELEASE</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework.security</groupId>
  		<artifactId>spring-security-config</artifactId>
  		<version>3.0.5.RELEASE</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework.security</groupId>
  		<artifactId>spring-security-web</artifactId>
  		<version>3.0.5.RELEASE</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-web</artifactId>
  		<version>3.0.5.RELEASE</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>

```
  * Default settings:
    * Login page: ` /spring_security_login `
    * Failure page: ` /spring_security_login?login_error `
    * Posts to: ` /j_spring_security_check `
  * Spring Security will auto-render a login page if you do not specify a custom login page URL.
  * Authentication Mechanisms vs. Authentication Providers
    * Mechanism bundles user info (e.g. username/password or cookie) into an AuthenticationRequest which is passed to the providers. Provider examines request to determine if sufficient information available to authenticate user.  If 'yes', then returns an AuthenticationResponse (has user roles, their format?, extra profile info) to the Mechanism (the mechanism stores it on on the [SecurityContextHolder](http://static.springsource.org/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/core/context/SecurityContextHolder.html)).
  * Run samples:
```
 You can also run the sample web applications directly using gradle's Jetty plugin. For example, to run the contacts sample

 $ cd samples/contacts
 $ gradle jettyRun

which will start the server on port 8080. You can then access it using the URL http://localhost:8080/contacts.
```
  * Source code available via git:
```
git clone git://git.springsource.org/spring-security/spring-security.git
```
  * Build source with [Gradle](http://gradle.org)
```
 $ cd spring-security
 $ ./gradlew build
```
  * in application configuration:
```
<authentication-manager>
<authentication-provider>
<user-service>
<user name="jimi" password="jimispassword" authorities="ROLE_USER, ROLE_ADMIN" />
<user name="bob" password="bobspassword" authorities="ROLE_USER" />
</user-service>
</authentication-provider>
</authentication-manager>
```
  * web.xml
```
<filter>
<filter-name>springSecurityFilterChain</filter-name>
<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
<filter-name>springSecurityFilterChain</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
```
  * Security config
```
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:security="http://www.springframework.org/schema/security"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/security
http://www.springframework.org/schema/security/spring-security-3.1.xsd">
...
</beans>
```
  * Security config old
```
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:security="http://www.springframework.org/schema/security"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/security
http://www.springframework.org/schema/security/spring-security-3.0.xsd">
...
</beans>
```
  * Or:
```
<beans:beans xmlns="http://www.springframework.org/schema/security"
xmlns:beans="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/security
http://www.springframework.org/schema/security/spring-security-3.0.xsd">
...
</beans:beans>
```
  * [SpringSource Tool Suite ](http://www.springsource.com/products/sts)
  * NOTE: elements here lifted from Ben Alex's book on spring security.
  * [Apache Portable Runtime Project versioning guidelines](http://apr.apache.org/versioning.html) _MAJOR.MINOR.PATCH_
    * Versions are denoted using a standard triplet of integers: MAJOR.MINOR.PATCH.
    * The basic intent is that MAJOR versions are incompatible, large-scale upgrades of the API.
    * MINOR versions retain source and binary compatibility with older minor versions, and changes in the PATCH level are perfectly compatible, forwards and backwards.
  * 2007 - Acegi Security is rebranded as **Spring Security**
  * Three main areas of interest in respect of authorization:
    1. authorizing web requests
    1. authorizing whether methods can be invoked
    1. authorizing access to individual domain object instances
  * [JCaptcha](http://jcaptcha.sourceforge.net/) integration for human user detection
  * Spring Security currently supports authentication integration with:
    * HTTP BASIC authentication headers (an IEFT RFC-based standard)
    * HTTP Digest authentication headers (an IEFT RFC-based standard)
    * HTTP X.509 client certificate exchange (an IEFT RFC-based standard)
    * LDAP
    * Form-based authentication (for simple user interface needs)
    * OpenID authentication
    * Authentication based on pre-established request headers (such as Computer Associates Siteminder)
    * JA-SIG Central Authentication Service (otherwise known as CAS, which is a popular open source single sign on system)
    * Transparent authentication context propagation for Remote Method Invocation (RMI) and HttpInvoker (a Spring remoting protocol)
    * Automatic "remember-me" authentication (so you can tick a box to avoid re-authentication for a
predetermined period of time)
    * Anonymous authentication (allowing every call to automatically assume a particular security identity)
    * Run-as authentication (which is useful if one call should proceed with a different security identity)
    * Java Authentication and Authorization Service (JAAS)
    * JEE container autentication (so you can still use Container Managed Authentication if desired)
    * Kerberos
    * Third Party Providers
      * Java Open Source Single Sign On (JOSSO), AppFuse, AndroMDA, Mule ESB, Direct Web Request (DWR), Grails,  Tapestry, JTrac, Jasypt, Roller, Elastic Path, Atlassian Crowd
  * **Authorization** refers to the process of deciding whether a principal is allowed to perform an action within your application.
  * **Authentication** is the process of establishing a principal is who they claim to be (a “principal” generally means a user, device or some other system which can perform an action in your application).
  * [Download Spring Security 3.0.5.RELEASE](http://s3.amazonaws.com/dist.springframework.org/release/SEC/spring-security-3.0.5.RELEASE.zip)
  * Spring Security works with webapps, but also with:
    * Batch jobs
    * Rich clients
    * Integration tests
  * [Flash Video - Ben Alex of SpringSource (has Swedish Fiance)](http://www.viddler.com/explore/oredev/videos/22/) - ben.alex@springsource.com
  * Spring Security used to be Acegi Security - became Spring Security in 2008
  * Spring Security capabilities:
    * Authentication
    * Web URL authorization
    * Method invocation authorization
    * Channel security
    * Human user detection
    * Domain instance based security (ACLs - Access Control Lists - e.g. can only see specific domain patient information)
    * WS-Security (via Spring Web Services)
    * Flow Authorization (via Spring Web Flow)
  * What Spring Security **IS NOT**:
    * [Firewall](http://en.wikipedia.org/wiki/Firewall_(computing))
    * [Proxy Server](http://en.wikipedia.org/wiki/Proxy_server)
    * [IDS](http://en.wikipedia.org/wiki/Intrusion_detection_system)
    * Operating System Security
    * JVM (sandbox) Security
## Logout ##
  * [Adding a logout link](http://static.springsource.org/spring-security/site/petclinic-tutorial.html)
```
<a href="{context-url}/j_spring_security_logout" htmlEscape="true" />">
```