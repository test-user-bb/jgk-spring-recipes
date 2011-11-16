package com.javapda.formarten;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javapda.formarten.dao.FootballPlayerDao;
import com.javapda.formarten.dao.SportsService;
import com.javapda.formarten.domain.FootballPlayer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:EntityManagerFactory-derived-SessionFactory-withoutusing-current_session_context_class-test-spring-main-config.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EntityManagerFactoryDerivedSessionFactoryWithoutUsingHibernate_current_session_context_classPropertyTest {
    @Inject ApplicationContext applicationContext;
    @Inject FootballPlayerDao footballPlayerDao;
    @Inject SportsService sportsService;
    
    @Transactional(propagation=Propagation.REQUIRED)
    @Test public void testing() {
        FootballPlayer[] footballPlayers={ 
                new FootballPlayer(12,"Joe Namath"),
                new FootballPlayer(32,"Jed Clampett"),
        };
        int idx = 0;
        for (FootballPlayer footballPlayer : footballPlayers) {
            footballPlayers[idx++]=sportsService.saveFootballPlayer(footballPlayer);
            System.out.println("FootballPlayerId: " + footballPlayer.getFootballPlayerId());
        }
//        System.out.println(sportsService.getAllFootballPlayers());
        assertEquals(footballPlayers.length, sportsService.getAllFootballPlayers().size());
        for (FootballPlayer footballPlayer : footballPlayers) {
//            System.out.println("DELETEING PLAYER:  "+footballPlayer);
            sportsService.deleteFootballPlayer(footballPlayer);
        }
        assertEquals(0, sportsService.getAllFootballPlayers().size());
    }
    @Before
    public void showBeanNames() {
      for( String beanName : applicationContext.getBeanDefinitionNames()) {
          System.out.println(beanName);
      }
      System.out.println("THE-sessionFactory: " + applicationContext.getBean("sessionFactory"));
  }
    
}
