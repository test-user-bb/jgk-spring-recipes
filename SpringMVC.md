## How to do it ##
  * Deploy `DispatcherServlet`
  * Implement a request handler (controller)
  * Implement Views
  * Register Controller with `DispatcherServlet`
  * Deploy / Test
### `DispatcherServlet` ###
```
<servlet-mapping>
   <servlet-name>somestuff</servlet-name>
   <url-pattern>/somestuff/*</url-pattern>
</servlet-mapping>
```
  * `http://host:portnum/context/somestuff/show?id=345`
### Config ###
```
  <context:component-scan base-package="com.jgk.some"/>
```
### Controller time ###
```
@Controller
public class SomeController {
  private SomeService myService;
  
  @Autowired
  public SomeController(SomeService svc) {
      this.myService=svc;
  }
  @RequestMapping("/some/show")
  public String show(@RequestParam("id") long id, Model model) {
     SomeThing something = myService.lookupSomething(id);
     model.addAttribute(something);
     return "someView";   // selects the 'someView' to render SomeThing
  }
}
```
### View time ###
  * `/WEB-INF/views/someView.jsp`
  * Here's the view markup:
```
<html>
  <head><title>Some Thing</title></head>
  <body>
    Amount=${something.amount}<br/>
    Date=${something.date}<br/>
    Account Number=${something.account.id}<br/>
    Store Number=${something.storeNumber}<br/>
  </body>
</html>
```
### Setup config ###
```
<beans>
   <bean class="org.springframework.web....InternalResourceViewResolver">
       <property name="prefix" value="/WEB-INF/views/" />
       <property name="suffix" value=".jsp" />
   </bean>
</beans>
```
## Interesting JSR's ##
  * [JSR-303](http://jcp.org/aboutJava/communityprocess/final/jsr303/index.html) Bean Validation API
    * define a meta-data model and API for JavaBeanTM validation based on annotations, with overrides and extended meta-data through the use of XML validation descriptors.
## Related Annotations ##
  * `@Controller`
  * `@CookieValue`
  * `@RequestHeaders`
## MVC Namespace ##
  * Provides default conversion service, validator and `HttpMessageConverters`
    * `<mvc:annotation-driven/>`
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/mvc
        http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd">

    <mvc:annotation-driven/>

</beans>
```
  * `mvc:annotation-driven` - This tag registers the DefaultAnnotationHandlerMapping and AnnotationMethodHandlerAdapter beans that are required for Spring MVC to dispatch requests to @Controllers.
## Pieces of the Spring MVC Puzzle ##
  * `DispatcherServlet`
  * Handlers
  * Views
## View Resolvers ##
  * You want to position a [InternalResourceViewResolver](http://static.springsource.org/spring/docs/3.0.x/api/org/springframework/web/servlet/view/InternalResourceViewResolver.html) ([UrlBasedViewResolver](http://static.springsource.org/spring/docs/3.0.x/api/org/springframework/web/servlet/view/UrlBasedViewResolver.html)) last.  You can only use one of these since the underlying implementation makes use of a [RequestDispatcher](http://download.oracle.com/javaee/6/api/javax/servlet/RequestDispatcher.html) call - which can only be done once.
## WARNING ##
  * Note
_When using controller interfaces (e.g. for AOP proxying), make sure to consistently put all your mapping annotations - such as `@RequestMapping` and `@SessionAttributes` - on the controller interface rather than on the implementation class._
## Handler return types ##
  * `ModelAndView`
  * `Model`
  * `Map`
  * `View`
  * `String` value that is interpreted as the logical view name
  * `void` if the method handles the response itself (by writing the response content directly, declaring an argument of type `ServletResponse` / `HttpServletResponse` for that purpose) or if the view name is supposed to be implicitly determined through a `RequestToViewNameTranslator` (not declaring a response argument in the handler method signature).
  * If the method is annotated with `@ResponseBody`, the return type is written to the response HTTP body. The return value will be converted to the declared method argument type using `HttpMessageConverters.`
    * Sample using `@ResponseBody` to return raw html:
```
	@RequestMapping("/friend")
	@ResponseBody
	public String theFriend() {
		return "<html><body><h1>FRIEND Hello There JSON</h1></body></html>";
	}
```
  * `HttpEntity<?>` or `ResponseEntity<?>` object to provide access to the Servlet reponse HTTP headers and contents. The entity body will be converted to the response stream using `HttpMessageConverters`.
  * Any other return type is considered to be a single model attribute to be exposed to the view, using the attribute name specified through `@ModelAttribute` at the method level (or the default attribute name based on the return type class name). The model is implicitly enriched with command objects and the results of `@ModelAttribute` annotated reference data accessor methods.
## Annotations ##
  * annotation-based programming model for MVC controllers that uses annotations such as
    * `@RequestMapping`
    * `@RequestParam`
    * `@ModelAttribute`
## Reference ##
  * [Spring MVC Reference](http://static.springsource.org/spring/docs/3.0.x/reference/mvc.html)
  * [Spring MVC javadoc](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/)
  * [MultipartHttpServletRequest](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/web/multipart/MultipartHttpServletRequest.html) - file upload support.
  * `WebApplicationContext` is bound in the `ServletContext`, and by using static methods on the [RequestContextUtils](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/web/servlet/support/RequestContextUtils.html) class you can always look up the `WebApplicationContext` if you need access to it.
  * In the Web MVC framework, each [DispatcherServlet](http://static.springsource.org/spring/docs/3.0.x/javadoc-api/org/springframework/web/servlet/DispatcherServlet.html) has its own `WebApplicationContext`, which inherits all the beans already defined in the root `WebApplicationContext`.
![http://static.springsource.org/spring/docs/3.0.x/reference/images/mvc-contexts.gif](http://static.springsource.org/spring/docs/3.0.x/reference/images/mvc-contexts.gif)
  * `DispatcherServlet` is an expression of the [“Front Controller” design pattern](http://java.sun.com/blueprints/patterns/FrontController.html).
  * ![http://static.springsource.org/spring/docs/3.0.x/reference/images/mvc.png](http://static.springsource.org/spring/docs/3.0.x/reference/images/mvc.png)
## Dependency ##
  * WebMvc
```

  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-webmvc</artifactId>
  		<version>${spring.version}</version>
  		<type>jar</type>
  		<scope>compile</scope>
  	</dependency>
```
## web.xml ##
  * web.xml with `ContextLoaderListener`
```
  <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>/WEB-INF/jed-webapp-config.xml</param-value>
  </context-param>
  <listener>
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

```
  * web.xml
```
<?xml version="1.0" encoding="ASCII"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">
  <display-name>mvc-first</display-name>
  <servlet>
        <servlet-name>example</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>example</servlet-name>
        <url-pattern>*.form</url-pattern>
    </servlet-mapping>
</web-app>
```