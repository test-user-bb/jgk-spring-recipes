package com.jgk.spring31hib4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.spring31hib4.util.MyTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test-config.xml"})
@TestExecutionListeners(listeners={MyTestExecutionListener.class})
@ActiveProfiles(profiles={"clampetts","flintstones"})
public class VerySimpleSpringTest {

    @Rule public TestName name = new TestName();
    
    @IfProfileValue(name="java.vendor", value="Sun Microsystems Inc.")
    @Test public void simple() {
        System.out.println("TEST "+this.name.getMethodName());
        System.out.println(System.getProperty("java.vendor"));
    }
    
    @IfProfileValue(name="java.vendor", value="Apple Inc.")
    @Test public void appleOnlyTest() {
        System.out.println("TEST "+this.name.getMethodName());
    }
    @IfProfileValue(name="java.vendor", values={"Sun Microsystems Inc.","Apple Inc."})
    @Test public void appleOrSunTest() {
        System.out.println("TEST "+this.name.getMethodName());
    }
    @IfProfileValue(name="user.name", value="jkroub")
    @Test public void jkroubOnlyTest() {
        System.out.println("TEST "+this.name.getMethodName());
    }
    @IfProfileValue(name="user.name", value="jedclampett")
    @Test public void jedclampettOnlyTest() {
        System.out.println("TEST "+this.name.getMethodName());
    }
}
