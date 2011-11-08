package com.jgk.fdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;

import org.hibernate.ejb.metamodel.EntityTypeImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.fdb.domain.Radimmo5;
import com.jgk.fdb.domain.Radimmo5PK;
import com.jgk.fdb.repository.Radimmo5Repository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/test-playtime-spring-beans-jpa-config.xml")
public class PlaytimeFdbTest {
    private Random rand;
    @Inject protected ApplicationContext applicationContext;
    @Inject protected Radimmo5Repository radimmo5Repository;
    @PersistenceContext protected EntityManager entityManager;
    @Test @Rollback(value=false)
    public void testing() {
        persistSomeData(300);
        List<Radimmo5> list = radimmo5Repository.findAll();
//        for (Radimmo5 radimmo5 : list) {
//            System.out.println(radimmo5);
//        }
        Pageable page = new PageRequest(4, 5);
        Page<Radimmo5> p = radimmo5Repository.findAll(page);
        for (Radimmo5 radimmo5 : p.getContent()) {
            System.out.println(radimmo5);
        }
        
        assertTrue(true);
        assertEquals(1, 1);
        assertNotNull(new Object());
        assertNull(null);
    }
    
    private void persistSomeData(int num) {
        for (int i = 0; i < num; i++) {
            Radimmo5PK pk = new Radimmo5PK(rand.nextInt()*rand.nextInt()+rand.nextInt(), rand.nextInt());
            Radimmo5 en = new Radimmo5(pk);
            radimmo5Repository.save(en);
            
        }
    }

    @Before public void onSetup() {
        rand = new Random( 19580427 );

        showBeans();
        showEntityNames();
    }
    public void showBeans() {
        System.out.println("BEAN NAMES:");
        for (String bn : applicationContext.getBeanDefinitionNames()) {
            System.out.println(bn);
        }
        System.out.println("JED TIME");
    }
    
    @SuppressWarnings("unused")
    private void showEntityNames() {
        Set<EntityType<?>> set = entityManager.getMetamodel().getEntities();
        List<String> entityNames = new ArrayList<String>();
//        System.out.println("Number of Entities: " + set.size());
        for (EntityType<?> entity : set) {
            @SuppressWarnings("rawtypes")
            EntityTypeImpl e = (EntityTypeImpl) entity;
//            System.out.println(e.getName());
            entityNames.add(e.getName());
        }
        Collections.sort(entityNames);
        int idx=0;
        System.out.println("-------------------------------------------------------------------");
        for (String entityName : entityNames) {
            idx++;
//            System.out.printf("%d|%s\n",idx,entityName.substring("com.gs.firstdb.".length()));
            System.out.printf("%s\n",entityName.substring(entityName.lastIndexOf(".")+1));
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.printf("No. entities: %d\n", entityNames.size());

    }
    
}
