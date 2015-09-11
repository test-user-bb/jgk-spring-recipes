# Introduction #
  * [java.lang.instrument](http://download.oracle.com/javase/6/docs/api/java/lang/instrument/package-summary.html)

## Maven Testing and javaagent ##
  * use the `maven-surefire-plugin` to enable the `-javaagent` switch:
```
	<build>
		<plugins>
		    <plugin>
		      <groupId>org.apache.maven.plugins</groupId>
		      <artifactId>maven-surefire-plugin</artifactId>
		      <version>${maven-surefire-plugin.version}</version>
		      <configuration>
		        <forkMode>pertest</forkMode>
		        <argLine>-javaagent:/Users/jkroub/.m2/repository/org/springframework/spring-instrument/3.0.5.RELEASE/spring-instrument-3.0.5.RELEASE.jar</argLine>
		        <!-- <argLine>-javaagent:${basedir}/target/openejb-javaagent-3.0.jar</argLine> -->
		        <workingDirectory>${basedir}/target</workingDirectory>
		      </configuration>
		    </plugin>
		</plugins>
	</build>

```