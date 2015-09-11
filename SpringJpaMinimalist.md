# Description #
  * The idea here is to put together the instructions for generating a very basic Spring and JPA application.
  * Part of this will be testing what is generated and then presenting the output.
## Requirements ##
  * Single database (embedded)
  * Single Repository
  * Maven-based application
### Pieces ###
  * Libraries
  * Java Code
    * Domain classes (annotated)
    * Test class
  * Configuration
    * Spring configuration
    * persistence.xml (for jpa)
    * log4j.xml (for logging)
### Domain ###
  * LogMessage
    * id
    * message
    * date (when it occurred)
    * author (not unique)
  * LogMessageRepository (and implementation JpaLogMessageRepository)
### Libraries ###
```
  <dependencies>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-annotations</artifactId>
      <version>3.5.6-Final</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-entitymanager</artifactId>
      <version>3.5.6-Final</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>3.5.6-Final</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>commons-dbcp</groupId>
      <artifactId>commons-dbcp</artifactId>
      <version>1.4</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.hsqldb</groupId>
      <artifactId>hsqldb</artifactId>
      <version>2.0.0</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-orm</artifactId>
      <version>3.0.5.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>javax.transaction</groupId>
      <artifactId>jta</artifactId>
      <version>1.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>dom4j</groupId>
      <artifactId>dom4j</artifactId>
      <version>1.6.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.6.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>commons-collections</groupId>
      <artifactId>commons-collections</artifactId>
      <version>3.2.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.6.1</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.12</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.8.2</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>3.0.5.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>3.0.5.RELEASE</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
```
### Java:Domain ###
  * `LogMessageRepository`
```
package com.jgk.springrecipes.orm.springjpaminimalist.repository;

import java.util.List;

import com.jgk.springrecipes.orm.springjpaminimalist.domain.LogMessage;

public interface LogMessageRepository {
	List<LogMessage> findAll();
	void save(LogMessage lm);
}

```
  * `JpaLogMessageRepository`
```
package com.jgk.springrecipes.orm.springjpaminimalist.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jgk.springrecipes.orm.springjpaminimalist.domain.LogMessage;

@Repository
public class JpaLogMessageRepository implements LogMessageRepository {
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<LogMessage> findAll() {
		return entityManager.createQuery("select p from LogMessage p",
				LogMessage.class).getResultList();
	}

	@Override
	@Transactional
	public void save(LogMessage logMessage) {
		entityManager.persist(logMessage);
	}
}

```
  * `LogMessage`
```
package com.jgk.springrecipes.orm.springjpaminimalist.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_MSG")
public class LogMessage {

	@Override
	public String toString() {
		return "LogMessage [id=" + id + ", message=" + message + ", author="
				+ author + ", date=" + date + "]";
	}

	private Long id;
	private String message, author;
	private Date date;

	@Id
	@GeneratedValue
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static LogMessage createLogMessage(String message, String author) {
		LogMessage logMessage = new LogMessage();
		logMessage.setMessage(message);
		logMessage.setAuthor(author);
		logMessage.setDate(new Date());
		return logMessage;
	}

}

```
### Java:Test ###
  * Integration test to check everything works
```
package com.jgk.springrecipes.orm.springjpaminimalist;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.springrecipes.orm.springjpaminimalist.domain.LogMessage;
import com.jgk.springrecipes.orm.springjpaminimalist.repository.LogMessageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/com/jgk/springrecipes/orm/springjpaminimalist/LogMessageRepositoryTest-config.xml")
public class LogMessageRepositoryTest {
	
	@Autowired
	LogMessageRepository logMessageRepository;
	
	@Test
	public void jed() {
		LogMessage hemingway = LogMessage.createLogMessage("Old man and the Sea","Hemingway");
		LogMessage melville = LogMessage.createLogMessage("Moby Dick","Melville");
		List<LogMessage> msgs = new ArrayList<LogMessage>();
		msgs.add(hemingway);
		msgs.add(melville);
		for (LogMessage logMessage : msgs) {
			logMessageRepository.save(logMessage);
			assertNotNull(logMessage.getId());
		}
		
		List<LogMessage> stuff = logMessageRepository.findAll();
		assertNotNull(stuff);
		assertFalse(stuff.isEmpty());
		assertEquals(msgs.size(), stuff.size());
		
	}
}

```

### persistence.xml ###
  * This file needs to end up on your classpath at location ` /META-INF/persistence.xml `
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="1.0"
      xmlns="http://java.sun.com/xml/ns/persistence"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
      http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd">
  <persistence-unit name="logMessagePersistenceUnit"/>
</persistence>
```
### Spring configuration ###
  * Two files are used here
    * repository-config.xml (com/jgk/springrecipes/orm/springjpaminimalist/repository/repository-config.xml)
    * `LogMessageRepositoryTest-config.xml` (`classpath:/com/jgk/springrecipes/orm/springjpaminimalist/LogMessageRepositoryTest-config.xml`)
  * `repository-config.xml`
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">

	<bean id="logMessageRepository" class="com.jgk.springrecipes.orm.springjpaminimalist.repository.JpaLogMessageRepository" />

	<!-- Instructs the container to look for beans with @Transactional and decorate them -->
	<tx:annotation-driven transaction-manager="transactionManager" />

	<!-- Enable annotation-config, needed for @PersistenceContext annotations -->
	<context:annotation-config />
		
</beans>		
```
  * `LogMessageRepositoryTest-config.xml`
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
	
	<import resource="classpath:com/jgk/springrecipes/orm/springjpaminimalist/repository/repository-config.xml"/>
	
	<!-- FactoryBean that creates the EntityManagerFactory  -->
	<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="jpaVendorAdapter">
			<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter"/>
		</property>
		<property name="jpaProperties">
			<props>
				<prop key="hibernate.show_sql">true</prop>
				<prop key="hibernate.format_sql">true</prop>
				<prop key="hibernate.hbm2ddl.auto">create-drop</prop>
			</props>
		</property>
		<property name="dataSource" ref="dataSource" />
	</bean>

	<!-- Creates an in-memorydatabase not-populated with test data for fast testing -->
	<jdbc:embedded-database id="dataSource">
	<!-- 
		<jdbc:script location="classpath:logmessage/testdb/schema.sql"/>
		<jdbc:script location="classpath:logmessage/testdb/test-data.sql"/>
	 -->
	</jdbc:embedded-database>

	<!-- A transaction manager for working with JPA EntityManagerFactories -->
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory" />
	</bean>	
</beans>		
```
### log4j ###
  * log4j.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
  <appender name="console" class="org.apache.log4j.ConsoleAppender"> 
    <param name="Target" value="System.out"/> 
    <layout class="org.apache.log4j.PatternLayout"> 
      <param name="ConversionPattern" value="%-5p %c{1} - %m%n"/> 
    </layout> 
  </appender>
   
  <root> 
    <priority value ="error" /> 
    <appender-ref ref="console" /> 
  </root>
  
</log4j:configuration>
```
### Output ###
  * Here is the output from running the integration test.
```
Hibernate: 
    insert 
    into
        LOG_MSG
        (id, author, date, message) 
    values
        (default, ?, ?, ?)
Hibernate: 
    insert 
    into
        LOG_MSG
        (id, author, date, message) 
    values
        (default, ?, ?, ?)
Hibernate: 
    select
        logmessage0_.id as id0_,
        logmessage0_.author as author0_,
        logmessage0_.date as date0_,
        logmessage0_.message as message0_ 
    from
        LOG_MSG logmessage0_

```