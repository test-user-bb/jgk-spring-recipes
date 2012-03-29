package com.jgk.springrecipes.springsecurity.autoconfig.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean public String webatronic() { return "WEB-A-TRONIC"; }
    
}
