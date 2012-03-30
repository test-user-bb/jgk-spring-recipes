package com.jgk.springrecipes.springsecurity.customfilter.first.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jgk.springrecipes.springsecurity.customfilter.first.beans.MyUsernamePasswordAuthenticationFilter;

@Configuration
@ComponentScan(basePackageClasses={ MyUsernamePasswordAuthenticationFilter.class })
public class CustomFilterFirstWebConfig {
    public CustomFilterFirstWebConfig() {
        System.out.println("CustomFilterFirstWebConfig");
    }
}
