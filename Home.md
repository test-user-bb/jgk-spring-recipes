# Introduction #

This project is about providing some usage patterns for [Spring Framework](http://www.springsource.com/).
I am not employed by `SpringSource` nor do I speak on its behalf.  I am, however, a big fan of Spring Framework.

All the projects in here are maven-based projects.  Some are console applications.  Some are web applications.  And others are just to demonstrate an interesting portion of Spring.
# Topics #
  * Application context
  * Annotations (spring + jsr-based)
  * Beans
  * Security
  * Aspect-Oriented Programming (aop)
  * Spring expression language (el)
  * Messaging (jms)
  * Persistence (jdbc, orm, jpa)
  * OXM?
  * Testing in Spring
  * Transactions (tx)
  * Web
  * Spring MVC
  * Remoting
  * Web-Services
  * Restful Services

# Sub Projects #
## simple-repository ##
  * Demonstrate DAO or Repository
  * Provide stubs for testing
  * Think about various injection strategies
## simple-context ##
  * Ultra-simple spring project.
  * Demonstrates:
    * Loading an application context from an .xml file.
    * Provides a simple infrastructure for Spring.
## simple-context-with-spring-annotations ##
  * Shows using an annotation
  * Uses <context:component-scan .../>