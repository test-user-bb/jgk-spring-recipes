package com.jgk.springrecipes.springsecurity.autoconfig.formbased.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={ "classpath*:jgk-springsecurity-autoconfig-formbased-web-security-config.xml"})
public class FormbasedWebSecurityConfig {
    public FormbasedWebSecurityConfig() {
        System.out.println("FormbasedWebSecurityConfig");
    }
}
