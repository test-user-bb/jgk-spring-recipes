package com.jgk.springrecipes.springsecurity.autoconfig.passwordencoded.config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jgk.springrecipes.springsecurity.autoconfig.passwordencoded.beans.MyNonEncodingPasswordEncoder;

@Configuration
@ComponentScan(basePackageClasses = { MyNonEncodingPasswordEncoder.class })
public class PasswordEncodedWebConfig {
    public PasswordEncodedWebConfig() {
        System.out.println("PasswordEncodedWebConfig");
    }
}
