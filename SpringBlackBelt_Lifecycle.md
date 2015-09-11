## Lifecycle 4 questions ##
### Use the init-method and destroy-method attributes in XML ###
  * init-method
```
```
  * destroy-method
```
```
### Identify when singleton and prototype beans are instantiated ###
  * singleton - instantiated during application context initialization
  * prototype - instantiated when requested (e.g. `applicationContext.getBean('....');`)
### Know that `BeanPostProcessor`s allow for custom modification of new bean instances, e.g. checking for marker interfaces or wrapping them with proxies. ###
### Know that `BeanFactoryPostProcessor`s for custom modification of an application context's bean definitions, adapting the bean property values of the context's underlying bean factory. ###
```
package com.jgk.springrecipes.blackbelt.lifecycle.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("Could change the world here!");

	}

}
	<bean class="com.jgk.springrecipes.blackbelt.lifecycle.beans.MyBeanFactoryPostProcessor"/>

```
### Know the container lifecycle sequence with respect to XML Parsing, `BeanFactoryPostProcessor`s, `BeanPostProcessor`s, validation, init method, `@PostConstruct` ###
  1. Instantiate context
  1. XML file
  1. Bean Factory Post Processing
  1. Bean Post Processing
  1. validation
  1. init method
  1. `@PostConstruct`
  1. use bean
  1. close context
  1. `@PreDestroy`
  1. destroy-method