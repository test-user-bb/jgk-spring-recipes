# Introduction #
  * `settings.xml` is the project descriptor for a maven project
  * by default it is located at `~/.m2/settings.xml`
  * sometimes you want to use a different rendition of this file.

## How to use a distinct pom.xml? ##
  * like this:
```
mvn --settings ${MAVEN2_HOME}/conf/settings.xml jetty:run
```