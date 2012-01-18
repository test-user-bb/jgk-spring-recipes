package com.jgk.spring31hib4.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component(value = "testAspect")
public class TestAspect {

    @Around(value = "execution(* com.jgk.spring31hib4.*.test*(..))")
    public Object aroundOpen(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = pjp.proceed();
        System.out.println("TestAspect HELLO THERE");
        System.out.println(pjp.getSignature());
        return retVal;
    }

    @Before(value = "execution(* com.jgk.spring31hib4.*.test*(..))")
    public void beforeSet(JoinPoint jp) {
        System.out.println("TestAspect BEFORE "+jp.getSignature());
    }

    // @Around(value = "execution(* org.hibernate.*.open*(..))")
    // public Object aroundOpen(ProceedingJoinPoint pjp) throws Throwable {
    // Object retVal = pjp.proceed();
    //
    // return retVal;
    // }
    // @Before(value = "execution(* org.hibernate.*.open*(..))")
    // public void saveSomething(JoinPoint jp) {
    // String msg = "SAVE ANOTHER " + jp.getSignature().getName();
    // System.out.println(msg);
    // }

}
