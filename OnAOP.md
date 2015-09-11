  1. ummary aop stuff

  * AOP = Aspect Oriented Programming
  * Aspect: a module that encapsulates pointcuts and advice.
  * Advice: code to execute
  * Joinpoint: place in code where code can be injected.  In Spring this is just before/after methods.
  * Pointcut: Expression to identify join point

  * Q:  What problems occur with cross-cutting concerns:
    * Code tangling - method does unrelated things
    * Code scattering - duplication


  * AOP term for a service applicable to a variety of problem domains.
    * Cross cutting concert
  * What are 2 styles of AOP supported by Spring?
    * CGLib (Inheritance)
    * Dynamic Proxies (Interfaces)
  * What do you call a Spring method that implements a cross cutting concert?
    * Advice
  * What do you call a point in the business logic to which advice is applied?
    * Joinpoint
  * What do you call an expression (filter) that identifies the methods to which advice will be applied?
    * Pointcut expression
  * A module (or class) that combines pointcuts and advice?
    * Aspect
  * What are the 5 types of advice?
    * `@Before`
    * `@After`
    * `@AfterReturning`
    * `@AfterThrowing`
    * `@Around`
  * More on advice types:
    * Before advice: Advice that executes before a join point, but which does not have the ability to prevent execution flow proceeding to the join point (unless it throws an exception).
    * After returning advice: Advice to be executed after a join point completes normally: for example, if a method returns without throwing an exception.
    * After throwing advice: Advice to be executed if a method exits by throwing an exception.
    * After - Advice to be executed regardless of the means by which a join point exits (normal or exceptional return)
      * Around advice: Advice that surrounds a join point such as a method invocation. This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.

  * What XML element is required to enable AOP in Spring?
    * 

&lt;aop:aspectj-autoproxy/&gt;


  * What is the role of the _execution()_ pointcut expression?
    * To identify the methods to which advice will be applied.
  * What elements may appear in the method pattern of a pointcut expression?
    * Modifiers
    * ReturnType
    * ClassType
    * MedhodName( Parameters  ) ExceptionType
  * What are the mandatory pieces of a pointcut expression?
    * Return Type
    * Method Name()
  * The meaning of `(*)`, when it appears in the parameter list of a method name?
    * One (1) parameter of any type.
  * The meaning of ( .. ) when it appears in the parameter list of a method name?
    * 0+ parameters of any type.
  * Ways in which around advice differs from other forms of advice?
    * Around advice forwards the request and response, other forms of advice do not.
    * Around advice can be used to provide both pre and post processing
  * 3 forms of loose coupling provide by AOP?
    * POJO unaware of advice
    * Advice unaware of POJO
    * Client unaware of advice
  * In Spring AOP, advice can be applied only to:
    * Methods (not attributes or constructors)
  * What the problems associated with code tangling?
    * Lack of cohesion.
    * Unclear purpose.
    * Increased complexity.
    * Error prone cut / paste recycling.
  * Issues with code scattering?
    * Duplicated code.
    * Redundant maintenance.
    * Potential inconsistent implementations.
  * AOP incorporates features found in several well-known design patterns.
    * Filter
    * Decorator
    * Observer
    * Proxy
  * What is the payoff of this approach?
    * You implement an aspect once, then reuse it again and again. As opposed to implementing the aspect again and again or cutting / pasting the aspect again and again.
  * Aspects provide 3 kinds of decoupling:
    * The code being advised (decorated) is unaware of its advisor. This means that advice (services) can be added or removed without having to modify the component benefiting from their services.
    * The advice code typically knows little about the code being advised. This means that advice is typically reusable across a variety of components.
    * The client is unaware of the presence or absence of advice. This means that advice (services) can be added or removed without having to modify the client benefiting from their services.
  * Differences between AspectJ and Spring AOP?
    * AspectJ is faster.
    * AspectJ works by modifying the class file being wrapped in an aspect. (Byte code weaving.)
    * Spring AOP uses proxies that wrap around business components, rather than altering the components themselves.
    * AspectJ is more powerful. AspectJ offers features not available in competing AOP implementations.
      * Wraps constructors.
      * Wraps attributes.
      * Wraps static initialization blocks.
  * Spring AOP supports what 2 styles of proxies?
    * Using Spring's CGLib libraries.
      * Relies on inheritance.
      * Historically has provided slightly better performance.
      * Manifestation of the Class Adapter pattern.
      * Requires CGLib.
    * Using the JVM's standard dynamic proxy library.
      * Relies on interfaces.
      * Historically has provided slightly worse performance.
      * Manifestation of the Object Adapter pattern.
  * How are Spring Aspects applied?
    * At configuration time any business object matching a pointcut expression will be wrapped in a proxy. _(This happens during the BeanPostProcessor phase in the bean's lifecycle, when the bean instance is created.)_
    * At runtime, when a method is invoked on the business object, the pointcut expression is applied to determine whether or not to apply the advice.
    * Advice application can be based on conditions that vary at runtime.
  * What do you do if, within an Aspect, you need information about the JoinPoint. What information might you get from a JoinPoint?
    * The name of the method being invoked.
    * The arguments to the method being invoked.
    * The target of the request.
    * The type of value to be returned to the client.

  * Pointcut expression: method starting with **send** that takes a single String parameter and has a void return type:
```
execution(void send*(String))
```
  * Pointcut expression: Any method named **send** that takes a single parameter
```
execution(* send(*))
```
  * Pointcut expression: Any method named send whose first parameter is an int (the ‚ **..** signifies 0 or more parameters may follow)
```
execution(* send(int, ..))
```
  * Pointcut expression:  Any void method in the MessageServiceImpl class
```
execution(void some.package.MessageServiceImpl.*(..))
```
  * Pointcut expression: any void method named **send** in any object of type (r subtype) MessageService that takes a single parameter
```
execution(void some.package.MessageService+.send(*))
```
  * Pointcut expression: any void method starting with send that is annotated with the @RolesAllowed annotation
```
execution(@javax.annotation.security.RolesAllowed void send*(..))
```
  * Pointcut expression: there is one directory between rewards and restaurant
```
execution(* rewards.*.restaurant.*.*(..))
```
  * Pointcut expression: there may be several directories between rewards and restaurant
```
execution(* rewards..restaurant.*.*(..))
```
  * Pointcut expression: any package (or subpackage) called restaurant
```
execution(* *..restaurant.*.*(..))
```
  * Pointcut expressions - these expressions act as filters, specifying to which methods / classes advice will apply.
```
		execution(public * *(..))
		
		execution(* set*(..))
		
		execution(* com.abc.service.BankService.*(..))
		
		execution(* com.abc.service.*.*(..))
			
		execution(* com.xyz.service..*.*(..))

```
  * Describe the pointcut expression: execution(public (..))
    * Method must be public
    * Method may return any type (or void)
    * Method may have any name
    * Method may receive any number of arguments
    * SHORT:  matches every **public** method.
  * Describe the pointcut expression: execution(**set**(..))
    * Modifier pattern omitted (i.e. public, protected, private, default)
    * Method may return any type
    * Method name must begin the the word **set**
    * Method may receive any number of arguments
  * Describe the pointcut expression: execution(**com.abc.service.BankService.**(..))
    * Modifier pattern omitted (i.e. public, protected, private, default)
    * Method may return any type.
    * Method must be a member of the com.abc.service.ankService class.
    * Method may have any name.
    * Method may receive any number of arguments.
  * Describe the pointcut expression:  execution(**com.abc.service.**.**(..))
    * Modifier pattern omitted (i.e. public, protected, private, default)
    * Method may return any type
    * Class must be a member of the com.abc.service package
    * Method may have any name
    * Method may receive any number of arguments
  * Describe the pointcut expression:  execution(** com.xyz.service..**.**(..))
    * Modifier pattern omitted (i.e. public, protected, private, default)
    * Method may return any type
    * Class must be a member of the com.abc.service package or any of its subpackages
    * Class may have any name
    * Method may have any name
    * Method may receive any number of arguments
  * How are aspects implemented in Spring?
    * Spring AOP aspects are implemented using regular classes (schema-based approach)
    * Spring AOP aspects may be regular classes annotated with @Aspect annotation (@AspectJ style)
  * How does Spring model aop advice?
    * As an interceptor, maintaining a set of interceptors around a joinpoint
  * In AOP, what is meant by a Target object?
    * Object being advised by one or more aspects.
    * Also referred to as the _advised_ object.
  * What is meant by _weaving_ in AOP?
    * Linking aspects with other application types or objects to create an advised object.
  * When can weaving be done?
    * compile time (e.g. using AspectJ compiler)
    * load time
    * runtime
  * When is weaving done in Spring AOP?
    * at runtime
  * How is @AspectJ support enabled?
    * by adding 

&lt;aop:aspectj-autoproxy/&gt;

 to your spring configuration.
    * or by declaring a bean like the following:
```
  <bean class="org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator" />
```
    * you also need 2 AspectJ libraries on the classpath:
      * aspectjweaver.jar
      * aspectjrt.jar
  * Example of declaring an aspect:
    * Part 1:
```
   <bean id="myAspect" class="org.xyz.NotVeryUsefulAspect">
    <!-- configure properties of aspect here as normal - wiring -->
  </bean>
```
    * Part 2:
```
    package org.xyz;
    import org.aspectj.lang.annotation.Aspect;
    @Aspect
    public class NotVeryUsefulAspect {
    }
```
  * Aspects (classes annotated with @Aspect) may have methods and fields just like any other class.  They may also contain, advice, and introduction (inter-type) declarations.
  * Spring AOP execution pointcut designator?
```
  execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
```
  * What parts of the AOP pointcut execution designator are mandatory?
    * return type
    * name pattern (e.g. name of method)
    * param pattern ()
  * Parameters pattern?
    * () matches a method that takes no parameters
    * (..) matches any number of parameters (zero or more)
    * (**) matches a method taking one parameter of any type
    * (**,String) matches a method taking two parameters, the first can be of any type, the second must be a String.

  * Types of Advice:
    * @Before - before calling code - aborts if exception is thrown
    * @After - after unconditionally
    * @AfterReturning - after on success - takes JoinPoint and return value, can change return value thrown
    * @AfterThrowing - after on failure - takes JoinPoint and exception, can change exception type thrown
    * @Around - surrounds - takes ProceedingJoinPoint, can intercept call or eat exception
  * Best Practice: prefer least powerful advice that can do the job