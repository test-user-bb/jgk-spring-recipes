  * Make properties available to SpEL:
```
<util:properties id="applicationProps" location="/WEB-INF/spring/application.properties"/>
```
  * `application.properties` files:
```
application.version=1.0.0
```
  * Using SpEL:
```
<mvc:resources mapping="/resources-#{applicationProps['application.version']}/**" location="/public-resources/"/>
```