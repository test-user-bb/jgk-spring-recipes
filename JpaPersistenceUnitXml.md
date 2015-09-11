  * [Issues](JpaPersistenceUnitXml#Issues.md)
  * [Format 2.0](JpaPersistenceUnitXml#Format_2.0.md)
  * [Format 1.0](JpaPersistenceUnitXml#Format_1.0.md)
  * [EntityManagerFactory](JpaPersistenceUnitXml#EntityManagerFactory.md)
# Introduction #
## Format 2.0 ##
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
   <persistence-unit name="manager1">
  	<class>com.jgk.springrecipes.jpa.domain.Person</class>
      <properties>
         <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>
         <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
         <property name="hibernate.showSql" value="true"/>
      </properties>
   </persistence-unit>
</persistence>

```
## Format 1.0 ##
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="1.0"
      xmlns="http://java.sun.com/xml/ns/persistence"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
      http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd">

  <persistence-unit name="myJoinedSubclassPersistenceUnit"/>
  
</persistence>
```

## Issues ##
  * the name of a file "persistence.xml" will be favored over those specified in the descriptor for an `EntityManagerFactory`.
## `EntityManagerFactory` ##

```
	<bean id="myEntityManagerFactory"
		class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="dataSource" ref="myDataSource" />
		<property name="persistenceUnitName" value="My-Persisence-Unit" />
		<property name="jpaVendorAdapter">
			<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
				<property name="showSql" value="${my.hibernate.show_sql}" />
				<property name="generateDdl" value="false" />
				<property name="databasePlatform" value="${my.hibernate.dialect}" />
			</bean>
		</property>
		<property name="persistenceXmlLocation" value="classpath:META-INF/my-persistence.xml" />
		<property name="jpaPropertyMap">
			<map>
				<entry key="hibernate.hbm2ddl.auto" value="${my.hibernate.hbm2ddl.auto}" />
				<entry key="hibernate.ejb.naming_strategy" value="${gs.hibernate.ejb.naming_strategy}" />
				<entry key="hibernate.format_sql" value="${my.hibernate.format_sql}" />
				<entry key="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>
			</map>
		</property>
	</bean>

```