## Annotations 4 questions ##
### Know how to enable component scanning ###
```
<context:component-scan base-package="com.jgk.springrecipes"/>
```
### Know how to turn on scanning for annotations such as `@Required` and `@PreDestroy` ###
  * 2 ways to do this:
```
<context:annotation-config/>
```

```
<context:component-scan base-package="com.jgk.springrecipes"/>
```
### Identify relationships between byType, byName and constructor autowiring ###
  * [Autowiring...](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-factory-autowire)
### Use @Autowired for field, setter and constructor injection ###
### Use @Qualifier with @Autowired ###
```
public class SomeBean {

  @Autowired
  @Qualifier("favoriteSob")
  private SomeOtherBean sob;

  // ...
}

```

```
@Autowired
  public void prepare(@Qualifier("favoriteSob") SomeOtherBean sob,
                      Integer quantity) {
      this.sob = sob;
      this.quantity = quantity;
  }

```

```
  <bean class="com.jgk.springrecipes.SomethingUsingSob">
      <qualifier value="favorite"/>
      <!-- inject any dependencies required by this bean -->
  </bean
```
### Define purpose of `@PostConstruct` and `@PreDestroy` ###
  * Related to `CommonBeanPostProcessor`
  * `@PostConstruct` is akin to init-method
  * `@PreDestroy` is akin to destroy-method