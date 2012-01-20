package com.jgk.spring31hib4.research;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:research/my-spring-research-transaction-config-test.xml"})
public class MySpringResearchTransactionIntegrationTest {
    @Rule public TestName name = new TestName();
    @Autowired ApplicationContext applicationContext;
    @Autowired DataSource dataSource;
    @Autowired SessionFactory sessionFactory;
    @Autowired EntityManagerFactory emf;
    @Test public void testDataSource() {
        Connection conn = DataSourceUtils.getConnection(dataSource);
        System.out.println("Connection: " + conn);
    }
    @Test public void testSessionFactory() {
        DataSource ds = SessionFactoryUtils.getDataSource(sessionFactory);
        try {
            System.out.println(name.getMethodName() + ": " + ds.getConnection());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    @Transactional(value="myPlatformTransactionManager")
    @Rollback(value=true)
    @Test public void simpleWithRollbackTrue() {
        System.out.println(name.getMethodName());
    }
    @Transactional(value="myPlatformTransactionManager")
    @Rollback(value=false)
    @Test public void simpleWithRollbackFalse() {
        System.out.println(name.getMethodName());
    }
    
    @Before public void before() {
    }
    
    @Test public void showBeans() {
        System.out.println(name.getMethodName());
        List<String> names = new ArrayList<String>();
        names.addAll(Arrays.asList(applicationContext.getBeanDefinitionNames()));
        Collections.sort(names);
        System.out.println(names);
    }
}
