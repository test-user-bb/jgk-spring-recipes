  * [Hades home page](http://redmine.synyx.org/projects/hades)
### General ###
  * Hades simplifies and standardizes the way DAO objects are developed.
  * You only define an interface and Hades does the rest.
  * Includes some great DSL to simplify creation of some sophisticated queries.
### Dependency ###
```
  <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <javax.inject.version>1.0-PFD-1</javax.inject.version>
  </properties>
    <dependencies>
	<dependency>
	     <groupId>org.synyx.hades</groupId>
	     <artifactId>org.synyx.hades</artifactId>
	     <version>2.0.2.RELEASE</version>
	     <exclusions>
	     	<exclusion>
	     		<groupId>org.springframework</groupId>
	     		<artifactId>org.springframework.orm</artifactId>
	     	</exclusion>
	     </exclusions>
	 </dependency>
    </dependencies>
```
### Configuration Files ###
#### ` MyHadesTest-config.xml ` ####
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				              http://www.springframework.org/schema/context
				              http://www.springframework.org/schema/context/spring-context-3.0.xsd"							
              >

	<import resource="classpath:/com/jgk/springrecipes/jpa/hades/my-app-config.xml"/>

</beans>

```
#### ` my-app-config.xml ` ####
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:hades="http://schemas.synyx.org/hades"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				              http://www.springframework.org/schema/context
				              http://www.springframework.org/schema/context/spring-context-3.0.xsd
				              http://schemas.synyx.org/hades http://schemas.synyx.org/hades/hades.xsd
				              "							
              >

	<import resource="classpath:/com/jgk/springrecipes/jpa/hades/infrastructure-config.xml"/>
	
	<hades:dao-config base-package="com.jgk.springrecipes.jpa.hades.dao" />
</beans>

```
#### ` infrastructure-config.xml ` ####
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:hades="http://schemas.synyx.org/hades" 
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
							http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				              http://www.springframework.org/schema/context
				              http://www.springframework.org/schema/context/spring-context-3.0.xsd
							http://www.springframework.org/schema/jdbc
							http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
				              http://schemas.synyx.org/hades http://schemas.synyx.org/hades/hades.xsd
						http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
				              ">

	<context:annotation-config/>
	<context:component-scan base-package="com.jgk.springrecipes.jpa.hades"/>
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
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory" />
	</bean>	
   <jdbc:embedded-database id="dataSource" type="HSQL"/>
	
</beans>

```
### ` src/main/resources/META-INF/persistence.xml ` ###
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
   <persistence-unit name="loveHades">
   </persistence-unit>
</persistence>
```
### User ###
```
package com.jgk.springrecipes.jpa.hades.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="USERS")
@NamedQuery(name="User.findByLastname",query="from User u where u.lastname = ?1")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	private String username;
	private String lastname;
	private Integer age;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", lastname="
				+ lastname + ", age=" + age + "]";
	}
	public static User createUser(String username, String lastname, Integer age) {
		User user = new User();
		user.setUsername(username);
		user.setLastname(lastname);
		user.setAge(age);
		return user;
	}


}

```
### `UserDao` ###
```
@Repository
public interface UserDao extends GenericDao<User, Long> {
	// Will trigger the NamedQuery due to a naming convention
	List<User> findByLastname(String lastname);
	 // Will create a query from the methodname
	 // from User u where u.username = ?
	 User findByUsername(String username);

	 // Uses query annotated to the finder method in case you
	// don't want to pollute entity with query info
	@Query("from User u where u.age > ?1")
	List<User> findByAgeGreaterThan(int age);
	
	List<User> findByAgeLessThan(int age);
	
	List<User> findByAgeGreaterThanOrderByAgeDesc(int age);

}

```
### ` MyHadesTest ` ###
```
package com.jgk.springrecipes.jpa.hades;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jgk.springrecipes.jpa.hades.dao.UserDao;
import com.jgk.springrecipes.jpa.hades.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/com/jgk/springrecipes/jpa/hades/MyHadesTest-config.xml")
public class MyHadesTest {

	@Inject
	UserDao userDao;
	
	@PersistenceContext
	EntityManager entityManager;
	
//	@Test
//	@Transactional
	public void other() {
		createUsers();
		Query q=entityManager.createNamedQuery("User.findByLastname");
		q.setParameter(1, "Smith");
		System.out.println(q.getResultList());
		
	}
	
	@Test
	public void testIt() {
		System.out.println(userDao);
		System.out.println(userDao.count());
		List<User>users=createUsers();
		System.out.println(userDao.count());
		
		assertEquals(Long.valueOf(users.size()), userDao.count());
		List<User> votingAdults = userDao.findByAgeGreaterThan(18);
		System.out.println(votingAdults);
		System.out.println("No. Voting Adults: "+votingAdults.size());
		List<User> nonVotingAdults = userDao.findByAgeLessThan(18);
		System.out.println("No. Non-Voting Adults: "+nonVotingAdults.size());
//		userDao.findByAgeGreaterThanOrderByAgeDesc(10);
		System.out.println(userDao.findByAgeGreaterThanOrderByAgeDesc(10));
		
	}
	
	List<User> createUsers() {
		List<User> users= new ArrayList<User>();
		users.add(User.createUser("DSMITH","Smith",15));
		users.add(User.createUser("JED","Clampett",83));
		users.add(User.createUser("JED","ClampettSe",76));
		for (User user : users) {
			userDao.save(user);
		}
		return users;
	}
}

```