# Introduction #
  * This shows basic usage of JMX
  * You write a class and it implements an interface (same name as class with 'MBean' suffix)
  * You bootstrap an MBeanServer and register your MBean(s) with the server using an ObjectName
  * Then you start a JMX Agent (e.g. jconsole, jvisualvm, etc.) and look at the MBean(s).

## MBean ##
```
package com.jgk.springrecipes.jmx.plain;

public class Hello implements HelloMBean {
	   private String message = null;

	   public Hello() {
	      message = "Hello there";
	   }

	   public Hello(String message) {
	      this.message = message;
	   }

	   public void setMessage(String message) {
	      this.message = message;
	   }

	   public String getMessage() {
	      return message;
	   }

	   public void sayHello() {
	      System.out.println(message);
	   }
}

```
## MBean Interface ##
```
package com.jgk.springrecipes.jmx.plain;

public interface HelloMBean {
	public void setMessage(String message);

	public String getMessage();

	public void sayHello();
}

```
## Agent Code ##
```
package com.jgk.springrecipes.jmx.plain;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class SimpleAgent {
	private MBeanServer mbeanServer;
	public SimpleAgent() {

	      // Get the platform MBeanServer
		mbeanServer = ManagementFactory.getPlatformMBeanServer();

	      // Unique identification of MBeans
	      Hello helloBean = new Hello();
	      ObjectName helloName = null;

	      try {
	         // Uniquely identify the MBeans and register them with the platform MBeanServer 
	         helloName = new ObjectName("SimpleAgent:name=hellothere");
	         mbeanServer.registerMBean(helloBean, helloName);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	   }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    SimpleAgent agent = new SimpleAgent();
	    System.out.println("SimpleAgent is running...");
	    SimpleAgent.waitForEnterPressed();
	    System.out.println("Good-bye");
	
	}
	 // Utility method: so that the application continues to run
	private static void waitForEnterPressed() {
		try {
			System.out.println("Press  to continue...");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

```
## Launch jconsole ##
```
  $ jconsole
```