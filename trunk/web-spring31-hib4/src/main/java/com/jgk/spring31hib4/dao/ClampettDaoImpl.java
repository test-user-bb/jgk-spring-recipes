package com.jgk.spring31hib4.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jgk.spring31hib4.domains.clampett.Granny;
import com.jgk.spring31hib4.domains.clampett.Jed;

@Component
public class ClampettDaoImpl implements ClampettDao {
    protected transient final Log log = LogFactory.getLog(ClampettDaoImpl.class);
            
    @Autowired SessionFactory sessionFactory;
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    public Granny getGrannyById(Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("loading by id: " + id);
        }
        return (Granny) getCurrentSession().get(Granny.class, id);
    }
    public Jed getJedById(Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("loading by id: " + id);
        }
        return (Jed) getCurrentSession().get(Jed.class, id);
    }
    @Transactional(value="web-spring31-hib4.TransactionManager1")
    @Override
    public void save(Object entity) {
        getCurrentSession().saveOrUpdate(entity);
        
    }

}
