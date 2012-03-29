package com.jgk.springrecipes.springsecurity.autoconfig.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value= { "classpath*:jgk-springsecurity-autoconfig-web-security-config.xml"})
public class AutoconfigWebSecurityConfig {
    public AutoconfigWebSecurityConfig() { System.out.println("AutoconfigWebSecurityConfig");}
    @Bean public String webSecurity() { return "YES, WE HAVE AutoconfigWebSecurityConfig SECURITY"; }

}
