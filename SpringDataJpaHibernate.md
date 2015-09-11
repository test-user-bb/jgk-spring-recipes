## [Spring Forum Thread](http://forum.springsource.org/showthread.php?t=108645) ##
## Items ##
  * Maven (pom.xml)
    * Project management
    * Dependencies
    * Repositories
  * Java Source
    * Domain
    * Repository
    * Test
  * Hibernate Mapping File
  * JPA Configuration (persistence.xml, orm.xml)
  * Spring Configuration
  * Logging Configuration
  * Stack trace _`Caused by: java.lang.IllegalStateException: No supertype found`_

### Maven (pom.xml) ###
  * pom.xml:
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.jgk</groupId>
	<artifactId>simple-spring-data-jpa </artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>
	<name>simple-spring-data-jpa </name>
	<url>http://maven.apache.org</url>
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<junit.version>4.8.2</junit.version>
		<maven.compiler.source>1.6</maven.compiler.source>
		<maven.compiler.target>1.6</maven.compiler.target>
		<maven.compiler.plugin.version>2.3.2</maven.compiler.plugin.version>
		<javax.inject.version>1.0-PFD-1</javax.inject.version>
		<jcl-over-slf4j.version>1.5.10</jcl-over-slf4j.version>
		<log4j.version>1.2.12</log4j.version>
		<slf4j-api.version>1.5.10</slf4j-api.version>
		<slf4j-log4j12.version>1.5.10</slf4j-log4j12.version>
		<org.hibernate.version>3.5.6-Final</org.hibernate.version>
		<spring.version>3.0.5.RELEASE</spring.version>
	</properties>

	<repositories>
		<repository>
			<id>org.springframework.maven.milestone</id>
			<name>Spring Maven Milestone Repository</name>
			<url>http://maven.springframework.org/milestone</url>
		</repository>
	</repositories>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit.version}</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>javax.inject</groupId>
			<artifactId>javax.inject</artifactId>
			<version>${javax.inject.version}</version>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>${org.hibernate.version}</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
			<type>jar</type>
			<scope>runtime</scope>
			<exclusions>
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-jpa</artifactId>
			<version>1.0.0.M2</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>${spring.version}</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-test</artifactId>
			<version>${spring.version}</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>jcl-over-slf4j</artifactId>
			<version>${jcl-over-slf4j.version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>${slf4j-api.version}</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>${slf4j-log4j12.version}</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>${log4j.version}</version>
		</dependency>
		<dependency>
			<groupId>org.hsqldb</groupId>
			<artifactId>hsqldb</artifactId>
			<version>2.0.0</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>${maven.compiler.plugin.version}</version>
				<configuration>
					<showWarnings>true</showWarnings>
					<source>${maven.compiler.source}</source>
					<target>${maven.compiler.target}</target>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```
### Java Source ###
#### Domain ####
  * Domain is simple.  Contains 2 classes (Book and Camera)
  * Book.java
```
package com.jgk.spring.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@javax.persistence.Entity
@Table(name="BOOK")
public class Book implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="BOOK_ID")
	private Long id;
	private String title;
	private String author;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
}

```
  * Camera.java
```
package com.jgk.spring.data.jpa.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Camera implements Serializable {
	
	private Long id;
	private String make;
	private String model;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}

```
#### Repository ####
  * 2 repositories based on spring-data.  One based on domain Book annotated with `@Entity` (`BookRepository`) and one based on domain Camera based on hibernate mapping file (`CameraRepository`)
  * `BookRepository`
```
package com.jgk.spring.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jgk.spring.data.jpa.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}

```
  * `CameraRepository`
```
package com.jgk.spring.data.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jgk.spring.data.jpa.domain.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {

}

```
#### Test ####
  * `SimpleSpringDataWithJpaAndHibernateTest.java`
```
package com.jgk.spring.data.jpa;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.spring.data.jpa.domain.Book;
import com.jgk.spring.data.jpa.domain.Camera;
import com.jgk.spring.data.jpa.repository.BookRepository;
import com.jgk.spring.data.jpa.repository.CameraRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/SimpleSpringDataWithJpaAndHibernateTest-config.xml"})
@SuppressWarnings("serial")
public class SimpleSpringDataWithJpaAndHibernateTest {
	
	@Inject
	BookRepository bookRepository;
	
	@Inject
	CameraRepository cameraRepository;
	
	@Test
	public void testCamera() {
		List<Camera> cameras = new ArrayList<Camera>() {{
			add(Camera.createCamera("Kodak","110"));
			add(Camera.createCamera("Nikon","SLR"));
		}};
		for (Camera camera : cameras) {
			cameraRepository.save(camera);
			System.out.println(camera.getId());
		}
		
		List<Camera> camerasRetrieved=cameraRepository.findAll();
		
		assertEquals(cameras.size(), camerasRetrieved.size());
		
	}
	
	@Test
	public void testBook() {
		List<Book> books = new ArrayList<Book>() {{
			add(Book.createBook("Moby Dick","Melville"));
			add(Book.createBook("Of Mice and Men","Steinbeck"));
		}};
		for (Book book : books) {
			bookRepository.save(book);
			System.out.println(book.getId());
		}
		
		List<Book> booksRetrieved=bookRepository.findAll();
		assertEquals(books.size(), booksRetrieved.size());
		
	}
}

```
### Hibernate Mapping File (.hbm.xml) ###
  * Camera.hbm.xml (to be replaced by entity-mapping in the **orm.xml**)
```
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE hibernate-mapping PUBLIC
    "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
    "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
	<class name="com.jgk.spring.data.jpa.domain.Camera"
		table="CAMERA">
		<id name="id" type="java.lang.Long">
			<column name="CAMERA_ID" />
			<generator class="assigned" />
		</id>
		<property name="make" type="java.lang.String" update="true"
			insert="true" column="make" />

		<property name="model" type="java.lang.String" update="true"
			insert="true" column="password" />

	</class>
</hibernate-mapping>
```
### JPA Configuration ###
  * Made up of persistence.xml and orm.xml
  * persistence.xml - located at META-INF/persistence.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
   <persistence-unit name="simpleJpa">
   </persistence-unit>
</persistence>
```
### Spring Configuration ###
  * `SimpleSpringDataWithJpaAndHibernateTest-config.xml`
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:jdbc="http://www.springframework.org/schema/jdbc" xmlns:jpa="http://www.springframework.org/schema/data/jpa"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
           http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd
           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
           ">

	<context:component-scan base-package="com.jgk.spring.data"/>
	
	<jpa:repositories base-package="com.jgk.spring.data.jpa.repository" />

	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager"
		p:entityManagerFactory-ref="entityManagerFactory" />
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
	<jdbc:embedded-database id="dataSource" type="HSQL" />
</beans>
```
### Loggin Configuration ###
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
  * orm.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://java.sun.com/xml/ns/persistence/orm http://java.sun.com/xml/ns/persistence/orm_1_0.xsd"
		version="1.0">
		
	<entity class="com.jgk.spring.data.jpa.domain.Camera">
		<table name="CAMERA"/>
		<attributes>
			<id name="id">
				<column name="CAMERA_ID"/>
				<generated-value strategy="TABLE"/>
			</id>
			<basic name="make"/>
			<basic name="model"/>
		</attributes>
	</entity>
	
</entity-mappings>		
```
### Stack trace ###
  * The following stack trace is produced if you:
    * provide hibernate mapping files (.hbm.xml) for your entity classes and you do not annotate your entity classes with `@Entity`
    * provide an annotated class (with `@Entity`) and also a mapping file (x.hbm.xml) reference in your persistence.xml. (you should use one **OR** the other, not both)
```
java.lang.IllegalStateException: Failed to load ApplicationContext
	at org.springframework.test.context.TestContext.getApplicationContext(TestContext.java:308)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:109)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:75)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:321)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.createTest(SpringJUnit4ClassRunner.java:220)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner$1.runReflectiveCall(SpringJUnit4ClassRunner.java:301)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.methodBlock(SpringJUnit4ClassRunner.java:303)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:240)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:49)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:193)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:52)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:191)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:42)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:184)
	at org.springframework.test.context.junit4.statements.RunBeforeTestClassCallbacks.evaluate(RunBeforeTestClassCallbacks.java:61)
	at org.springframework.test.context.junit4.statements.RunAfterTestClassCallbacks.evaluate(RunAfterTestClassCallbacks.java:70)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:236)
	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:180)
	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:49)
	at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'cameraRepository': FactoryBean threw exception on object creation; nested exception is java.lang.IllegalStateException: No supertype found
	at org.springframework.beans.factory.support.FactoryBeanRegistrySupport.doGetObjectFromFactoryBean(FactoryBeanRegistrySupport.java:149)
	at org.springframework.beans.factory.support.FactoryBeanRegistrySupport.getObjectFromFactoryBean(FactoryBeanRegistrySupport.java:102)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getObjectForBeanInstance(AbstractBeanFactory.java:1429)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:190)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:580)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:895)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:425)
	at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:84)
	at org.springframework.test.context.support.AbstractGenericContextLoader.loadContext(AbstractGenericContextLoader.java:1)
	at org.springframework.test.context.TestContext.loadApplicationContext(TestContext.java:280)
	at org.springframework.test.context.TestContext.getApplicationContext(TestContext.java:304)
	... 24 more
Caused by: java.lang.IllegalStateException: No supertype found
	at org.hibernate.ejb.metamodel.AbstractIdentifiableType.requireSupertype(AbstractIdentifiableType.java:74)
	at org.hibernate.ejb.metamodel.AbstractIdentifiableType.getIdType(AbstractIdentifiableType.java:162)
	at org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation.<init>(JpaMetamodelEntityInformation.java:65)
	at org.springframework.data.jpa.repository.utils.JpaClassUtils.getMetadata(JpaClassUtils.java:103)
	at org.springframework.data.jpa.repository.support.JpaRepositoryFactory.getEntityInformation(JpaRepositoryFactory.java:162)
	at org.springframework.data.jpa.repository.support.JpaRepositoryFactory.getTargetRepository(JpaRepositoryFactory.java:91)
	at org.springframework.data.jpa.repository.support.JpaRepositoryFactory.getTargetRepository(JpaRepositoryFactory.java:72)
	at org.springframework.data.repository.support.RepositoryFactorySupport.getRepository(RepositoryFactorySupport.java:132)
	at org.springframework.data.repository.support.RepositoryFactoryBeanSupport.getObject(RepositoryFactoryBeanSupport.java:107)
	at org.springframework.data.repository.support.RepositoryFactoryBeanSupport.getObject(RepositoryFactoryBeanSupport.java:36)
	at org.springframework.beans.factory.support.FactoryBeanRegistrySupport.doGetObjectFromFactoryBean(FactoryBeanRegistrySupport.java:142)
	... 35 more


```