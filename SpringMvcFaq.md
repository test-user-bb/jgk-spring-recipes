  * [Why are my @Controller annotations ignored?](SpringMvcFaq#Why_are_my_@Controller_annotations_ignored?.md)
  * [SpringMvcFaq#](SpringMvcFaq#.md)
  * [SpringMvcFaq#](SpringMvcFaq#.md)
  * [SpringMvcFaq#](SpringMvcFaq#.md)
  * [SpringMvcFaq#](SpringMvcFaq#.md)

### Why are my @Controller annotations ignored? ###
  * Make sure you have the following:
```
<context:component-scan base-package="com.jgk.springrecipes.mvc.release.controllers"/>
```

```
<?xml version="1.0" encoding="UTF-8"?>
<beans 
    xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:p="http://www.springframework.org/schema/p"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				        http://www.springframework.org/schema/context
				        http://www.springframework.org/schema/context/spring-context-3.0.xsd
						http://www.springframework.org/schema/mvc
						http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
						http://www.springframework.org/schema/util
						http://www.springframework.org/schema/util/spring-util-3.0.xsd
				              "							
              >
              
    <!-- JSR-303 support will be detected on classpath and enabled automatically -->
	<context:component-scan base-package="com.jgk.springrecipes.mvc.release.controllers"/>   
    <mvc:annotation-driven />
	
</beans>

```