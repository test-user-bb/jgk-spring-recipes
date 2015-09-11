  * [How to log on an exception?](AspectJExamples#How_to_log_on_an_exception?.md)
  * [How to aspect static main?](AspectJExamples#How_to_aspect_static_main?.md)

### How to aspect static main? ###
Simple example where we shoot messages out _**around**_ a static main call.
### Simple:staticArtifacts ###
  * java (.java) source to be advised
  * aspects (.aj)
  * compile with ajc (aspectj compiler)
  * run the application
#### Simple:java source ####
  * file:  jed/Jedatron.java
```
package jed;

public class Jedatron {

   public static void main(String[] args) {
     System.out.println("Hello Jedatron");
   }
}
```
#### Simple:aspects source ####
  * file:  jed/JedAspects.java
```
package jed;

aspect JedAspects {
   pointcut statMain(): execution(static void main(..));
   Object around(): statMain() {
      System.out.println("Main it up in ASPECT");
      Object result = proceed();
      System.out.println("PROCEED ATRON");
      return result;
   }
}

```
#### Simple:compile source ####
```
$ ajc -cp /Users/jkroub/aspectj1.6/lib/aspectjrt.jar JedAspects.aj Jedatron.java
```
### Simple:run application ###
```
$ java -cp /Users/jkroub/aspectj1.6/lib/aspectjrt.jar:.. jed.Jedatron
```
### Simple:output of application ###
```
Main it up in ASPECT
Hello Jedatron
PROCEED ATRON
```


### How to log on an exception? ###
  * whenever any public method of an interface or class in the com.jgk.springrecipes package throws an error, that error is logged before being thrown to its caller.
```
aspect PublicErrorLogging {
    Log log = new Log();

    pointcut publicInterface(Object o):
        call(public * com.jgk.springrecipes.*.*(..)) && target(o);

    after(Object o) throwing (Error e): publicInterface(o) {
        log.write(o, e);
    }
}
```