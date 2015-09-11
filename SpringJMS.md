  * [Spring JMS Reference](http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jms.html)
## jms module ##
  * added a jms module to the source base
  * uses pojo's as jms listeners
```
<jms:listener-container connection-factory="jmsConnectionFactory">
		<jms:listener ref="clampettFamily" method="familyArrived" destination="clampett.queue.family" response-destination="clampett.queue.familyarrived"/>
		<jms:listener ref="familyArrivedLogger" method="log" destination="clampett.queue.familyarrived"/>
</jms:listener-container>
```
    * Pojo 1: `ClampettHelper`
```
package com.jgk.springrecipes.jms;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component(value="clampettFamily")
public class ClampettHelper {

	@Transactional
	public String familyArrived(String family) {
		System.out.println("familyArrived: "+family);
		return family+" clampett";
	}
}
```
    * Pojo 2: `FamilyArrivedLogger`
```
package com.jgk.springrecipes.jms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class FamilyArrivedLogger {
	private static final Logger log = Logger.getLogger(FamilyArrivedLogger.class);
	private List<String> arrivedFamilyMembers = new ArrayList<String>();

	public void log(String familyMember) {
		arrivedFamilyMembers.add(familyMember);
		log.info(familyMember);
		
	}
	
	public List<String> whoHasArrived() {
		return Collections.unmodifiableList(arrivedFamilyMembers);
	}

}
```

  * Uses 2 queues
```
	<bean id="clampettQueue" class="org.apache.activemq.command.ActiveMQQueue">
		<constructor-arg value="clampett.queue.family"/>
	</bean>
              
	<bean id="clampettArrivedQueue" class="org.apache.activemq.command.ActiveMQQueue">
		<constructor-arg value="clampett.queue.familyarrived"/>
	</bean>

```
  * Uses a `jmsTemplate` defaulting to `clampettQueue`
```
	<bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
		<property name="connectionFactory" ref="jmsConnectionFactory"/>
		<property name="defaultDestination" ref="clampettQueue"/>
	</bean>

```
  * Uses a `connectionFactory`
```
	<bean id="jmsConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
		<property name="brokerURL" value="vm://embedded?broker.persistent=false"/>
	</bean>
```
  * Uses component scanning:
```
    <context:component-scan base-package="com.jgk.springrecipes.jms"/>
```
  * Uses a batch processor:
```
package com.jgk.springrecipes.jms;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsClampettFamilyBatchProcessor implements ClampettFamilyBatchProcessor {

	@Inject
	JmsTemplate jmsTemplate;
	
	@Override
	public void processBatch(List<String> batch) {
		for (String familyMember : batch) {
			System.out.println(familyMember);
			jmsTemplate.convertAndSend(familyMember);
		}
	}

}

```
  * Uses test harness
```
package com.jgk.springrecipes.jms;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:/com/jgk/springrecipes/jms/SpringJmsTest-context.xml")
public class SpringJmsTest {
	
	@Inject
	@Named(value="clampettQueue")
	Object cq;
	
	@Inject
	ClampettFamilyBatchProcessor clampettFamilyBatchProcessor;
	
	@Inject
	FamilyArrivedLogger familyArrivedLogger;
	
	@Inject
	@Named(value="jmsConnectionFactory")
	ConnectionFactory jmsConnectionFactory;
	@Test
	public void doit() {
		System.out.println(jmsConnectionFactory);
		System.out.println(cq);
		
		List<String> family = new ArrayList<String>();
		family.add("Jethro");
		family.add("Jed");
		family.add("Granny");
		family.add("Elliemae");
		clampettFamilyBatchProcessor.processBatch(family);
		waitForBatch(family.size(), 1000);

		System.out.println();
	}

	private void waitForBatch(int batchSize, int timeout) {
		long sleepTime = 100;
		while (familyArrivedLogger.whoHasArrived().size() < batchSize && timeout > 0) {
			pause(sleepTime);
			timeout -= sleepTime;
		}
	}
	
	private void pause(long pauseTime) {
		try {
			Thread.sleep(pauseTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}

```
# Simple JMS #
  * setup a connection factory
  * setup a queue
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:jms="http://www.springframework.org/schema/jms"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
			http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
              http://www.springframework.org/schema/jms
              http://www.springframework.org/schema/jms/spring-jms-3.0.xsd
                        ">

	<bean id="connectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
		<property name="brokerURL" value="vm://embedded?broker.persistent=false"/>
	</bean>

	<bean id="myQueue" class="org.apache.activemq.command.ActiveMQQueue">
		<constructor-arg value="com.jgk.springrecipes.jms.queue.myqueue"/>
	</bean>

	<jms:listener-container>
		<jms:listener ref="myClient" method="messageFor"
                   destination="rewards.queue.dining" response-destination="rewards.queue.confirmation"/>
	</jms:listener-container>


</beans>

```
# General JMS #
  * API Walk
```
ConnectionFactory->Connection->Session->MessageProducer->send
```
# Core #
  * Message
  * Destination
  * Connection
  * Session
  * `MessageProducer`
  * `MessageConsumer`

## Dependencies ##
```
    <dependency>
        <groupId>org.apache.activemq</groupId>
        <artifactId>activemq-core</artifactId>
        <version>5.3.2</version>
    </dependency>  
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jms</artifactId>
        <version>${spring.version}</version>
    </dependency>
```
## JMS Namespace in Spring configuration ##
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:jms="http://www.springframework.org/schema/jms"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
              http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
              http://www.springframework.org/schema/jms
              http://www.springframework.org/schema/jms/spring-jms-3.0.xsd">

	<jms:listener-container>
		<jms:listener ref="rewardNetwork" method="rewardAccountFor" destination="rewards.queue.dining" response-destination="rewards.queue.confirmation"/>
	</jms:listener-container>
 
</beans>

```
## Message ##
  * from `geronimo-jms_1.1_spec-1.1.1.jar`
### Implementations javax.jms ###
  * `TextMessage`
  * `ObjectMessage`
  * `MapMessage`
  * `BytesMessage`
  * `StreamMessage`

## Destination ##
  * from `geronimo-jms_1.1_spec-1.1.1.jar`
### Implementations javax.jms ###
  * Queue
    * Point-to-point messaging
  * Topic
    * Publish/subscribe messaging
_NOTE:  difference between sending email to a single person or to a List Server_