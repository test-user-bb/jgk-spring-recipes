# [Spring Remoting](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/remoting.html) #
## Remoting 2 questions ##
### Explain the benefits of Spring Remoting over direct RMI ###
  * Easier
### Know the goal of the RMI Service Exporter and RMI Proxy Generator ###
  * `RmiServiceExporter` - on server
```
<bean class="org.springframework.remoting.rmi.RmiServiceExporter">
    <!-- does not necessarily have to be the same name as the bean to be exported -->
    <property name="serviceName" value="AccountService"/>
    <property name="service" ref="accountService"/>
    <property name="serviceInterface" value="example.AccountService"/>
    <!-- defaults to 1099 -->
    <property name="registryPort" value="1199"/>
</bean>
```
  * `RmiProxyFactoryBean` - on client
```
<bean class="example.SimpleObject">
    <property name="accountService" ref="accountService"/>
</bean>

<bean id="accountService" class="org.springframework.remoting.rmi.RmiProxyFactoryBean">
    <property name="serviceUrl" value="rmi://HOST:1199/AccountService"/>
    <property name="serviceInterface" value="example.AccountService"/>
</bean>
```
### Write XML code to configure the RMI Service Exporter and RMI Proxy Generator ###
_NOTE: see previous section._
### Identify the difference between the RMI Service Exporter, HttpInvoker and Hessian/Burlap ###
  * Spring HTTP invokers use the standard Java serialization mechanism to expose services through HTTP.
  * Hessian offers a binary HTTP-based remoting protocol. It is developed by Caucho and more information about Hessian itself can be found at [CAUCHO](http://www.caucho.com).
### Know whether `HttpInvoker` requires a web server on the server or client side ###
  * For the client it doesn't matter, the server has to be a `Web Server` (i.e. tomcat, jetty or the like).
  * See forum post [HERE](http://forum.springsource.org/showthread.php?t=88765).