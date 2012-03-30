package com.jgk.springrecipes.springsecurity.controller.config.dispatcher.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jgk.springrecipes.springsecurity.controller.controllers.MyRestController;

@Configuration
@ComponentScan(basePackageClasses={ MyRestController.class})
public class ControllersConfig {

}
