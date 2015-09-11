Project that shows dependency injection into domain objects
  * [Details about weaving (including performance implications)](http://hugunin.net/papers/aosd-2004-cameraReady.pdf)
  * Added Hibernate Interceptor demonstration - see `CandleTest` and related classes.
  * Added JPA support with `EntityManager` et al.
  * Added load time weaving, beefed up pom.xml to add -javaagent on test.
  * [Hibernate Interceptor Example](http://www.jblewitt.com/blog/?p=129)
  * [Using Interceptor in JPA](http://thoughts.seul.in/2009/07/hibernate-jpa-interceptors-hooks-for-entities/)
  * [Anemic domain model](http://www.martinfowler.com/bliki/AnemicDomainModel.html)
  * [Spring AOP](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/aop.html#aop-atconfigurable) see 7.8.1 Using AspectJ to dependency inject domain objects with Spring
  * Added a pojo (`LegoIndianaJonesPojo`) to be used as the non-spring managed pojo.  The objective is to be able to inject the `LegoService` component into this pojo in a way that does not require using `-javaagent`.
  * Added simple `@Aspect` usage (see `IndianaJonesAspect`)
  * Added `LegoService`
  * Added maven and spring scaffolding
  * ![http://www.manning.com/laddad/laddad_cover150.jpg](http://www.manning.com/laddad/laddad_cover150.jpg) Fender - Books - Computer - Manning - Manning - AspectJ in Action Enterprise AOP with Spring Applications 2nd-2010.pdf
  * [Code for AspectJ in Action](http://manning.com/laddad2/)