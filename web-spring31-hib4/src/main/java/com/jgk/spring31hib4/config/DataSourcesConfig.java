package com.jgk.spring31hib4.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = {
        "classpath:config/datasources/datasource.firstdb.properties",
        "classpath:config/datasources/datasource.hcpcs.properties" })
@ComponentScan(basePackages = { "com.jgk.spring31hib4.simplebeans" })
@Import(value = { AuxSubsystemConfig.class, CondensateSubsystemConfig.class })
public class DataSourcesConfig {
    protected static Logger log = Logger.getLogger(DataSourcesConfig.class);
    @Autowired
    Environment env;

    @Bean
    public DataSource firstdbDataSource() {
        return getDataSource(
                env.getProperty("firstdb.connection.username"),
                env.getProperty("firstdb.connection.password"),
                env.getProperty("firstdb.connection.url"),
                env.getProperty("firstdb.connection.driver_class"),
                env.getProperty("firstdb.connection.validationQuery"));

    }
    @Bean
    public DataSource hcpcsDataSource() {
        return getDataSource(
                env.getProperty("hcpcs.connection.username"),
                env.getProperty("hcpcs.connection.password"),
                env.getProperty("hcpcs.connection.url"),
                env.getProperty("hcpcs.connection.driver_class"),
                env.getProperty("hcpcs.connection.validationQuery"));

    }

    private DataSource getDataSource(String username, String password,
            String url, String driverClass, String validationQuery) {
        BasicDataSource dataSource = new BasicDataSource();
        if (username != null)
            dataSource.setUsername(username);
        if (password != null)
            dataSource.setPassword(password);
        if (driverClass != null)
            dataSource.setDriverClassName(driverClass);
        if (url != null)
            dataSource.setUrl(url);
        if (validationQuery != null) {
            dataSource.setValidationQuery(validationQuery);
            Connection conn = null;
            try {
                if (log.isInfoEnabled()) {
                    log.info(String.format("conn dataSource:%s:%s:[%s]:%s",
                            dataSource.getUsername(), dataSource.getPassword(),
                            dataSource.getUrl(),
                            dataSource.getDriverClassName()));
                }
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        if (log.isInfoEnabled()) {
                            log.info("closing conn:" + conn);
                        }
                        conn.close();

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataSource;

    }
}
