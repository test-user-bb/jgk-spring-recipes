package com.jgk.spring31hib4.util;

public class DefaultUsageTracked implements UsageTracked {
    private int useCount;
    @Override
    public void incrementUseCount() {
        useCount++;
        System.out.println("Incremented use count: " + useCount);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DefaultUsageTracked [useCount=");
        builder.append(useCount);
        builder.append("]");
        return builder.toString();
    }
    
}
