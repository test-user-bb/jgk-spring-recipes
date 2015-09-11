# Spring 3 Certification Mock #

  * [Black Belt Factory](http://www.blackbeltfactory.com/QuestionnaireDefDisplay.wwa?questPublicId=1712)

## General  3 questions ##
  * Define Dependency Injection and Inversion of Control
  * List the advantages of Dependency Injection
  * Be able to write XML code for constructor injection
  * Be able to write XML code for setter injection
  * List the (non-web) scopes for beans
  * Identify the default scope for beans

## Lifecycle  4 questions ##
  * Use the init-method and destroy-method attributes in XML
  * Identify when singleton and prototype beans are instantiated
  * Know that `BeanPostProcessors` allow for custom modification of new bean instances, e.g. checking for marker interfaces or wrapping them with proxies.
  * Know that `BeanFactoryPostProcessors` for custom modification of an application context's bean definitions, adapting the bean property values of the context's underlying bean factory.
  * Know the container lifecycle sequence with respect to XML Parsing, `BeanFactoryPostProcessors`, `BeanPostProcessors`, validation, init method, `@PostConstruct`

## Annotations  4 questions ##
  * Know how to enable component scanning
  * Know how to turn on scanning for annotations such as `@Required` and `@PreDestroy`
  * Identify relationships between byType, byName and constructor autowiring
  * Use `@Autowired` for field, setter and constructor injection
  * Use `@Qualifier` with `@Autowired`
  * Define purpose of `@PostConstruct` and `@PreDestroy`

## Miscellaneous  4 questions ##
  * Write XML code to inject primitive/String values into a bean
  * Write XML code to inject List/Set/Map/Properties into a bean
  * Write XML code to inject null values into a bean
  * Identify the different `ApplicationContexts` along with the default resource prefix for each - classpath, file system, web
  * List all the resource prefixes that can be used when pointing to the XML configuration file when creating an Application Context.
  * Identify the correct way to point to a Spring configuration file within a package.
  * Use Property Placeholder to refer to external properties in the Spring XML configuration
  * Use abstract and parent properties for bean inheritance
  * Write code to use the p namespace
  * Differentiate between id and name attributes when creating a bean
  * Describe a scenario where `<aop:scoped-proxy />` is needed

## `JavaConfig`  2 questions ##
  * Identify the purpose of the `@Bean` annotation
  * Identify the purpose of the `@Configuration` annotation
  * Write a method using `@Bean`
  * Write code using one `@Bean` method that calls another `@Bean` method

## Testing  3 questions ##
  * Know the purpose of `SpringJUnit4ClassRunner`
  * Write the annotation to set up the context configuration for one or more XML files
  * Identify the annotation to enable transaction support in a test
  * Differentiate between tests that commit and rollback their changes
  * Define unit tests and integration tests including which access the Spring container

## AOP ##
  * Define AOP keywords
    * Aspect
    * Advice
    * Pointcut
    * Joinpoint
  * Write basic pointcuts using
    * execution()
    * `*`
    * `..`
    * `+`
  * Identify which classes/methods match a provided pointcut
  * List the five Advice types
    * `@Before`
    * `@After`
    * `@AfterReturning`
    * `@AfterThrowing`
    * `@Around`
  * Write an Advice
  * Identify which Advice types can accomplish a task
  * Identify which Advice type is preferred when multiple can be used
### AOP: Configuration  2 questions ###
  * State the XML configuration required to enable scanning for @Aspect
  * Use code to completely configure aspects in XML
### AOP: Proxies  2 questions ###
  * Identify where proxies are created in the Spring lifecycle
  * Know proxies share interface with object they wrap
  * List limitations of Spring AOP when compared with AspectJ

## Data Access and Transactions ##
  * List advantages of `DataAccessException` over `SQLException`
  * Write XML to define a datasource using a JNDI reference
  * Write XML to define a datasource using a standalone connection pool
### `JdbcTemplate`  3 questions ###
  * State whether `JdbcTemplate` throws checked or unchecked exceptions
  * State whether you throw checked or unchecked exceptions in the callback methods
  * Know when to use SimpleJdbcTemplate and JdbcTemplate
  * Write code using JdbcTemplate to write select and update queries
  * Write code using JdbcTemplate to pass parameters to a query
  * Know when to use each of the following callbacks and write code using them:
    * `RowMapper`
    * `RowCallbackHandler`
    * `ResultSetExtractor`
### Hibernate  1 question ###
  * Write XML to configure a `SessionFactoryBean` in the following ways:
    * listing each entity
    * scanning for annotations
    * listing each hbm file

## Transaction  3 questions ##
  * Write XML code to configure a local transaction manager
  * Write XML code to configure a JTA transaction manager
  * Write XML code to configure transactions using AOP
  * Know XML to enable @Transaction
  * Understand how `TransactionTemplate` is used to rollback a transaction
  * Identify which exceptions cause a rollback by default
  * Understand main attributes for Transaction:
    * readOnly
    * isolation
    * propagation
    * rollbackFor
    * noRollbackFor
  * State the default isolation and propagation settings
  * Know the difference between PROPAGATION\_REQUIRED and PROPAGATIONS\_REQUIRES\_NEW
  * Know the difference between READ\_COMMITTED and READ\_UNCOMMITTED

## Spring MVC and REST ##
### General Configuration  1 question ###
  * State the goal of a `ViewResolver`, `DispatcherServlet` and `HandlerMapping`
  * Understand the difference between the web and main `ApplicationContext`
### Controllers  2 questions ###
  * Write web.xml code to setup `DispatcherServlet` and `ContextLoaderListener`
  * Know the usage of `@Controller` and `@RequestMapping`
  * State the purpose of the return value of a `@RequestMapping` method
  * Identify the main parameter types that can be passed to a `@RequestMapping` method
  * State the goal of `@RequestParam`

### REST  2 questions ###
  * Identify the proper scenario for using
    * GET
    * POST
    * PUT
    * DELETE
  * Write code using `@PathVariable`
  * Know the use of the `RestTemplate`
  * Write code using `RestTemplate`
  * Know the purpose of `@ResponseStatus` and `@ExceptionHandler`

## Advanced Topics ##
### Remoting  2 questions ###
  * Explain the benefits of Spring Remoting over direct RMI
  * Know the goal of the RMI Service Exporter and RMI Proxy Generator
  * Write XML code to configure the RMI Service Exporter and RMI Proxy Generator
  * Identify the difference between the RMI Service Exporter, `HttpInvoker` and Hessian/Burlap
  * Know whether HttpInvoker requires a web server on the server or client side

### Security  2 questions ###
  * Define the Spring security filter chain
  * Write XML code to configure Spring security intercept URLs
  * Write XML code to configure method level security
  * Write code using `@Secured` and `@RolesAllowed`
  * List authentication manager types
  * Know whether any authenticate managers support password encoding
  * State the tag used in a JSP to see if authorized to certain role

### JMS  2 questions ###
  * Know purpose of `JmsTemplate`
  * Write XML code to declare JMS listener
  * Write XML code to make a POJO a JMS listener
  * Know whether JMS can run in Tomcat

### JMX  2 questions ###
  * Know the role of the `MBeanExporter`
  * Write XML code to export a POJO as an `MBean`
  * Write XML code to register an existing `MBean` with the `MBeanServer`
  * Know purpose of `@ManagedResource`, `@ManagedOperation` and `@ManagedAttribute`