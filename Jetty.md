# Introduction #
  * Let's say you have a maven project that is of type 'war'
  * You want to see if it works.  You can use the [jetty plugin](http://docs.codehaus.org/display/JETTY/Maven+Jetty+Plugin) to help
# Steps #
  * add the jetty plugin to the build section of your pom.xml
```
<build>
  ...
  <plugins>
     <plugin>
        <groupId>org.mortbay.jetty</groupId>
        <artifactId>maven-jetty-plugin</artifactId>
        <version>6.1.10</version>
        <configuration>
        </configuration>

     </plugin>
  </plugins>
  ...
</build>
```
  * run the jetty plugin
```
mvn jetty:run
```
  * access the application using your project's artifact id (e.g. jedweb)
```
  http://localhost:8080/jedweb
```
# Customizations/Configurations #
  * by default jetty listens on 8080, you can change this by setting the `jetty.port` system property:
```
mvn -Djetty.port=9999 jetty:run
```
  * you can place configuration properties in an external file and reference inside the plugin configuration section:
```
        <configuration>
             <jettyConfig>myjetty.xml</jettyConfig>
        </configuration>
```
  * 
```
        <configuration>
             <webAppConfig>
               <contextPath>/test</contextPath>
               <tempDirectory>${project.build.directory}/work</tempDirectory>

             </webAppConfig>
        </configuration>
```