# Dependencies #
  * Changed to ` j_spring_security_check ` from ` j_acegi_security_check `
  * Package names changed to ` org.springframework.security.core* ` from ` org.acegisecurity.* `
  * Here is a discussion of the experience of migrating a project from using Acegi Security (1.0.3) to using Spring Security (
  * From
```
    <properties>
        <acegi.security.version>1.0.3</acegi.security.version>
    </properties>
    <dependencies>
                <dependency>
                        <groupId>org.acegisecurity</groupId>
                        <artifactId>acegi-security</artifactId>
                        <version>${acegi.security.version}</version>
                </dependency>
    </dependencies>

```
  * To:
```
    <properties>
        <org.springframework.version>3.0.5.RELEASE</org.springframework.version>
    </properties>
    <dependencies>
	<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-security</artifactId>
			<version>${org.springframework.version}</version>
	</dependency>
  	<dependency>
  		<groupId>org.springframework.security</groupId>
  		<artifactId>spring-security-config</artifactId>
  		<version>${org.springframework.version}</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-web</artifactId>
  		<version>${org.springframework.version}</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>


    </dependencies>
```
  * Basic config in 3.0.5.RELEASE
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
    * Context configuration
```
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:security="http://www.springframework.org/schema/security"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/security
http://www.springframework.org/schema/security/spring-security-3.0.xsd">

<security:http auto-config="true">
  <security:intercept-url pattern="/**" access="ROLE_USER" />
</security:http>
<security:authentication-manager>
  <security:authentication-provider>
  	<security:user-service>
  		<security:user name="jed" password="bigred" authorities="ROLE_USER, ROLE_ADMIN"/>
  		<security:user name="wilma" password="bigred" authorities="ROLE_USER"/>
  	</security:user-service>
  </security:authentication-provider>
</security:authentication-manager>
</beans>
```