### `aspectjrt.jar` ###
  * `aspectjrt.jar` file contains:
    * definitions for the various user accessible AspectJ types such as JoinPoint and Signature
    * various @AspectJ annotations
    * internal AspectJ types utilized during runtime
### Eclipse + AspectJ ###
  * [AJDT Plugin](http://www.eclipse.org/ajdt/downloads)
  * [Spring Tool Suite (STS)](http://www.springsource.com/developer/sts)
  * [![](http://www.springsource.com/files/uploads/all/images/product/Logo_sts.png)](http://www.springsource.com/developer/sts)
### stuff here ###
  * you can use the aspects written using `@AspectJ` in a Spring application without needing the AspectJ weaver
### Spring + `AspectJ` ###
  * uses dynamic proxies
  * Due to the use of proxies, it exposes method execution join points only for objects created by the Spring container (commonly known as Spring beans)
### Weaver ###
  * Weaver is used to:
    * weave together classes and aspects so the advice gets executed
    * inter-type declarations affect the static structure
    * weave-time declarations produce warnings and errors
  * Types of weaving models:
    * Source weaving
    * Binary weaving
    * Load-time weaving
#### Source Weaving ####
  * weaver is part of the compiler
  * input is java source and aspect source
  * aspects can be written in traditional or `@AspectJ` notation
#### Binary Weaving ####
  * input to weaver is .class (java and aspect) in byte-code form
  * can use jar files or class files produced using java compiler
  * General steps:
    * compile .java (non-aspects) and place in location 'x'
    * compile .aj/.java (aspects) and place in location 'y'
    * weave classes:
```
ajc –inpath x;y –aspectpath y –d woven
```
      * _The –inpath option specifies the path to the classes that are weaving targets. Because you used javac to compile the aspects, you must also pass those to –inpath so that ajc can add the necessary support methods. The -aspectpath option specifies the path to the aspects to be woven in._
#### Load-Time Weaving ####
  * uses a combination of configuration file (e.g. aop.xml), .class files (binary classes and aspects), load-time agent.
  * load-time agent forms:
    * Java VM Tools Interface (JVMTI) agent
    * classloader
    * VM- and application server–specific class preprocessor (weaves the classes as they’re loaded into
the VM)
### `aop.xml` ###
  * `META-INF/aop.xml` configuration file:
```
<aspectj>
  <aspects>
    <aspect name="aspinaction.aspects.security.SecurityAspect"/>
    <concrete-aspect name="ajia.monitoring.JDBCMonitoring" extends="ajia.monitoring.Monitoring">
      <pointcut name="monitored" expression="call(* java.sql.*.*(..))"/>
    </concrete-aspect>
  </aspects>
</aspectj>
```
  * Unlike with traditional or `@AspectJ` syntax, you can’t define or override methods in the base aspect.
  * Note that you can replace the binary operators || and && with OR and AND for pointcuts written using XML. This substitution is especially helpful for writing the && operator, which in XML is written &amp;&amp;.

_NOTE: aop.xml can include a lot more configuration information, including pointcut definitions._
  * load-time weaver requires that you explicitly specify each aspect in an aop.xml file in the META-INF directory on the classpath.  Because many classpath entries (jar files and directories) may contain an aop.xml file, the **weaver uses them all** by combining the information in them.
  * `aop.xml` files provide information about the aspects and classes participating in load-time weaving. The weaver intercepts loading of any class to weave in appropriate aspects.
  * You can also use the **`-Dorg.aspectj.weaver.loadtime.configuration`** VM option to specify an explicit list of files instead of using the default ones on the classpath. This option is useful when you want to try multiple load-time weaver
configurations quickly.
    * You enable LTW by including the –javaagent option when starting the VM:
      * Unix
```
> java –classpath classes;aspects
➥ -javaagent:${ASPECTJ_HOME}/lib/aspectjweaver.jar com.jgk.springrecipes.someapp.Main
```
      * Windows
```
> java –classpath classes;aspects
➥ -javaagent:%ASPECTJ_HOME%\lib\aspectjweaver.jar com.jgk.springrecipes.someapp.Main
```
### `@AspectJ` syntax ###
  * available with Java 5 annotations
  * main advantage of this syntax style is that you can compile your code using a plain Java compiler
  * The disadvantage of `@AspectJ` syntax is its verbosity in expressing the same constructs and its limitations in expressing certain constructs, especially in the static crosscutting category.
  * Syntax comparison:
| Traditional | `@AspectJ` |
|:------------|:-----------|
| aspect      | @Aspect    |
| pointcut    | @Pointcut  |
| before      | @Before    |
| after       | @After     |
| around      | @Around    |

### Ways to use `@AspectJ` syntax ###
  * Simplest way:  use the ajc compiler instead of javac
  * Alternate 1: include binary weaving, where code compiled using javac is then woven using **ajc**
  * Alternate 2: load-time weaving (LTW), where classes are woven as they’re being loaded into the VM

# Introduction #
  * a language specification (new elements (pseudo-keywords) like 'aspect' - which is analogous to 'class')
  * an implementation of AOP
  * Syntax choices:
    * traditional
    * `@AspectJ`
  * Crosscutting constructs:
    * common (join point, pointcut, and aspect)
    * dynamic (advice)
    * static (inter-type declarations and weave-time declarations)
  * Wildcards (`*`)
  * `..` any method parameters
  * `+` wildcard denotes subtypes.
## Pointcuts ##
  * select execution of any public method in the system:
```
execution(public * *.*(..))
```
  * same with a name given to the pointcut:
```
pointcut publicOperation() : execution(public * *.*(..));
```

### Aspect ###
  * contains the code expressing the weaving rules for both dynamcic and static crosscutting.
  * aspectcs can have :
    * data
    * methods
    * nested classes
### Advice ###
  * advice is the code executed at a join point selected by a pointcut.
  * advice can execute:
    * before
    * after
    * around
  * body of advice encapsulates the logic to be executed upon reaching a join point.

### Parts of a join point ###
  * method name
  * the _this_ object
  * method arguments

### Static crosscutting (VERY COOL!) ###
  * Inter-type declaration (aka _introduction_)
  * alters static structcre of :
    * classes
    * interfaces
    * aspects
  * enables adding a method or field to a class
  * enables declare a type to implement an interface.
  * The following makes weaver assign `AccessTracked` interface as parent of the `MessageCommunicator` class:
```
  declare parents:  MessageCommunicator implements AccessTracked;
```