package com.jgk.springrecipes.springsecurity.openid.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.jgk.springrecipes.springsecurity.openid.beans.OpenidRegisteringUserService;

@Configuration
@ComponentScan(basePackageClasses={OpenidRegisteringUserService.class})
@ImportResource(value={"classpath*:jgk-springsecurity-openid-web-security-config.xml"})
public class OpenidWebSecurityConfig {
    public OpenidWebSecurityConfig() {
        System.out.println("OpenidWebSecurityConfig");
    }
    
}
