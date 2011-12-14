package com.jgk.spring31hib4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.spring31hib4.domain.Jed;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test-config.xml"})
public class BasicSpringTest {
    
    @Autowired @Qualifier(value="dbcpDataSource") DataSource ds;
    @Autowired ApplicationContext applicationContext;
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired SessionFactory sessionFactory;

    @Test public void testing() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Jed jed = new Jed();
        jed.setFirstName("WILMA");
        jed.setLastName("FLINTSTONE");
        Calendar cal = Calendar.getInstance();
        cal.set(1980, Calendar.JANUARY, 32);
        Date birthdate = cal.getTime();
        jed.setBirthdate(birthdate);
        session.save(jed);
        tx.commit();
    }
    @Test public void testJdbcTemplate() {
        int count = jdbcTemplate.queryForInt("SELECT count(0) from user_tables");
        System.out.println("No. of user_tables: " + count);
        
    }
    
    @Ignore
    @Test public void testConnection() {
        try {
            Connection conn = ds.getConnection();
            System.out.println("conn: "+conn);
            Statement stmt = conn.createStatement();
            boolean res = stmt.execute("SELECT count(*) c from user_tables");
            System.out.println("res: " + res);
            ResultSet rs = stmt.getResultSet();
            if(rs.next()) {
                System.out.println("FREAKY: " + rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
