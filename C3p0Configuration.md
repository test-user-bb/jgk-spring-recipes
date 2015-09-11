# dependencies #
```
	<properties>
		<c3p0.version>0.9.1.2</c3p0.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>c3p0</groupId>
			<artifactId>c3p0</artifactId>
			<version>${c3p0.version}</version>
		</dependency>
	</dependencies>
```
# configuration #
```

	<bean id="c3poDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource"
		destroy-method="close">
		<property name="user" value="${db.username}" />
		<property name="password" value="${db.password}" />
		<property name="driverClass" value="${db.driverClassName}" />
		<property name="jdbcUrl" value="${db.url}" />
		<property name="initialPoolSize" value="00" />
		<property name="maxPoolSize" value="4" />
		<property name="minPoolSize" value="0" />
		<property name="acquireIncrement" value="1" />
		<property name="acquireRetryAttempts" value="0" />
	</bean>
```