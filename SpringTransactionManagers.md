# How to create a transaction manager? #
## Based on an `EntityManagerFactory` ##
```
<bean id="myEntityManagerBasedTransactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="myEntityManagerFactory" />
</bean>
```
## Based on a `SessionFactory` ##
```
<bean id="mySessionFactoryBasedTransactionManager"
      class="org.springframework.orm.hibernate4.HibernateTransactionManager">
      <property name="sessionFactory">
            <ref bean="mySessionFactory"/>
      </property>
</bean>
```
## Based on a `DataSource` ##
```
<bean id="myDataSourceBasedTransactionManager"
      class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
      <property name="dataSource">
            <ref bean="myDataSource"/>
      </property>
</bean>
```