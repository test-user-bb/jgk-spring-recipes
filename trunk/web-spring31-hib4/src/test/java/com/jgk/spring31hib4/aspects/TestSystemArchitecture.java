package com.jgk.spring31hib4.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
@Aspect
public class TestSystemArchitecture {
    /**
     * A join point is in the data access layer if the method is defined
     * in a type in the com.gs.core.services.dao.hibernate package or any sub-package
     * under that.
     * within: http://static.springsource.org/spring/docs/3.1.0.RELEASE/spring-framework-reference/html/aop.html#aop-pointcuts-designators
     * 
     */
    @Pointcut("within(com.jgk.spring31hib4.TransactionSpringTest)")
    public void inDataAccessLayer() {}

    @Pointcut("within(com.jgk.spring31hib4.TransactionSpringTest)")
    public void executingServiceLayer() {}

}
