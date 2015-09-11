  * lives in **META-INF/aop.xml**
  * aop.xml is an `AspectJ` artifact.  (It is NOT a Spring artifact.)
  * `AspectJ` LTW infrastructure is configured using one or more 'META-INF/aop.xml' files, that are on the Java classpath (either directly, or more typically in jar files).
  * LTW = Load Time Weaving
  * [aop.xml structure](http://www.eclipse.org/aspectj/doc/released/devguide/ltw-configuration.html)