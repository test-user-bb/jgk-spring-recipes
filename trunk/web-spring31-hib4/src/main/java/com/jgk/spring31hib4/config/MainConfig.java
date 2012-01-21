package com.jgk.spring31hib4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.jgk.spring31hib4.navy.ships.submarine.Submarine;
import com.jgk.spring31hib4.simplebeans.Person;

@Configuration
@ComponentScan(basePackages={"com.jgk.spring31hib4.simplebeans"})
@Import(value={SessionFactoriesConfig.class, TransactionManagerConfig.class, DataSourcesPropertiesConfig.class, DataSourcesConfig.class, AuxSubsystemConfig.class, CondensateSubsystemConfig.class})
public class MainConfig {
    @Autowired @Qualifier(value="auxSubsystemName") String auxSubsystem;
    @Autowired @Qualifier(value="condensateSubsystemName") String condensateSubsystem;
    
    @Bean public Person freakPerson() {
        return Person.create();
    }
    @Scope("prototype")
    @Bean(initMethod="init") public Submarine submarine() {
        return new Submarine(auxSubsystem,condensateSubsystem);
    }
    
}
