package com.javapda.formarten.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.javapda.formarten.domain.FootballPlayer;


@Repository
public class FootballPlayerHibernateDao implements FootballPlayerDao {
    
    @Inject ApplicationContext applicationContext;

    @Inject private SessionFactory sessionFactory;
    protected Session getCurrentSession()  {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    protected static final Log log = LogFactory
            .getLog(FootballPlayerHibernateDao.class);

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        log.info("Setting sessionFactory: " + sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    public FootballPlayer save(FootballPlayer player) {
//        sessionFactory=applicationContext.getBean(SessionFactory.class);
        return (FootballPlayer) getCurrentSession().merge(player);
    }

    @SuppressWarnings("unchecked")
    public List<FootballPlayer> getAllFootballPlayers() {
        return getCurrentSession().createCriteria(FootballPlayer.class).list();
    }

    public void delete(FootballPlayer footballPlayer) {
        getCurrentSession().delete(footballPlayer);
//        getCurrentSession().flush();
        
    }
}
