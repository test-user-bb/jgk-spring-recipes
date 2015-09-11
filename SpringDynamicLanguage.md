# [Spring Dynamic Language](http://static.springsource.org/spring/docs/current/spring-framework-reference/html/dynamic-language.html) Support #

```
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:lang="http://www.springframework.org/schema/lang"
        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                http://www.springframework.org/schema/lang http://www.springframework.org/schema/lang/spring-lang-3.0.xsd
">
	<lang:groovy id="theGroovyExperiment" script-source="classpath:/groovy/TheGroovyExperiment.groovy">
		<lang:property name="message" value="Groovy is groovy to use!" />
	</lang:groovy>

</beans>
```
  * Groovy
```
import com.jgk.springrecipes.util.SimpleMessenger;

class Messenger implements SimpleMessenger {
	def message

	void printMessage() {
		println message
	}	
	
}
```