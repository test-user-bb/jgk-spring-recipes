# Introduction #
  * You can have multiple PropertyPlaceholderConfigurer beans.  You can specify an **order** in the bean definition.  The first match wins:
```
	<context:property-placeholder order="1" location="classpath:first.properties"/>
	<context:property-placeholder order="2" location="classpath:second.properties"/>
	<util:list id="wordList" list-class="java.util.LinkedList">
		<value>one</value>
		<value>two</value>
		<value>three</value>
		<value>${lastname}</value>
	</util:list>
Here the ${lastname} would be replaced with 'clampett'
--------------
first.properties:
firstname=jed
lastname=clampett
--------------
second.properties:
firstname=wilma
lastname=flintstone
```
  * PropertyPlaceholderConfigurer enables you to load properties from a .properties file and then use ${....} syntax to replace the items during Bean Factory Post Processing
  * The examples show two methods of defining your PropertyPlaceholderConfigurer
    * Using standard spring bean definition:
```
	<bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
	  <property name="location" value="classpath:myspringproperties.properties"></property>
	</bean>
```
    * Using the more concise context namespace:
```
	<context:property-placeholder location="classpath:myspringproperties.properties"/>
```
  * If you want to use multiple properties files, use the 'locations' attribute:
```
	<bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
        <property name="locations">
            <list>
                <value>classpath:myspringproperties_1.properties</value>
                <value>classpath:myspringproperties_2.properties</value>
            </list>
        </property>
	</bean>
```
  * If you want to use multiple properties files, use the 'location' attribute and a comma-separated list of files:
```
	<context:property-placeholder location="classpath:myspringproperties1.properties, classpath:myspringproperties2.properties"/>
```
  * If your properties file looks like:
```
   greeting=Hello
   love=amor
```
  * Then you can use these properties in defining other beans:
```
   <bean id="myBean" class="com.jgk.SomeBean">
       <property name="greeting">${greeting}</property>
       <property name="love">${love}</property>
   </bean>
```
  * Spring 3.0 through the introduction of expression language support enables even elements inside the definition of your PropertyPlaceholderConfigurer to be discovered at runtime.