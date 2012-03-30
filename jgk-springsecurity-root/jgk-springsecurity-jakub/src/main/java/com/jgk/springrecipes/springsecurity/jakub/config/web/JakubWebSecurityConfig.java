package com.jgk.springrecipes.springsecurity.jakub.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={ "classpath*:jgk-springsecurity-jakub-web-security-config.xml" })
public class JakubWebSecurityConfig {
    public JakubWebSecurityConfig() {
        System.out.println("JakubWebSecurityConfig");
    }
}
