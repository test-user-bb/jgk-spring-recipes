  * [Building Spring](SpringUpdates#Building_Spring.md)
  * [Interesting ant targets](SpringUpdates#Interesting_ant_targets.md)
  * [Spring Downloads](http://www.springsource.com/download/community)
## Building Spring ##
  * [blog instructions](http://blog.springsource.com/2009/03/03/building-spring-3/)
  * download source from svn
```
$ svn co https://src.springsource.org/svn/spring-framework/trunk spring-framework
```
  * build it (cd build-spring-framework) - 'Total time: 13 minutes 3 seconds'
```
$ cd build-spring-framework
$ ant
```
  * bundle:
```
$ ant jar
```
## Version Modify ##
  * The default version is `3.1.0.BUILD-SNAPSHOT`.
  * If you want to change this in your local maven, simple modify the pom.xml to indicate your desired build version:
    * First modify the pom.xml files:
```
  $ groovy ReplaceStringInPomXmlFile.groovy
```
    * Next run the install-maven-central:
```
 $ ant install-maven-central
```
## Interesting ant targets ##
  * jar - build the software
  * install-maven
  * install-maven-central (THIS ONE)