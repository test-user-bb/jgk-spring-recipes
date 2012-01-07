package com.jgk.spring31hib4.servlets;

import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;

import com.jgk.spring31hib4.dao.ClampettDao;
import com.jgk.spring31hib4.domains.clampett.Granny;
import com.jgk.spring31hib4.domains.clampett.Jed;

/**
 * Servlet implementation class StartupServlet
 */
public class StartupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected transient final Log log = LogFactory.getLog(StartupServlet.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartupServlet() {
        super();
    }

    private void showBeans() {
        WebApplicationContext wac = (WebApplicationContext) getServletContext()
        .getAttribute(
                WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        int count=0;
        for(String beanName : wac.getBeanDefinitionNames()) {
            count++;
            System.out.printf("%d. %s\n", count, beanName);
        }
    }
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Author: " + getInitParameter("author"));
        WebApplicationContext wac = (WebApplicationContext) getServletContext()
                .getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        showBeans();
        ClampettDao clampettDao = wac.getBean(ClampettDao.class);
        Jed jed = Jed.create("BEN", String.format("Thom-%d", System.currentTimeMillis()), new Date());
        startSession();
        clampettDao.save(jed);
        System.out.println(jed.getPersonId());
        jed.addGranny(Granny.createGranny("FIRST", "GRAN", new Date()));
        jed.addGranny(Granny.createGranny("SECOND", "GRAN", new Date()));
        stopSession();
        startSession();
        clampettDao.save(jed);
        stopSession();

    }

    protected void stopSession() {
        WebApplicationContext wac = (WebApplicationContext) getServletContext()
                .getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        SessionFactory sessionFactory = wac.getBean(
                "web-spring31-hib4.sessionFactory1", SessionFactory.class);
        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager
                .unbindResource(sessionFactory);
        log.debug("Closing Hibernate Session");
        SessionFactoryUtils.closeSession(sessionHolder.getSession());
    }

    protected void startSession() {
        WebApplicationContext wac = (WebApplicationContext) getServletContext()
                .getAttribute(
                        WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        SessionFactory sessionFactory = wac.getBean(
                "web-spring31-hib4.sessionFactory1", SessionFactory.class);
        Session session = openSession(sessionFactory);
        TransactionSynchronizationManager.bindResource(sessionFactory,
                new SessionHolder(session));

    }

    protected Session openSession(SessionFactory sessionFactory)
            throws DataAccessResourceFailureException {
        try {
            Session session = SessionFactoryUtils.openSession(sessionFactory);
            session.setFlushMode(FlushMode.MANUAL);
            return session;
        } catch (HibernateException ex) {
            throw new DataAccessResourceFailureException(
                    "Could not open Hibernate Session", ex);
        }
    }

}
