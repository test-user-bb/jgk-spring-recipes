package com.jgk.spring31hib4.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

import com.jgk.spring31hib4.util.DefaultUsageTracked;
import com.jgk.spring31hib4.util.UsageTracked;

@Component(value="FREAKIN-USAGE-TRACKING")
@Aspect
public class UsageTracking {
    @DeclareParents(value = "com.jgk.spring31hib4.dao.*+", defaultImpl = DefaultUsageTracked.class)
    public static UsageTracked mixin;

    @Before("com.jgk.spring31hib4.aspects.SystemArchitecture.inDataAccessLayer() &&"
            + "this(usageTracked)")
    public void recordUsage(UsageTracked usageTracked) {
        System.out.println("RECORD USAGE "+this);
        usageTracked.incrementUseCount();
    }
}
