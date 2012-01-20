package com.jgk.spring31hib4.research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.spring31hib4.config.MainConfig;
import com.jgk.spring31hib4.navy.ships.submarine.Submarine;
import com.jgk.spring31hib4.simplebeans.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:research/my-spring-research-configuration-config-test.xml"})
public class MySpringResearchConfigurationIntegrationTest {
    @Rule public TestName name = new TestName();
    @Autowired ApplicationContext applicationContext;

    
    @Before public void before() {
    }
    
    @Test public void showBeans() {
        System.out.println(name.getMethodName());
        List<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(applicationContext.getBeanDefinitionNames()));
        Collections.sort(names);
        System.out.println(names);
    }
    
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfig.class);
        List<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(ctx.getBeanDefinitionNames()));
        Collections.sort(names);
        System.out.println(names);
        System.out.println("LATERE");
        System.out.println("ctx.getBean(Person.class): " + ctx.getBean(Person.class));
        System.out.println(ctx.getBean(Submarine.class));
        System.out.println(ctx.getBean(Submarine.class));
        System.out.println(ctx.getBean(Submarine.class));
        System.out.println(ctx.getBean(Submarine.class));
        ctx.getBean(Submarine.class);
        
    }
}
