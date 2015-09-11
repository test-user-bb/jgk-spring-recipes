# Introduction #
  * has spring3.1.0.xxx, Servlet 2.5, jsp 2.1, and jstl support.
```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.jgk.spring31hib4</groupId>
	<artifactId>web-spring31-hib4</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>

	<name>web-spring31-hib4</name>
	<url>http://maven.apache.org</url>
	<properties>
		<commons-logging.version>1.1.1</commons-logging.version>
		<jsp-api.version>2.1</jsp-api.version>
		<jstl.version>1.1.2</jstl.version>
		<junit.version>4.9</junit.version>
		<log4j.version>1.2.12</log4j.version>
		<org.springframework.version>3.1.0.RC2</org.springframework.version>
		<org.springframework-security.version>3.1.0.RELEASE</org.springframework-security.version>
		<servlet-api.version>2.5</servlet-api.version>
		<slf4j-api.version>1.6.1</slf4j-api.version>
		<slf4j-log4j12.version>1.6.1</slf4j-log4j12.version>
		<taglibs.standard.version>1.1.2</taglibs.standard.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-struts</artifactId>
			<version>${org.springframework.version}</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${org.springframework.version}</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-core</artifactId>
			<version>${org.springframework-security.version}</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
			<version>${org.springframework-security.version}</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>${org.springframework-security.version}</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>${commons-logging.version}</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>${log4j.version}</version>
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
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>${servlet-api.version}</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>${jsp-api.version}</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>${jstl.version}</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>taglibs</groupId>
			<artifactId>standard</artifactId>
			<version>${taglibs.standard.version}</version>
		</dependency>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit.version}</version>
			<scope>test</scope>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>2.3.2</version>
				<configuration>
					<source>1.6</source>
					<target>1.6</target>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
```