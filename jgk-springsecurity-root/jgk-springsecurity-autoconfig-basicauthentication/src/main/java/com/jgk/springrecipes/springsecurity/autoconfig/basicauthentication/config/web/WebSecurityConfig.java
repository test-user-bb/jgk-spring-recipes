package com.jgk.springrecipes.springsecurity.autoconfig.basicauthentication.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value={ "classpath*:jgk-springsecurity-autoconfig-basicauthentication-web-security-config.xml" })
public class WebSecurityConfig {
    public WebSecurityConfig() {
        System.out.println("WEBSECURITYCONFIG-com.jgk.springrecipes.springsecurity.autoconfig.basicauthentication.config.web");
    }
}
