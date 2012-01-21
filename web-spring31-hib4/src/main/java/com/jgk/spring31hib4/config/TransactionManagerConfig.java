package com.jgk.spring31hib4.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {
    protected static Logger log = Logger.getLogger(TransactionManagerConfig.class);
    @Autowired @Qualifier("firstdbDataSource") DataSource firstdbDataSource;
    @Autowired @Qualifier("hcpcsDataSource") DataSource hcpcsDataSource;
    @Autowired @Qualifier("firstdbSessionFactory") SessionFactory firstdbSessionFactory;
    @Autowired @Qualifier("hcpcsSessionFactory") SessionFactory hcpcsSessionFactory;
    
    @Bean PlatformTransactionManager hcpcsSessionFactoryTransactionManager() {
        return new HibernateTransactionManager(hcpcsSessionFactory);
    }
    @Bean PlatformTransactionManager firstdbSessionFactoryTransactionManager() {
        return new HibernateTransactionManager(firstdbSessionFactory);
    }
    @Bean
    public PlatformTransactionManager firstdbDataSourceTransactionManager() {
        return new DataSourceTransactionManager(firstdbDataSource);
    }
    @Bean
    public PlatformTransactionManager hcpcsDataSourceTransactionManager() {
        return new DataSourceTransactionManager(hcpcsDataSource);
    }
}
