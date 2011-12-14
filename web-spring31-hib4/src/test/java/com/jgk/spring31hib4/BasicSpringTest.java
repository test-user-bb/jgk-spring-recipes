package com.jgk.spring31hib4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateUtils;
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
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.jgk.spring31hib4.dao.ClampettDao;
import com.jgk.spring31hib4.domain.Granny;
import com.jgk.spring31hib4.domain.Jed;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-test-config.xml"})
public class BasicSpringTest {
    
    @Autowired @Qualifier(value="dbcpDataSource") DataSource ds;
    @Autowired ApplicationContext applicationContext;
    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired SessionFactory sessionFactory;
    @Autowired PlatformTransactionManager transactionManager;
    @Autowired ClampettDao clampettDao;

    @BeforeTransaction public void beforeTransaction() {
        System.out.println("beforeTransaction");
    }
    @AfterTransaction public void afterTransaction() {
        System.out.println("afterTransaction");
    }
    @Transactional(value="web-spring31-hib4.TransactionManager")
    @Test public void testingOther() {
        System.out.println(transactionManager);
        Jed jed = Jed.create("Jed","Clampett",mkdate("19550302"));
        jed.addGranny(Granny.createGranny("Franny", "Clinker", mkdate("19230723")));
        jed.addGranny(Granny.createGranny("Sally", "Sampson", mkdate("19311112")));
        System.out.println(jed);
        clampettDao.save(jed);
        System.out.println(jed);
        Jed retrievedJed = clampettDao.getJedById(jed.getPersonId());
        Assert.assertNotNull(retrievedJed);
    }
    private Date mkdate(String yyyymmdd) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date= df.parse(yyyymmdd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    @Ignore
    @Test public void testingTx() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Jed jed = new Jed();
        jed.setFirstName("WILMA");
        jed.setLastName("FLINTSTONE");
        Calendar cal = Calendar.getInstance();
        cal.set(1980, Calendar.JANUARY, 32);
        Date birthdate = cal.getTime();
        jed.setBirthdate(birthdate);
        Granny granny = Granny.createGranny("Granny","Clampett",DateUtils.addYears(birthdate, -23));
        jed.addGranny(granny);
        session.save(jed);
        tx.commit();
    }
    
    @Ignore
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
