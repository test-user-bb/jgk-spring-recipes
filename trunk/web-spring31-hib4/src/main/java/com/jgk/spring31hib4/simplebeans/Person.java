package com.jgk.spring31hib4.simplebeans;

import org.springframework.stereotype.Component;

//@Component
public class Person {
    private String firstName,lastName;
    protected Person() {}
    public static Person create() { return new Person();}
    
}
