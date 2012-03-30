package com.jgk.springrecipes.springsecurity.customfilter.first.beans;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("myLoginUrlAuthenticationEntryPoint")
public class MyLoginUrlAuthenticationEntryPoint extends
        LoginUrlAuthenticationEntryPoint {

    public MyLoginUrlAuthenticationEntryPoint() {
        super("/j_spring_security_check");
    }

    @Override
    public String getLoginFormUrl() {
        System.out.println("MyLoginUrlAuthenticationEntryPoint: getLoginFormUrl");
        return super.getLoginFormUrl();
    }    

}
