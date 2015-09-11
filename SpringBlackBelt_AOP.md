## [Spring AOP](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/aop.html) ##
### Define AOP keywords ###
  * Aspect - a modularization of a concern that cuts across multiple classes.
  * Advice - action taken by an aspect at a particular join point.
  * Pointcut - a predicate that matches join points.
  * Joinpoint - a point during the execution of a program, such as the execution of a method or the handling of an exception.
### Write basic pointcuts using [HELP](http://www.eclipse.org/aspectj/doc/released/progguide/semantics-pointcuts.html) ###
  * `execution()`
  * `*` matches a method taking one parameter of any type
  * `..` matches any number of parameters (zero or more)
  * `+`
  * `()` matches a method that takes no parameters
  * `(*,String)` matches a method taking two parameters, the first can be of any type, the second must be a String
### Format of an execution expression ###
```
execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)
          throws-pattern?)
```
### Identify which classes/methods match a provided pointcut ###
### List the five Advice types ###
  * `@Before` - Advice that executes before a join point, but which does not have the ability to prevent execution flow proceeding to the join point (unless it throws an exception).
  * `@After` - Advice to be executed regardless of the means by which a join point exits (normal or exceptional return).
  * `@AfterReturning` - Advice to be executed after a join point completes normally: for example, if a method returns without throwing an exception.
  * `@AfterThrowing` - Advice to be executed if a method exits by throwing an exception.
  * `@Around` - Advice that surrounds a join point such as a method invocation. This is the most powerful kind of advice. Around advice can perform custom behavior before and after the method invocation. It is also responsible for choosing whether to proceed to the join point or to shortcut the advised method execution by returning its own return value or throwing an exception.
### Write an Advice ###
### Identify which Advice types can accomplish a task ###
### Identify which Advice type is preferred when multiple can be used ###
  * Recommend that you use the least powerful advice type that can implement the required behavior.
  * Using the most specific advice type provides a simpler programming model with less potential for errors. For example, you do not need to invoke the proceed() method on the JoinPoint used for around advice, and hence cannot fail to invoke it.
### AOP: Configuration 2 questions ###
#### State the XML configuration required to enable scanning for @Aspect ####
```
	<aop:aspectj-autoproxy/>
```
#### Use code to completely configure aspects in XML ####
```
	<aop:config>
		<aop:aspect id="saveAndGetAspect" ref="anotherPctAspect">
			<aop:pointcut id="savePointcut"
				expression="execution(* com.jgk.springrecipes.aop.pct.*.save*(..))" />
			<aop:pointcut id="getPointcut"
				expression="execution(* com.jgk.springrecipes..*.get*(..))" />
			<aop:before pointcut-ref="savePointcut" method="saveit" />	
			<aop:before pointcut-ref="getPointcut" method="getit" />	
		</aop:aspect>
	</aop:config>

```
### AOP: Proxies 2 questions ###
#### Identify where proxies are created in the Spring lifecycle ####
#### Know proxies share interface with object they wrap ####
#### List limitations of Spring AOP when compared with AspectJ ####
  * Spring AOP only method execution join points (advising the execution of methods on Spring beans).
  * Field interception is not implemented.