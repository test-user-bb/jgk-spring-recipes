# Introduction #
  * problems during integration has forced a rethink on strategy.  to that end we'll be building a fresh web app based on newest spring and hibernate and spring security to make sure the basics are working.

## Steps ##
  * create maven based web app (and test)
    * org.codehaus.mojo.archetypes:webapp-jee5
```
  <groupId>com.jgk.spring31hib4</groupId>
  <artifactId>web-spring31-hib4</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>

```
  * add spring dependencies (and test)
  * add simple spring context to web.xml (and test)
  * add spring security dependencies (and test)
  * add hibernate dependencies (and test)
  * add some persistence (and test)