package com.jgk.spring31hib4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CondensateSubsystemConfig {
    @Bean public String condensateSubsystemName() {
        return "Condensate";
    }

}
