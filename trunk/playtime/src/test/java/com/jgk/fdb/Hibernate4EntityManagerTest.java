package com.jgk.fdb;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class Hibernate4EntityManagerTest {

    @Test public void testBasic() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "firstdbPersistenceUnit" );
        
    }
}
