package com.jgk.spring31hib4.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.DefaultNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class SessionFactoriesConfig {
    protected static Logger log = Logger.getLogger(SessionFactoriesConfig.class);
    @PostConstruct public void init() {
    }
    
    private @Value("${firstdb.connection.driver_class}") String firstdbDriverClassName;
    private @Value("${firstdb.connection.url}") String firstdbUrl;
    private @Value("${firstdb.connection.username}") String firstdbUsername;
    private @Value("${firstdb.connection.password}") String firstdbPassword;
    private @Value("${firstdb.connection.validationQuery}") String firstdbValidationQuery;
    private @Value("${firstdb.dialect}") String firstdbDialect;
    private @Value("${hcpcs.connection.driver_class}") String hcpcsDriverClassName;
    private @Value("${hcpcs.connection.url}") String hcpcsUrl;
    private @Value("${hcpcs.connection.username}") String hcpcsUsername;
    private @Value("${hcpcs.connection.password}") String hcpcsPassword;
    private @Value("${hcpcs.connection.validationQuery}") String hcpcsValidationQuery;
    private @Value("${hcpcs.dialect}") String hcpcsDialect;
    
    @Bean SessionFactory hcpcsSessionFactory() {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(hcpcsDataSource());
        lsfb.setMappingResources(new String[] {
           "mappings/hcpcs/HcpcsCategory.hbm.xml"     
        });
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", hcpcsDialect);
        lsfb.setHibernateProperties(hibernateProperties);
        lsfb.setNamingStrategy(new DefaultNamingStrategy());
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SessionFactory sf = lsfb.getObject();
        System.out.println("SessionFactory: " + sf);
        return sf;
    }
    @Bean SessionFactory firstdbSessionFactory() {
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(firstdbDataSource());
        lsfb.setMappingResources(new String[] {
           "mappings/firstdb/Radimef0.hbm.xml"     
        });
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", firstdbDialect);
        lsfb.setHibernateProperties(hibernateProperties);
        lsfb.setNamingStrategy(new DefaultNamingStrategy());
        try {
            lsfb.afterPropertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SessionFactory sf = lsfb.getObject();
        System.out.println("SessionFactory: " + sf);
        return sf;
    }
    
    @Bean
    public DataSource firstdbDataSource() {
        return getDataSource(firstdbUsername,firstdbPassword,firstdbUrl,firstdbDriverClassName,firstdbValidationQuery);
//                env.getProperty("firstdb.connection.username"),
//                env.getProperty("firstdb.connection.password"),
//                env.getProperty("firstdb.connection.url"),
//                env.getProperty("firstdb.connection.driver_class"),
//                env.getProperty("firstdb.connection.validationQuery"));

    }
    @Bean
    public DataSource hcpcsDataSource() {
        return getDataSource(hcpcsUsername,hcpcsPassword,hcpcsUrl,hcpcsDriverClassName,hcpcsValidationQuery);
//                env.getProperty("hcpcs.connection.username"),
//                env.getProperty("hcpcs.connection.password"),
//                env.getProperty("hcpcs.connection.url"),
//                env.getProperty("hcpcs.connection.driver_class"),
//                env.getProperty("hcpcs.connection.validationQuery"));

    }

    private DataSource getDataSource(String username, String password,
            String url, String driverClassName, String validationQuery) {
//        return getBasicDataSource(username, password, url, driverClassName, validationQuery);
        return getC3P0DataSource(username, password, url, driverClassName, validationQuery);
    }
    private DataSource getC3P0DataSource(String username, String password, String url, String driverClassName, String validationQuery) {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverClassName);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setUser(username);
        dataSource.setInitialPoolSize(1);
        dataSource.setJdbcUrl(url);
        dataSource.setPassword(password);
        dataSource.setMaxPoolSize(4);
        dataSource.setMinPoolSize(1);
        if(validationQuery!=null) {
            dataSource.setPreferredTestQuery(validationQuery);
            dataSource.setTestConnectionOnCheckin(true);
            Connection conn = null;
            try {
                if (log.isInfoEnabled()) {
                    log.info(String.format("conn dataSource:%s:%s:[%s]:%s:%s",
                            dataSource.getUser(), dataSource.getPassword(),
                            dataSource.getJdbcUrl(),
                            dataSource.getDriverClass(),validationQuery)
                            );
                }
                conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(validationQuery);
                while (rs.next()) {
                    
                    System.out.println(rs.getMetaData().getColumnLabel(1)+": "+rs.getInt(1));
                }
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
    private DataSource getBasicDataSource(String username, String password,
            String url, String driverClassName, String validationQuery) {
        BasicDataSource dataSource = new BasicDataSource();
        if (username != null)
            dataSource.setUsername(username);
        if (password != null)
            dataSource.setPassword(password);
        if (driverClassName != null)
            dataSource.setDriverClassName(driverClassName);
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
