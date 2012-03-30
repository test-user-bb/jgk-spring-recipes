package com.jgk.springrecipes.springsecurity.controller.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jgk.springrecipes.springsecurity.controller.controllers.MyRestController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses={MyRestController.class})
public class ControllerWebConfig {
    public ControllerWebConfig() {
        System.out.println("ControllerWebConfig");
    }
}
