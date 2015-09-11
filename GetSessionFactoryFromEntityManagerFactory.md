# Introduction #
## How to get a `SessionFactory` from an `EntityManagerFactory`? ##
```
<bean id="my.sessionFactory" factory-bean="my.entityManagerFactory" factory-method="getSessionFactory"/>
```