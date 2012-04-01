package com.jgk.springrecipes.springsecurity.stateless.restful.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jgk.springrecipes.springsecurity.stateless.restful.beans.MyRestAuthenticationEntryPoint;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={ MyRestAuthenticationEntryPoint.class })
public class StatelessRestfulWebConfig {
    public StatelessRestfulWebConfig() {
        System.out.println("StatelessRestfulWebConfig");
    }
}
