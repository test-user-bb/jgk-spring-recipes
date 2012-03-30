package com.jgk.springrecipes.springsecurity.stateless.restful.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class StatelessRestfulWebConfig {
    public StatelessRestfulWebConfig() {
        System.out.println("StatelessRestfulWebConfig");
    }
}
