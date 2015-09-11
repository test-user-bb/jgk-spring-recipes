### Method: 1 [Spring reference](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/aop.html#aop-aj-ltw) ###
  * Use the `@Configurable` annotation
  * Use `-javaagent` Java 5 VM flag - this requires spring-instrument since need `InstrumentationSavingAgent`
```
-javaagent:/Users/jkroub/.m2/repository/org/springframework/spring-instrument/3.0.5.RELEASE/spring-instrument-3.0.5.RELEASE.jar
```
  * Use `<context:spring-configured/>` in your Spring config
  * Next:  _`Remember that the LoadTimeWeaver exists just as a mechanism for Spring's LTW infrastructure to add one or more ClassFileTransformers.`_
  * Include `<context:load-time-weaver/>`
    * Registers weaving beans:
```
DEFAULT:
org.springframework.context.weaving.AspectJWeavingEnabler@6f69b66e

without weaver-class defined:
org.springframework.context.weaving.DefaultContextLoadTimeWeaver@44755866 (named loadTimeWeaver)

with: 
loadTimeWeaver-org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver@30943653
<context:load-time-weaver weaver-class="org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver"/>

NOT SURE (since does not work in junit test):
    <context:load-time-weaver weaver-class="org.springframework.instrument.classloading.ReflectiveLoadTimeWeaver"/>
(from dependency:  spring-instrument-tomcat)

```
  * Include file on classpath `META-INF/aop.xml`
  * Spring config:
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">

    <context:load-time-weaver/>

</beans>
```
  * Maven dependencies:
```
	<properties>
		<aspectjweaver.version>1.6.8</aspectjweaver.version>
		<aspectjrt.version>1.6.8</aspectjrt.version>
		<spring.version>3.0.5.RELEASE</spring.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-instrument</artifactId>
			<version>${spring.version}</version>
		</dependency>
 		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>${aspectjweaver.version}</version>
		</dependency>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${aspectjrt.version}</version>
		</dependency>
	</dependencies>

```
### One Method ###
  * Use the `@Configurable` annotation
  * Use `-javaagent` Java 5 VM flag
```
 -javaagent:/Users/jkroub/.m2/repository/org/aspectj/aspectjweaver/1.6.8/aspectjweaver-1.6.8.jar
```
  * Use `<context:spring-configured/>` in your Spring config
  * Do not include `<context:load-time-weaver/>`
  * Include file on classpath `META-INF/aop.xml`
```
<!DOCTYPE aspectj PUBLIC
        "-//AspectJ//DTD//EN" "http://www.eclipse.org/aspectj/dtd/aspectj.dtd">
<aspectj>
    <weaver options="-verbose">
        <include within="com.gs.fdb..*"/>
    </weaver>
    <aspects>
        <aspect name="org.springframework.beans.factory.aspectj.AbstractInterfaceDrivenDependencyInjectionAspect"/>
    </aspects>
</aspectj>
```
  * Maven dependencies:
```
	<properties>
		<aspectjweaver.version>1.6.8</aspectjweaver.version>
		<aspectjrt.version>1.6.8</aspectjrt.version>
	</properties>

	<dependencies>
 		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>${aspectjweaver.version}</version>
		</dependency>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${aspectjrt.version}</version>
		</dependency>
	</dependencies>
```
```
@Configurable(preConstruction=true,dependencyCheck=true)
@Entity
@Table(name="RNDC14")
public class Rndc14 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	Robcd0Repository robcd0Repository;
	
	
	public Robcd0Repository getRobcd0Repository() {
		return robcd0Repository;
	}
	@Inject 
	public void setRobcd0Repository( Robcd0Repository robcd0Repository) {
		this.robcd0Repository = robcd0Repository;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NDC")
	private String ndc;

	@Column(name="AD")
	private String ad;

       ...more content here....
	public List<Robcd0> getOrangeBookRecords() {
		List<Robcd0> res = getRobcd0Repository().findCustomHereWithParam(getObc());
		return res;
	}
	
}

```