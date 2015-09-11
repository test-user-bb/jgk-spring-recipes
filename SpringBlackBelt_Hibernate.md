## Hibernate 1 question ##
### Write XML to configure a `SessionFactoryBean` in the following ways: ###
  * listing each entity
  * scanning for annotations
  * listing each hbm file

#### listing each entity ####
```
	<bean id="mySessionFactory"
		class="org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
 		<property name="annotatedClasses">
 			<list>
 				<value>com.jgk.springrecipes.blackbelt.dataaccess.domain.LegoBrick</value>
 			</list>
 		</property>
		<property name="mappingDirectoryLocations">
			<list>
				<value>${hibernate-mappings-dir}</value>
			</list>
		</property>
		<property name="namingStrategy">
			<ref local="myDefaultPrefixNamingStrategy" />
		</property>
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">${hibernate.dialect}</prop>
				<prop key="hibernate.show_sql">false</prop>
			</props>
		</property>
	</bean>
```
#### scanning for annotations ####
  * Annotated Entity:
```
package com.jgk.springrecipes.blackbelt.dataaccess.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="LEGO_BRICK")
@Access(AccessType.PROPERTY)
public class LegoBrick implements Serializable {
	Long id;
	String color;
	String dimensions; // 2x4
	
	@Id
	@Column(name="LEGO_BRICK_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDimensions() {
		return dimensions;
	}
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	
}

```

#### listing each hbm file ####
  * Usage:
```
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
		destroy-method="close">
		<property name="driverClassName" value="${jdbc.driverClassName}" />
		<property name="url" value="${jdbc.url}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
	</bean>
	<bean id="myDefaultPrefixNamingStrategy" class="org.hibernate.cfg.DefaultNamingStrategy" />
	<bean id="mySessionFactory"
		class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="mappingResources">
			<list>
				<value>${hibernate-mappings-dir}/Username.hbm.xml</value>
			</list>
		</property>
		<property name="namingStrategy">
			<ref local="myDefaultPrefixNamingStrategy" />
		</property>
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">${hibernate.dialect}</prop>
				<prop key="hibernate.show_sql">false</prop>
			</props>
		</property>
	</bean>
```
  * Mapping file:  Username.hbm.xml
```
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE hibernate-mapping PUBLIC
    "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
    "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping>
	<class name="com.jgk.springrecipes.blackbelt.dataaccess.domain.Username"
		table="username">
		<id name="username" type="java.lang.String">
			<column name="USERNAME" />
			<generator class="assigned" />
		</id>
		<property name="interimName" type="java.lang.String" update="true"
			insert="true" column="interim_Name" />

		<property name="password" type="java.lang.String" update="true"
			insert="true" column="password" />

		<property name="passwordExpirationDate" type="java.sql.Date"
			update="true" insert="true" column="password_Expiration_Date" />

		<property name="passwordReminder" type="java.lang.String"
			update="true" insert="true" column="password_Reminder" />
		<property name="passwordStatus" type="java.lang.String"
			update="true" insert="true" column="password_Status" />
		<property name="personId" type="java.lang.String" update="true"
			insert="true" column="person_Id" />

		<property name="userExpirationDate" type="java.sql.Date"
			update="true" insert="true" column="user_Expiration_Date" />
		<property name="userType" type="java.lang.String" update="true"
			insert="true" column="user_Type" />
	</class>

</hibernate-mapping>
```