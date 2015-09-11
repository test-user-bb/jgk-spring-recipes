# Introduction #
  * Data Access Object (DAO) is a front-end to a data repository
  * In Spring we refer to Repository objects
  * You can annotate a class with `@Repository` annotation.
```
@Repository
public class ProductDaoImpl implements ProductDao {

    // class body here...

}
```
  * If annotated with `@Repository` and an Exception Translation Post Processor is configured, then exceptions are translated into a runtime exception:
```

<beans>

  <!-- Exception translation bean post processor -->
  <bean class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>

  <bean id="myProductDao" class="product.ProductDaoImpl"/>

</beans>
```

# Strategy #
  * Develop a Person Repository (used to load Person objects)
  * Base element is a Person object (name info and date of birth)