package com.jgk.springrecipes.springsecurity.autoconfig.passwordencoded.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={ "classpath*:jgk-springsecurity-autoconfig-passwordencoded-web-security-config.xml"})
public class PasswordEncodedWebSecurityConfig {
    public PasswordEncodedWebSecurityConfig() {
        System.out.println("PasswordEncodedWebSecurityConfig");
    }
}
