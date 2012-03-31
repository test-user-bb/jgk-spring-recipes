package com.jgk.springrecipes.springsecurity.stateless.restful.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.jgk.springrecipes.springsecurity.stateless.restful.controller.StatelessRestfulApiController;

@Configuration
@ImportResource(value={ "classpath*:jgk-springsecurity-stateless-restful-web-security-config.xml" })
//@ComponentScan(basePackageClasses={ StatelessRestfulController.class })
public class StatelessRestfulWebSecurityConfig {
    public StatelessRestfulWebSecurityConfig() {
        System.out.println("StatelessRestfulWebSecurityConfig");
    }
}
