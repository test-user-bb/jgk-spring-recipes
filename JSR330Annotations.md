  * [Spec home page](http://jcp.org/aboutJava/communityprocess/final/jsr330)
  * Spring supports JSR-330 annotations if they are on the classpath.
  * JSR-330 Annotations:
    * ` @Inject  `
    * ` @Named  `
    * ` @Qualifier  `
    * ` @Scope  `
    * ` @Singleton `
  * JSR-330 Interfaces:
    * ` Provider  `
  * Maven dependency
```
<dependency>
  <groupId>javax.inject</groupId>
  <artifactId>javax.inject</artifactId>
  <version>1.0-PFD-1</version>
</dependency>
```
## Examples ##
  * `@Inject`
```
Identifies injectable constructors, methods, and fields. May apply to static as well as instance members. An injectable member may have any access modifier (private, package-private, protected, public). Constructors are injected first, followed by fields, and then methods. Fields and methods in superclasses are injected before those in subclasses. Ordering of injection among fields and among methods in the same class is not specified.

   public class Car {
     // Injectable constructor
     @Inject public Car(Engine engine) { ... }

     // Injectable field
     @Inject private Provider<Seat> seatProvider;

     // Injectable package-private method
     @Inject void install(Windshield windshield, Trunk trunk) { ... }
   }

```
  * `@Named`
```
   public class Car {
     @Inject @Named("driver") Seat driverSeat;
     @Inject @Named("passenger") Seat passengerSeat;
     ...
   }
```
  * `@Qualifier`
```
A qualifier may annotate an injectable field or parameter and, combined with the type, identify the implementation to inject. Qualifiers are optional, and when used with @Inject in injector-independent classes, no more than one qualifier should annotate a single field or parameter. The qualifiers are bold in the following example:

   public class Car {
     @Inject private @Leather Provider<Seat> seatProvider;

     @Inject void install(@Tinted Windshield windshield,
         @Big Trunk trunk) { ... }
   }
```