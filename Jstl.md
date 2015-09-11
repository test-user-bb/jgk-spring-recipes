  * [JSTL Home](http://www.oracle.com/technetwork/java/index-jsp-135995.html)

## Dependencies ##
```
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <servlet-api.version>2.5</servlet-api.version>
    <jsp-api.version>2.1</jsp-api.version>
    <jstl.version>1.2</jstl.version>
  </properties>

  <dependencies>
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>servlet-api</artifactId>
  		<version>2.5</version>
  		<type>jar</type>
  		<scope>provided</scope>
  	</dependency>
  	<dependency>
  		<groupId>javax.servlet.jsp</groupId>
  		<artifactId>jsp-api</artifactId>
  		<version>2.1</version>
  		<type>jar</type>
  		<scope>provided</scope>
  	</dependency>
  	<dependency>
  		<groupId>javax.servlet</groupId>
  		<artifactId>jstl</artifactId>
  		<version>1.2</version>
  		<type>jar</type>
  		<scope>provided</scope>
  	</dependency>
  </dependencies>

```
### NOTE ###
  * without the dependencies you could get the following:
```
SEVERE: Servlet.service() for servlet release threw exception
java.lang.ClassNotFoundException: javax.servlet.jsp.jstl.core.Config
```