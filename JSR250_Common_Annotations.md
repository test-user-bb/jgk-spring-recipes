  * [JSR-250](http://jcp.org/en/jsr/detail?id=250) - Common Annotations for the JavaTM Platform
  * `javax.annotation.*`
  * Annotations:
    * `@PostConstruct`
    * `@PreDestroy`
    * `@Generated `
    * `@Resource `
    * `@Resources `
    * `@ManagedBean `
    * Security
      * `@RunAs `
      * `@RolesAllowed `
      * `@PermitAll `
      * `@DenyAll `
      * `@DeclareRoles `
    * SQL
      * `@DataSourceDefinition `
      * `@DataSourceDefinitions `

  * To make them visible in Spring use the following:
```
  <context:annotation-config/>
```