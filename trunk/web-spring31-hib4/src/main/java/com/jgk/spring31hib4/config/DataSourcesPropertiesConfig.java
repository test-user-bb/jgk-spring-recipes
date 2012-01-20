package com.jgk.spring31hib4.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class DataSourcesPropertiesConfig {
    protected static Logger log = Logger.getLogger(DataSourcesPropertiesConfig.class);
    static @Bean public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[] {
                new ClassPathResource("config/datasources/datasource.firstdb.properties"),
                new ClassPathResource("config/datasources/datasource.hcpcs.properties"),
        };
        p.setLocations(resourceLocations);
        return p;
    }
}
