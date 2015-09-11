# [Static Resources](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/mvc.html#mvc-static-resources) #


  * public resources:
```
<mvc:resources mapping="/resources/**" location="/public-resources/"/>
```
  * images
```
<mvc:resources mapping="/images/**" location="/images/"/>
```
  * Use a cache expiration date:
```
<mvc:resources mapping="/resources/**" location="/public-resources/" cache-period="31556926"/>
```
  * Can use multiple locations:
```
<mvc:resources mapping="/resources/**" location="/, classpath:/META-INF/public-web-resources/"/>
```