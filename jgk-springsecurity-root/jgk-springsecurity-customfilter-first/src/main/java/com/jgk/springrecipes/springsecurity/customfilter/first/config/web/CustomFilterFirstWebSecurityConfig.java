package com.jgk.springrecipes.springsecurity.customfilter.first.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = { "classpath*:jgk-springsecurity-customfilter-first-web-security-config.xml" })
public class CustomFilterFirstWebSecurityConfig {
    public CustomFilterFirstWebSecurityConfig() {
        System.out.println("CustomFilterFirstWebSecurityConfig");
    }
}
