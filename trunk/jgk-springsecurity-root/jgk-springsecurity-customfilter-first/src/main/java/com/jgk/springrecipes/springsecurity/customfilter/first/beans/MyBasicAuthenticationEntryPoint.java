package com.jgk.springrecipes.springsecurity.customfilter.first.beans;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("myBasicAuthenticationEntryPoint")
public class MyBasicAuthenticationEntryPoint extends
        BasicAuthenticationEntryPoint {

    public MyBasicAuthenticationEntryPoint() {
        super();
        setRealmName("fredRealm");
    }
    
}
