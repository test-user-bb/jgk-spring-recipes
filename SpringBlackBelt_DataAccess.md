## [Spring Data Access](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/spring-data-tier.html) ##
### List advantages of `DataAccessException` over SQLException ###
  * allows you to handle most persistence exceptions, which are non-recoverable, only in the appropriate layers, without annoying boilerplate catches, throws, and exception declarations. You can still trap and handle exceptions as necessary. Remember that JDBC exceptions (including DB-specific dialects) are also converted to the same hierarchy, meaning that you can perform some operations with JDBC within a consistent programming model.
### Write XML to define a datasource using a JNDI reference ###
  * New way with jee namespace:
```
<jee:jndi-lookup id="dataSource" jndi-name="jdbc/MyDataSource"/>
```
  * Old xml way:
```
<bean id="dataSource" 
      class="org.springframework.jndi.JndiObjectFactoryBean">
      <property name="jndiName" 
                  value="java:comp/env/jdbc/myjdb"/>    
      <property name="resourceRef" 
                  value="true" /> 
</bean>
```
### Write XML to define a datasource using a standalone connection pool ###
```
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driverClassName}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <context:property-placeholder location="jdbc.properties"/>
```