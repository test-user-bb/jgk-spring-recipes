  * [Reference](SpringTransactions#Reference.md)
  * [Stuff](SpringTransactions#Stuff.md)
## Reference ##
  * [Spring transaction 3.1.x](http://static.springsource.org/spring/docs/3.1.x/reference/htmlsingle/#transaction)
  * [Declarative Transactions 3.0](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html#transaction-declarative)

## Stuff ##
  * Spring Framework `@Transactional` annotation with the `@TransactionAttribute` annotation found in the EJB 3.0 specification
  * ORM-based frameworks **require a transaction** in order to trigger the synchronization between the object cache and the database. It is through a transaction commit that the SQL code is generated and the database affected by the desired action (that is, insert, update, delete). Without a transaction there is no trigger for the ORM to generate SQL code and persist the changes.
  * When using the @Transactional annotation in Spring, **you must** add the following line to your Spring configuration file:
```
<tx:annotation-driven transaction-manager="transactionManager"/>
```
  * To support the `<tx:annotation-driven/>` element you will need the xmlns for tx and the schema definitions:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans 
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
					http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
				              "							
              >

	<tx:annotation-driven transaction-manager="transactionManager"/>
	<bean id="transactionManager" 
			class="org.springframework.orm.jpa.JpaTransactionManager" 
			p:entityManagerFactory-ref="entityManagerFactory"/> 

	<bean id="entityManagerFactory"
		class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="jpaVendorAdapter">
			<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
				<property name="showSql" value="true" />
				<property name="generateDdl" value="true" />
			</bean>
		</property>
		<property name="jpaProperties">
			<props>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="hibernate.format_sql">true</prop>
				<prop key="hibernate.hbm2ddl.auto">create-drop</prop>
			</props>
		</property>
		
	</bean>

</beans>
```
  * When using the @Transactional annotation by itself without any parameters, the propagation mode is set to REQUIRED, the read-only flag is set to false, the transaction isolation level is set to the database default (usually READ\_COMMITTED), and the transaction will not roll back on a checked exception.

## `@Transactional` properties ##
| Property | Type | Description |
|:---------|:-----|:------------|
| value    | String | Optional.  Name of the transaction manager to be used |
| propagation | enum: Propagation |             |
| isolation | enum: Isolation |             |
| readOnly | boolean |             |
| timeout  | int (seconds) |             |
| rollbackFor | Array of Class objects, which must be derived fromThrowable. | Optional array of exception classes that must cause rollback.  |
| rollbackForClassname | Array of String class names. Classes must be derived from Throwable. | Optional array of names of exception classes that mustcause rollback.  |
| noRollbackFor | Array of Class objects, which must be derived fromThrowable. | Optional array of exception classes that must not cause rollback.  |
| noRollbackForClassname | Array of String class names, which must be derived fromThrowable. | Optional array of names of exception classes that must not cause rollback.  |

## Resources ##
  * [IBM Transaction Pitfalls](http://www.ibm.com/developerworks/java/library/j-ts1.html)