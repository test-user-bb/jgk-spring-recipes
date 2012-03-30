package com.jgk.springrecipes.springsecurity.stateless.restful.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={ "classpath*:jgk-springsecurity-stateless-restful-web-security-config.xml" })
public class StatelessRestfulWebSecurityConfig {
    public StatelessRestfulWebSecurityConfig() {
        System.out.println("StatelessRestfulWebSecurityConfig");
    }
}
