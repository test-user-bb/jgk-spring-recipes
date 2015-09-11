  * [Spring Remoting](http://static.springsource.org/spring/docs/2.5.x/reference/remoting.html)

### Goals of Spring Remoting ###
  * Hide 'plumbing' code
  * Configure and expose services **declaratively**
  * Support multiple protocols in a consistent way

### Advantages: Spring Remoting vs. RMI ###
#### Problems with traditional remoting and RMI ####
  * Big Picture: _Remoting mechanisms provide an abstraction over transport details._
  * Abstractions are 'leaky', meaning the code must conform to a particular model.
  * RMI:
    * `Service` interface extends **`Remote`**
    * `Service` client extends **`UnicasterRemoteObject`**
    * `Client` must catch **`RemoteException`s**
    * **Violates a separation of concerns**
    * **Couples business logic to remoting infrastructure**
  * RMI model is invasive - server and client code is coupled to the framework
    * RMI: server-side exposes a skeleton
    * RMI: client-side invokes methods on a stub (proxy)
    * RMI: java serialization is used for marshalling
  * Spring's RMI Service Exporter
    * Transparently expose an existing `POJO` service to the RMI registry
      * no need to write binding code
    * Avoids typical RMI requirements:
      * Service interface does not extend `Remote`
      * Service class is a `POJO`

### Spring Remoting:  Declarative Approach ###
  * Spring's abstraction uses a configuration-based approach
  * Server side:
    * Expose existing services with **NO** code changes!
  * Client side:
    * Invoke remote methods from existing code
    * Take advantage of polymorphism by using dependency injection
### Spring Remoting: Consistency across protocols ###
  * Spring's exporters and proxy `FactoryBean`s bring consistent approach to multiple protocols:
    * provides flexibility
    * promotes ease of adoption
  * Server Side
    * expose a single service over multiple protocols
  * Client Side
    * switch easily between protocols (RMI, `HttpInvoker`, Hessian/Burlap)
      * Hessian - binary web service protocol
      * Burlap - xml web service protocol
    * migrate between remote vs. local deployments

### Goal/Cfg of RMI Service Exporter ###
  * Spring provides **exporters** to handle server-side requirements:
    * binding to registry or exposing an endpoint
    * conforming to a programming model if needed

#### Configuring RMI Service Exporter ####
  * Star with an existing POJO service:
```
  <bean id="myService" class="com.jgk.MyServiceImpl">
    <property name="myRepository" ref="someMyRepository"/>
  </bean>
```
  * Define a bean to export it
```
  <bean class="org.springframework.remoting.rmi.RmiServiceExporter">
    <property name="serviceName" value="myService"/>  (binds to rmiRegistry as "myService")
    <property name="serviceInterface" value="com.jgk.MyService"/>
    <property name="service" ref="myService"/>
    <property name="registryPort" value="1099"/>  (not required but enables changing RMI registry port)
  </bean>
```

### Goal/Cfg of RMI Proxy Generator ###
  * Spring provides `FactoryBean`s that generate **proxies** to handle client-side requirements
    * Communicate with server-side endpoint
    * convert remote exceptions to a runtime hierarchy
#### Configuring RMI Proxy ####
  * define a factory bean to generate the proxy
```
  <bean id="myService"
        class="org.springframework.remoting.rmi.RmiProxyFactoryBean">
    <property name="serviceInterface" value="com.jgk.MyService"/>
    <property name="serviceUrl" value="rmi://jgk:1099/myService"/>
  </bean>
```
  * Inject the factory bean into the client
```
  <bean id="myDesktopUI" class="com.jgk.client.MyDesktopUI">
    <property name="myService" ref="myService"/>
  </bean>
```

### Difference between RMI Service Exporter and `HttpInvoker` ###
### `HttpInvoker` ###
  * Lightweight HTTP-based remoting protocol:
    * method invocation is converted to an HTTP Post
    * method result is returned as an HTTP response
    * method parameters and return values are marshalled with standard Java serialization.
  * Spring's HTTP invoker. Spring provides a special remoting strategy which allows for Java serialization via HTTP, supporting any Java interface (just like the RMI invoker). The corresponding support classes are `HttpInvokerProxyFactoryBean` and `HttpInvokerServiceExporter`.
#### Configuring `HttpInvoker` Service Exporter (server) ####
  * start with an existing POJO
```
  <bean id="myService" class="com.jgk.MyServiceImpl">
     <property name="myRepository" ref="myRepository"/>
  </bean>
```
  * define a bean to export the POJO
```
  <bean name="/servitup"  (endpoint for HTTP request handling)
     class="org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter">
    <property name="serviceInterface" value="com.jgk.MyService"/>
    <property name="service" ref="myService"/>
  </bean>
```
#### Configuring `HttpInvoker` Proxy (client) ####
  * define a factory bean to generate the proxy
```
  <bean id="myService"
       class="org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean">
    <property name="serviceInterface" value="com.jgk.MyService"/>
    <property name="serviceUrl" value="http://jgkhost:8080/services/servitup"/>  (HTTP Post requests sent to this URL)
  </bean>
```
  * inject the factory bean into the client
```
   <bean id="myDesktopUI" class="com.jgk.MyDesktopUI">
      <property name="myService" ref="myService"/>
   </bean>
```
### `HttpInvoker` Questions ###
  * Does `HttpInvoker` require running on a web server on the client side?
    * **`HttpInvokerServiceExporter`**
  * Does `HttpInvoker` require running on a web server on the server side?
    * **`HttpInvokerProxyFactoryBean`**


### Hessian and Burlap ###
  * lightweight protocols for sending XML over HTTP
  * Hessian is binary
  * Burlap is textual
  * Server-Side
    * `org.springframework.remoting.caucho.HessianServiceExporter`
    * `org.springframework.remoting.caucho.BurlapServiceExporter`
  * Client-Side
    * `org.springframework.remoting.caucho.HessianProxyFactoryBean`
    * `org.springframework.remoting.caucho.BurlapProxyFactoryBean`

### Choosing Remoting protocol ###
  * If spring is running on both client and server:
    * `HttpInvoker`
  * If Java environment without a web server:
    * `RMI`
  * If you need to interoperate with other languages using HTTP:
    * Hessian
  * If you need to interoperate with other languages without HTTP:
    * `RMI-IIOP` (CORBA)
  * When using Java serialization:
    * classes & interfaces must be available on the client
    * Versions must match
  * If serving up public clients beyond your control, Web Services (REST or SOAP) are good option:
    * document-based messaging promotes loose coupling