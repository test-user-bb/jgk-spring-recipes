# [Spring Transactions](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html) #
## Transaction 3 questions ##
### Write XML code to configure a local transaction manager ###
  * Data source:
    * Local transaction manager:
```
<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <property name="dataSource" ref="dataSource"/>
</bean>
```
    * Associated data source:
```
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
  <property name="driverClassName" value="${jdbc.driverClassName}" />
  <property name="url" value="${jdbc.url}" />
  <property name="username" value="${jdbc.username}" />
  <property name="password" value="${jdbc.password}" />
</bean>
```
  * Hibernate:
```
<bean id="txManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
  <property name="sessionFactory" ref="sessionFactory" />
</bean>
```
### Write XML code to configure a JTA transaction manager ###
  * If you use JTA in a Java EE container then you use a container `DataSource`, obtained through JNDI, in conjunction with Spring's `JtaTransactionManager`.
  * The `JtaTransactionManager` does not need to know about the `DataSource`, or any other specific resources, because it uses the container's global transaction management infrastructure.
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xmlns:jee="http://www.springframework.org/schema/jee"
     xsi:schemaLocation="
     http://www.springframework.org/schema/beans 
     http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
     http://www.springframework.org/schema/jee 
     http://www.springframework.org/schema/jee/spring-jee-3.0.xsd">

  <jee:jndi-lookup id="dataSource" jndi-name="jdbc/jpetstore"/> 

  <bean id="txManager" class="org.springframework.transaction.jta.JtaTransactionManager" />
  
  <!-- other <bean/> definitions here -->

</beans>
```
### Write XML code to configure transactions using AOP ###
```
<!-- from the file 'context.xml' -->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xmlns:aop="http://www.springframework.org/schema/aop"
     xmlns:tx="http://www.springframework.org/schema/tx"
     xsi:schemaLocation="
     http://www.springframework.org/schema/beans 
     http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
     http://www.springframework.org/schema/tx
     http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
     http://www.springframework.org/schema/aop 
     http://www.springframework.org/schema/aop/spring-aop-3.0.xsd">
  
  <!-- this is the service object that we want to make transactional -->
  <bean id="fooService" class="x.y.service.DefaultFooService"/>

  <!-- the transactional advice (what 'happens'; see the <aop:advisor/> bean below) -->
  <tx:advice id="txAdvice" transaction-manager="txManager">
  <!-- the transactional semantics... -->
  <tx:attributes>
    <!-- all methods starting with 'get' are read-only -->
    <tx:method name="get*" read-only="true"/>
    <!-- other methods use the default transaction settings (see below) -->
    <tx:method name="*"/>
  </tx:attributes>
  </tx:advice>
  
  <!-- ensure that the above transactional advice runs for any execution
    of an operation defined by the FooService interface -->
  <aop:config>
  <aop:pointcut id="fooServiceOperation" expression="execution(* x.y.service.FooService.*(..))"/>
  <aop:advisor advice-ref="txAdvice" pointcut-ref="fooServiceOperation"/>
  </aop:config>
  
  <!-- don't forget the DataSource -->
  <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
  <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"/>
  <property name="url" value="jdbc:oracle:thin:@rj-t42:1521:elvis"/>
  <property name="username" value="scott"/>
  <property name="password" value="tiger"/>
  </bean>

  <!-- similarly, don't forget the PlatformTransactionManager -->
  <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  <property name="dataSource" ref="dataSource"/>
  </bean>
  
  <!-- other <bean/> definitions here -->

</beans>
```
### Know XML to enable @Transaction ###
```
<tx:annotation-driven transaction-manager="transactionManager"/>
```
### Understand how `TransactionTemplate` is used to rollback a transaction ###
  * Code within the callback can roll the transaction back by calling the setRollbackOnly() method on the supplied `TransactionStatus`:
```
transactionTemplate.execute(new TransactionCallbackWithoutResult() {

  protected void doInTransactionWithoutResult(TransactionStatus status) {
    try {
      updateOperation1();
      updateOperation2();
    } catch (SomeBusinessExeption ex) {
      status.setRollbackOnly();
    }
  }
});
```
### Identify which exceptions cause a rollback by default ###
  * `RuntimeException`s
  * roll back is automatic only on unchecked exceptions
  * In its default configuration, the Spring Framework's transaction infrastructure code only marks a transaction for rollback in the case of runtime, unchecked exceptions; that is, when the thrown exception is an instance or subclass of RuntimeException. (Errors will also - by default - result in a rollback). Checked exceptions that are thrown from a transactional method do not result in rollback in the default configuration.
### Understand main attributes for Transaction: ###
  * readOnly - read-only transaction can be used when your code reads but does not modify data. Read-only transactions can be a useful optimization in some cases, such as when you are using Hibernate.
  * isolation - The degree to which this transaction is isolated from the work of other transactions.
  * propagation - Typically, all code executed within a transaction scope will run in that transaction. However, you have the option of specifying the behavior in the event that a transactional method is executed when a transaction context already exists. For example, code can continue running in the existing transaction (the common case); or the existing transaction can be suspended and a new transaction created.
  * rollbackFor
  * noRollbackFor
### State the default isolation and propagation settings ###
  * Default propagation:  REQUIRED (PROPAGATION\_REQUIRED)
  * Isolation level is DEFAULT (ISOLATION\_DEFAULT)
  * Transaction is read/write.
  * Transaction timeout defaults to the default timeout of the underlying transaction system, or none if timeouts are not supported.
  * Any `RuntimeException` triggers rollback, and any checked Exception does not.
### Know the difference between PROPAGATION\_REQUIRED and PROPAGATION\_REQUIRES\_NEW ###
  * PROPAGATION\_REQUIRED
  * PROPAGATION\_REQUIRES\_NEW - uses a completely independent transaction for each affected transaction scope. In that case, the underlying physical transactions are different and hence can commit or roll back independently, with an outer transaction not affected by an inner transaction's rollback status.
### Know the difference between READ\_COMMITTED and READ\_UNCOMMITTED ###
  * READ\_COMMITTED - dirty reads are prevented; non-repeatable reads and phantom reads can occur.
  * READ\_UNCOMMITTED - dirty reads, non-repeatable reads and phantom reads can occur.