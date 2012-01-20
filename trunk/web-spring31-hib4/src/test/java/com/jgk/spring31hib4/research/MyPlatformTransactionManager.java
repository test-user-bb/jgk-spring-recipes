package com.jgk.spring31hib4.research;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class MyPlatformTransactionManager implements PlatformTransactionManager {

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition)
            throws TransactionException {
        System.out.println("getTransaction:" + definition);
        TransactionStatus ts = new TransactionStatus() {

            @Override
            public Object createSavepoint() throws TransactionException {
                System.out.println("createSavepoint");
                return null;
            }

            @Override
            public void rollbackToSavepoint(Object savepoint)
                    throws TransactionException {
               System.out.println("rollbackToSavepoint:"+savepoint);
                
            }

            @Override
            public void releaseSavepoint(Object savepoint)
                    throws TransactionException {
                System.out.println("releaseSavepoint:"+savepoint);
                
            }

            @Override
            public boolean isNewTransaction() {
                System.out.println("isNewTransaction");
                return false;
            }

            @Override
            public boolean hasSavepoint() {
                System.out.println("hasSavePoint");
                return false;
            }

            @Override
            public void setRollbackOnly() {
                System.out.println("setRollbackOnly");
                
            }

            @Override
            public boolean isRollbackOnly() {
                System.out.println("isRollbackOnly");

                return false;
            }

            @Override
            public void flush() {
                System.out.println("flush");
                
            }

            @Override
            public boolean isCompleted() {
                System.out.println("isCompleted");
                return false;
            }
            
        };
        return ts;
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        System.out.println("commit:" + status);
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        System.out.println("rollback:" + status);

    }

}
