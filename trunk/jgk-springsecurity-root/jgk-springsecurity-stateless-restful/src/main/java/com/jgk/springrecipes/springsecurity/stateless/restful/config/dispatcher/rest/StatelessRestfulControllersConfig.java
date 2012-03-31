package com.jgk.springrecipes.springsecurity.stateless.restful.config.dispatcher.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jgk.springrecipes.springsecurity.stateless.restful.controller.StatelessRestfulApiController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { StatelessRestfulApiController.class })
public class StatelessRestfulControllersConfig {
    public StatelessRestfulControllersConfig() {
        super();
        System.out.println("StatelessRestfulControllersConfig");
    }
}
