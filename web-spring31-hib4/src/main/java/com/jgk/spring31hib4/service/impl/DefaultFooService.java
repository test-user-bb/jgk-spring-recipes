package com.jgk.spring31hib4.service.impl;

import com.jgk.spring31hib4.service.FooService;
import com.jgk.webspring31hib4.domain.Foo;

public class DefaultFooService implements FooService {
    public Foo getFoo(String fooName) {
        System.out.println("GETTING "+fooName);
//        throw new UnsupportedOperationException();
        return Foo.create();
      }

      public Foo getFoo(String fooName, String barName) {
        throw new UnsupportedOperationException();
      }

      public void insertFoo(Foo foo) {
        throw new UnsupportedOperationException();
      }

      public void updateFoo(Foo foo) {
        throw new UnsupportedOperationException();
      }
}
