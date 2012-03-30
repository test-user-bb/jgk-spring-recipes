package com.jgk.springrecipes.springsecurity.jakub.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jgk.springrecipes.springsecurity.jakub.beans.UserRepositoryImpl;

@Configuration
@ComponentScan(basePackageClasses={ UserRepositoryImpl.class })
public class JakubWebConfig {
    public JakubWebConfig() {
        System.out.println("JakubWebConfig");
    }
}
