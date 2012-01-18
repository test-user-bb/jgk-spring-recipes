package com.jgk.spring31hib4.util;

import org.junit.Assert;
import org.junit.Test;


public class DefaultUsageTrackedTest {

    @Test public void testing() {
        UsageTracked u = new DefaultUsageTracked();
        Assert.assertNotNull(u);
//        System.out.println(u);
    }
}
