package com.jgk.spring31hib4.service;

import com.jgk.webspring31hib4.domain.Foo;

public interface FooService {
    Foo getFoo(String fooName);

    Foo getFoo(String fooName, String barName);

    void insertFoo(Foo foo);

    void updateFoo(Foo foo);
}
