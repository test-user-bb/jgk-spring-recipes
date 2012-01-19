package com.jgk.spring31hib4.util;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class MyTestExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        System.out.println("beforeTestClass:"+testContext);

    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        System.out.println("prepareTestInstance:"+testContext);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        System.out.println("beforeTestMethod:"+testContext);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        System.out.println("afterTestMethod:"+testContext);
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        System.out.println("afterTestClass:"+testContext);
    }

}
