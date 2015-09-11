# [Spring MVC](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/mvc.html) #


## General Configuration 1 question ##
### State the goal of a `ViewResolver`, `DispatcherServlet` and `HandlerMapping` ###
  * `ViewResolver` - provides a mapping between view names and actual views. The View interface addresses the preparation of the request and hands the request over to one of the view technologies.
  * `DispatcherServlet` -
  * `HandlerMapping` -
### Understand the difference between the web and main `ApplicationContext` ###
## Controllers 2 questions ##
### Write web.xml code to setup `DispatcherServlet` and `ContextLoaderListener` ###
  * web.xml:
```
<?xml version="1.0" encoding="ASCII"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	version="2.5">
	<display-name>blackbelt-mvc</display-name>
	
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/blackbeltmvc-webapp-config.xml</param-value>
	</context-param>
	
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
	
	<servlet>
		<servlet-name>blackbeltmvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>blackbeltmvc</servlet-name>
		<url-pattern>/blackbeltmvc/*</url-pattern>
	</servlet-mapping>

	<welcome-file-list>
		<welcome-file>index.html</welcome-file>
		<welcome-file>index.htm</welcome-file>
		<welcome-file>index.jsp</welcome-file>
		<welcome-file>default.html</welcome-file>
		<welcome-file>default.htm</welcome-file>
		<welcome-file>default.jsp</welcome-file>
	</welcome-file-list>
</web-app>
```
### Know the usage of `@Controller` and `@RequestMapping` ###
  * `@Controller` - indicates that a particular class serves the role of a controller.
  * `@RequestMapping` - used to map URLs such as /appointments onto an entire class or a particular handler method. Typically the class-level annotation maps a specific request path (or path pattern) onto a form controller, with additional method-level annotations narrowing the primary mapping for a specific HTTP method request method ("GET"/"POST") or specific HTTP request parameters.
_Note: To enable autodetection of such annotated controllers, you add component scanning to your configuration._ `<context:component-scan base-package="com.jgk.springrecipes.blackbelt.mvc.controllers"/> `
### State the purpose of the return value of a `@RequestMapping` method ###
  * `@RequestMapping` -
### Identify the main parameter types that can be passed to a `@RequestMapping` method ###
### State the goal of `@RequestParam` ###
  * `@RequestParam` - parameters for access to specific Servlet request parameters. Parameter values are converted to the declared method argument type.
```
	/**
	 * http://localhost:8080/blackbelt.mvc/blackbeltmvc/withparams?name=Jed&age=52
	 */
	@RequestMapping(value="/withparams")
	public String withparams( @RequestParam Integer age, @RequestParam String name ) {
		System.out.println("Name: " + name +", age="+age);
		return "redirect:http://java.sun.com";
	}

```
## REST 2 questions ##
### Identify the proper scenario for using ###
  * GET - read
  * POST - update/change
  * PUT - create
  * DELETE - delete/remove
### Write code using `@PathVariable` ###
  * `@PathVariable` method parameter annotation to indicate that a method parameter should be bound to the value of a URI template variable.
```
@RequestMapping(value="/owners/{ownerId}", method=RequestMethod.GET)
public String findOwner(@PathVariable String ownerId, Model model) {
  Owner owner = ownerService.findOwner(ownerId);  
  model.addAttribute("owner", owner);  
  return "displayOwner"
```
### Know the use of the `RestTemplate` ###
  * `RestTemplate` is the core class for client-side access to **RESTful** services.
### Write code using `RestTemplate` ###
```
	@RequestMapping(value="/cnn")
	public @ResponseBody String cnn() {
		RestTemplate rt = new RestTemplate();
		URI uri=null;
		try {
			uri = new URI("http://cnn.com");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String res = rt.getForObject(uri, String.class);
		return res;
	}

```
### Know the purpose of `@ResponseStatus` and `@ExceptionHandler` ###
  * `@ResponseStatus` - Marks a method or exception class with the status code and reason that should be returned. The status code is applied to the HTTP response when the handler method is invoked, or whenever said exception is thrown.
  * `@ExceptionHandler` - use the @ExceptionHandler method annotation within a controller to specify which method is invoked when an exception of a specific type is thrown during the execution of controller methods.
```
@Controller
public class SimpleController {

  // other controller method omitted

  @ExceptionHandler(IOException.class)
  public String handleIOException(IOException ex, HttpServletRequest request) {
    return ClassUtils.getShortName(ex.getClass());
  }
}
```