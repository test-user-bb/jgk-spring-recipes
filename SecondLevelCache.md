# Related #
  * [Forum Entry](http://forum.springsource.org/showthread.php?110176-SpringDataJpa-Cannot-use-Pageable-with-an-Entity-that-has-a-composite-key-amp-Oracle&highlight=pageable)
  * [from terracotta jira](https://jira.terracotta.org/jira/browse/EHC-881)
  * [COUNT operator](https://hibernate.onjira.com/browse/HHH-5419) - Oliver indicates a problem in Oracle and other RDBMS.

## Code to reproduce ##

## Error ##
```
Caused by: org.hibernate.cache.NoCachingEnabledException: Second-level cache is not enabled for usage [hibernate.cache.use_second_level_cache | hibernate.cache.use_query_cache]
	at org.hibernate.cache.internal.NoCachingRegionFactory.buildTimestampsRegion(NoCachingRegionFactory.java:81)
	at org.hibernate.cache.spi.UpdateTimestampsCache.<init>(UpdateTimestampsCache.java:63)
	at org.hibernate.internal.SessionFactoryImpl.<init>(SessionFactoryImpl.java:459)
	at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:1726)
	at org.hibernate.ejb.EntityManagerFactoryImpl.<init>(EntityManagerFactoryImpl.java:76)
	at org.hibernate.ejb.Ejb3Configuration.buildEntityManagerFactory(Ejb3Configuration.java:907)
	... 44 more

```

### Cause ###
  * using incorrect properties (should use `hibernate.cache.region.factory_class`, not `hibernate.cache.provider_class`)
  * here is a proper persistence.xml:
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
	version="2.0">
	<persistence-unit name="firstdbPersistenceUnit">
		<provider>org.hibernate.ejb.HibernatePersistence</provider>
		
		<class>com.jgk.fdb.domain.Radimef0</class>
		<class>com.jgk.fdb.domain.Radimmo5</class>
 		<exclude-unlisted-classes>true</exclude-unlisted-classes>
		
		<properties>
			<property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect" />
			<property name="hibernate.dialect" value="org.hibernate.dialect.Oracle10gDialect" />
			<property name="hibernate.connection.driver_class" value="oracle.jdbc.driver.OracleDriver" />
			<property name="hibernate.connection.username" value="db_username" />
			<property name="hibernate.connection.password" value="db_password" />
			<property name="hibernate.connection.url" value="jdbc:oracle:thin:@hostname:1521:dbname" />
			<property name="hibernate.cache.use_query_cache" value="true"/>
			<property name="hibernate.cache.use_second_level_cache" value="true"/>
			<property name="hibernate.cache.region.factory_class" value="org.hibernate.cache.ehcache.EhCacheRegionFactory"/>
			<property name="hibernate.ejb.naming_strategy" value="org.hibernate.cfg.DefaultComponentSafeNamingStrategy"/>
			<property name="hibernate.show_sql" value="true" />
			<property name="hibernate.format_sql" value="true" />
			<property name="hibernate.hbm2ddl.auto" value="create" />
<!-- 			<property name="hibernate.hbm2ddl.auto" value="create-drop" /> -->
		</properties>
	</persistence-unit>
</persistence>
```

---

## Proper Settings ##
```

hibernate.query.factory_class=org.hibernate.hql.classic.ClassicQueryTranslatorFactory
#hibernate.cache.provider_class=org.hibernate.cache.EhCacheProvider
hibernate.cache.provider_class=net.sf.ehcache.hibernate.EhCacheProvider
#hibernate.cache.region.factory_class=net.sf.ehcache.hibernate.EhCacheRegionFactory
hibernate.cache.use_query_cache=true
hibernate.cache.use_second_level_cache=true

```
## Symptom ##
```
Caused by: org.hibernate.cache.NoCachingEnabledException: Second-level cache is not enabled for usage [hibernate.cache.use_second_level_cache | hibernate.cache.use_query_cache]
	at org.hibernate.cache.impl.NoCachingRegionFactory.buildEntityRegion(NoCachingRegionFactory.java:70)
	at org.hibernate.impl.SessionFactoryImpl.<init>(SessionFactoryImpl.java:291)
	at org.hibernate.cfg.Configuration.buildSessionFactory(Configuration.java:1385)
	at org.springframework.orm.hibernate3.LocalSessionFactoryBean.newSessionFactory(LocalSessionFactoryBean.java:860)
	at org.springframework.orm.hibernate3.LocalSessionFactoryBean.buildSessionFactory(LocalSessionFactoryBean.java:779)
	at org.springframework.orm.hibernate3.AbstractSessionFactoryBean.afterPropertiesSet(AbstractSessionFactoryBean.java:211)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.invokeInitMethods(AbstractAutowireCapableBeanFactory.java:1477)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1417)
	... 64 more

```