package com.jgk.spring31hib4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jgk.spring31hib4.navy.ships.submarine.Submarine;
import com.jgk.spring31hib4.simplebeans.Person;

@Configuration
@ComponentScan(basePackages={"com.jgk.spring31hib4.simplebeans"})
@Import(value={AuxSubsystemConfig.class, CondensateSubsystemConfig.class, DataSourcesConfig.class})
public class MainConfig {
    @Autowired @Qualifier(value="auxSubsystemName") String auxSubsystem;
    @Autowired @Qualifier(value="condensateSubsystemName") String condensateSubsystem;
    
    @Bean public Person freakPerson() {
        return Person.create();
    }
    @Bean public Submarine submarine() {
        return new Submarine(auxSubsystem,condensateSubsystem);
    }
    
}
