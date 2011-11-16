package com.javapda.formarten.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value="myExquisiteDataSourceConfig")
public class MyDataSourceConfig {
    @Value("#{myProps['hibernate.connection.url']}") String url;
    @Value("#{myProps['hibernate.connection.driver_class']}") String driverClassName;
    @Value("#{myProps['hibernate.dialect']}") String dialect;
    @Value("#{myProps['hibernate.connection.username']}") String username;
    @Value("#{myProps['hibernate.connection.password']}") String password;
    @Bean(name={"footballDataSource"}) DataSource dataSource() {
        StringBuilder sb = new StringBuilder();
        sb.append("PROPERTIES OF DATASOURCE");
        sb.append("url=").append(url).append("\n");
        sb.append("driverClassName=").append(driverClassName).append("\n");
        sb.append("username=").append(username).append("\n");
        sb.append("password=").append(password).append("\n");
        sb.append("url=").append(url).append("\n");
        System.out.println(sb);
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setMaxActive(-1);
        return ds;
    }
    
}
