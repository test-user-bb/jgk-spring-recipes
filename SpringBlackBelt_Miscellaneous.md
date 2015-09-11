## Miscellaneous 4 questions ##
### Write XML code to inject primitive/String values into a bean ###
```
	<bean id="myMiscellaneousListSetMapPropertiesBean"
		class="com.jgk.springrecipes.blackbelt.miscellaneous.MyMiscellaneousListSetMapPropertiesBean">
		<property name="map">
			<map>
				<entry key="Fred" value="Flintstone" />
				<entry key="Barney" value="Rubble" />
				<entry key="Sam" value="Slate" />
			</map>
		</property>
		<property name="set">
			<set>
				<value>one</value>
				<value>two</value>
				<value>three</value>
			</set>
		</property>
		<property name="props">
			<value>
				first: Mike
				second: Carol
				third: Greg
				forth: Marcia
			</value>
		</property>
		<property name="someEmails">
			<props>
				<prop key="administrator">administrator@example.com</prop>
				<prop key="support">support@example.com</prop>
			</props>
		</property>
		<property name="someObject">
			<null />
		</property>
	</bean>
```
### Write XML code to inject List/Set/Map/Properties into a bean ###
### Write XML code to inject null values into a bean ###
```
<null/>
```
### Identify the different `ApplicationContext`s along with the default resource prefix for each - classpath, file system, web ###
  * File, Url, Web, Classpath
    * ClassPathResource	 Uses classpath as the default to load resources.
    * FileSystemResource	 Uses file system as the default to load resources.
    * ServletContextResource	 Uses web context as the default. Ex: '/WEB-INF/applicationContext.xml'
    * UrlResource	 Loads URL resources as the default. Built on top of java.net.URL and appropriately can handle 'http:', 'ftp:', and 'file:'
### List all the resource prefixes that can be used when pointing to the XML configuration file when creating an Application Context. ###
  * classpath:
  * file:
  * ftp:
  * http:
### Identify the correct way to point to a Spring configuration file within a package. ###
### Use Property Placeholder to refer to external properties in the Spring XML configuration ###
### Use abstract and parent properties for bean inheritance ###
### Write code to use the p namespace ###
### Differentiate between id and name attributes when creating a bean ###
### Describe a scenario where 

&lt;aop:scoped-proxy /&gt;

 is needed ###
  * Definitions of beans scoped at the request, session, globalSession and custom-scope levels require the `<aop:scoped-proxy/>` element
  * Example:
```
<bean id="legoPreferences" class="com.jgk.springrecipes.LegoPreferences" scope="session">
    <aop:scoped-proxy/>
</bean>

<bean id="legoManager" class="com.jgk.springrecipes.LegoManager">
    <property name="legoPreferences" ref="legoPreferences"/>
</bean
```
  * From spring:
```
By default, when the Spring container creates a proxy for a 
bean that is marked up with the <aop:scoped-proxy/> element, 
a CGLIB-based class proxy is created. This means that you need 
to have the CGLIB library in the classpath of your application.

Note: CGLIB proxies only intercept public method calls! 
Do not call non-public methods on such a proxy; they will not be 
delegated to the scoped target object.

Alternatively, you can configure the Spring container to create 
standard JDK interface-based proxies for such scoped beans, 
by specifying false for the value of the proxy-target-class attribute 
of the <aop:scoped-proxy/> element. Using JDK interface-based 
proxies means that you do not need additional libraries in your 
application classpath to effect such proxying. However, it also 
means that the class of the scoped bean must implement at least 
one interface, and that all collaborators into which the scoped bean
is injected must reference the bean through one of its interfaces.


```