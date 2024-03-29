package org.springframework.orm.hibernate4;

import java.lang.reflect.Method;
import javax.sql.DataSource;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.ObjectDeletedException;
import org.hibernate.PersistentObjectException;
import org.hibernate.PropertyValueException;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.StaleStateException;
import org.hibernate.TransientObjectException;
import org.hibernate.UnresolvableObjectException;
import org.hibernate.WrongClassException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
 
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
 
/**
 * Helper class featuring methods for Hibernate Session handling.
 * Also provides support for exception translation.
 *
 * <p>Used internally by {@link HibernateTransactionManager}.
 * Can also be used directly in application code.
 *
 * @author Juergen Hoeller
 * @since 3.1
 * @see HibernateExceptionTranslator
 * @see HibernateTransactionManager
 */
public abstract class SessionFactoryUtils {
 
        /**
         * Order value for TransactionSynchronization objects that clean up Hibernate Sessions.
         * Returns <code>DataSourceUtils.CONNECTION_SYNCHRONIZATION_ORDER - 100</code>
         * to execute Session cleanup before JDBC Connection cleanup, if any.
         * @see org.springframework.jdbc.datasource.DataSourceUtils#CONNECTION_SYNCHRONIZATION_ORDER
         */
        public static final int SESSION_SYNCHRONIZATION_ORDER =
                        DataSourceUtils.CONNECTION_SYNCHRONIZATION_ORDER - 100;
 
        /**
         * A Method handle for the <code>SessionFactory.openSession()</code> method.
         * The return value differs between Hibernate 3.x and 4.x; for cross-compilation purposes,
         * we have to use reflection here as long as we keep compiling against Hibernate 3.x jars.
         */
        private static final Method openSessionMethod = ClassUtils.getMethod(SessionFactory.class, "openSession");
 
        static final Log logger = LogFactory.getLog(SessionFactoryUtils.class);
 
 
        /**
         * Determine the DataSource of the given SessionFactory.
         * @param sessionFactory the SessionFactory to check
         * @return the DataSource, or <code>null</code> if none found
         * @see org.hibernate.engine.spi.SessionFactoryImplementor#getConnectionProvider
         */
        public static DataSource getDataSource(SessionFactory sessionFactory) {
                if (sessionFactory instanceof SessionFactoryImplementor) {
                        ConnectionProvider cp = ((SessionFactoryImplementor) sessionFactory).getConnectionProvider();
                        return cp.unwrap(DataSource.class);
                }
                return null;
        }
 
        /**
         * Obtain a new Session from the given SessionFactory.
         * <p>Bridges between Hibernate signature differences.
         * @param sessionFactory the SessionFactory to use
         * @return the new Session
         * @see org.hibernate.SessionFactory#openSession()
         */
        public static Session openSession(SessionFactory sessionFactory) {
                return (Session) ReflectionUtils.invokeMethod(openSessionMethod, sessionFactory);
        }
 
        /**
         * Perform actual closing of the Hibernate Session,
         * catching and logging any cleanup exceptions thrown.
         * @param session the Hibernate Session to close (may be <code>null</code>)
         * @see org.hibernate.Session#close()
         */
        public static void closeSession(Session session) {
                if (session != null) {
                        try {
                                session.close();
                        }
                        catch (HibernateException ex) {
                                logger.debug("Could not close Hibernate Session", ex);
                        }
                        catch (Throwable ex) {
                                logger.debug("Unexpected exception on closing Hibernate Session", ex);
                        }
                }
        }
 
        /**
         * Convert the given HibernateException to an appropriate exception
         * from the <code>org.springframework.dao</code> hierarchy.
         * @param ex HibernateException that occured
         * @return the corresponding DataAccessException instance
         * @see HibernateExceptionTranslator#convertHibernateAccessException
         * @see HibernateTransactionManager#convertHibernateAccessException
         */
        public static DataAccessException convertHibernateAccessException(HibernateException ex) {
                if (ex instanceof JDBCConnectionException) {
                        return new DataAccessResourceFailureException(ex.getMessage(), ex);
                }
                if (ex instanceof SQLGrammarException) {
                        SQLGrammarException jdbcEx = (SQLGrammarException) ex;
                        return new InvalidDataAccessResourceUsageException(ex.getMessage() + "; SQL [" + jdbcEx.getSQL() + "]", ex);
                }
                if (ex instanceof LockAcquisitionException) {
                        LockAcquisitionException jdbcEx = (LockAcquisitionException) ex;
                        return new CannotAcquireLockException(ex.getMessage() + "; SQL [" + jdbcEx.getSQL() + "]", ex);
                }
                if (ex instanceof ConstraintViolationException) {
                        ConstraintViolationException jdbcEx = (ConstraintViolationException) ex;
                        return new DataIntegrityViolationException(ex.getMessage()  + "; SQL [" + jdbcEx.getSQL() +
                                        "]; constraint [" + jdbcEx.getConstraintName() + "]", ex);
                }
                if (ex instanceof DataException) {
                        DataException jdbcEx = (DataException) ex;
                        return new DataIntegrityViolationException(ex.getMessage() + "; SQL [" + jdbcEx.getSQL() + "]", ex);
                }
                if (ex instanceof JDBCException) {
                        return new HibernateJdbcException((JDBCException) ex);
                }
                if (ex instanceof PropertyValueException) {
                        return new DataIntegrityViolationException(ex.getMessage(), ex);
                }
                if (ex instanceof PersistentObjectException) {
                        return new InvalidDataAccessApiUsageException(ex.getMessage(), ex);
                }
                if (ex instanceof TransientObjectException) {
                        return new InvalidDataAccessApiUsageException(ex.getMessage(), ex);
                }
                if (ex instanceof ObjectDeletedException) {
                        return new InvalidDataAccessApiUsageException(ex.getMessage(), ex);
                }
                if (ex instanceof QueryException) {
                        return new HibernateQueryException((QueryException) ex);
                }
                if (ex instanceof UnresolvableObjectException) {
                        return new HibernateObjectRetrievalFailureException((UnresolvableObjectException) ex);
                }
                if (ex instanceof WrongClassException) {
                        return new HibernateObjectRetrievalFailureException((WrongClassException) ex);
                }
                if (ex instanceof NonUniqueResultException) {
                        return new IncorrectResultSizeDataAccessException(ex.getMessage(), 1, ex);
                }
                if (ex instanceof StaleObjectStateException) {
                        return new HibernateOptimisticLockingFailureException((StaleObjectStateException) ex);
                }
                if (ex instanceof StaleStateException) {
                        return new HibernateOptimisticLockingFailureException((StaleStateException) ex);
                }
 
                // fallback
                return new HibernateSystemException(ex);
        }
 
}