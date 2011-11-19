package com.jgk.fdb;

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
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jgk.fdb.repository.JgkFakeUserTypesRepository;
import com.jgk.fdb.repository.Radimmo5Repository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/test-playtime-spring-beans-jpa-config.xml")
public abstract class AbstractPlaytimeTest {
    protected Random rand;
    @Inject protected ApplicationContext applicationContext;
    @Inject protected Radimmo5Repository radimmo5Repository;
    @Inject protected JgkFakeUserTypesRepository jgkFakeUserTypesRepository;
    @PersistenceContext protected EntityManager entityManager;
    
    @Before public void onSetup() {
        rand = new Random( 19580427 );

        showBeans();
        showEntityNames();
    }
    protected void showBeans() {
        System.out.println("BEAN NAMES:");
        for (String bn : applicationContext.getBeanDefinitionNames()) {
            System.out.println(bn);
        }
    }
    
    @SuppressWarnings("unused")
    protected void showEntityNames() {
        Set<EntityType<?>> set = entityManager.getMetamodel().getEntities();
        List<String> entityNames = new ArrayList<String>();
//        System.out.println("Number of Entities: " + set.size());
        for (EntityType<?> entity : set) {
            @SuppressWarnings("rawtypes")
            EntityTypeImpl e = (EntityTypeImpl) entity;
//            System.out.println(e.getName());
            if(e.getName()==null){
                System.out.println("WHY ENTITY NAME NULL ");
            }
            entityNames.add(e.getName()!=null?e.getName():"NO-NAME-FOR-THIS-ENTITY");
        }
//        Collections.sort(entityNames);
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
