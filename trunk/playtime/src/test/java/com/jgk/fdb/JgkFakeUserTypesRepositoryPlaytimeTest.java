package com.jgk.fdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Clob;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.LobHelper;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jgk.fdb.domain.JgkFakeUserTypes;

public class JgkFakeUserTypesRepositoryPlaytimeTest extends AbstractPlaytimeTest {
    
    @Test @Rollback(value=false)
    @Transactional(propagation=Propagation.REQUIRED)
    public void testing() {
        persistSomeData(10);
        Pageable page = new PageRequest(3, 2);
        Page<JgkFakeUserTypes> p = jgkFakeUserTypesRepository.findAll(page);
        for (JgkFakeUserTypes jkt : p.getContent()) {
            System.out.println(jkt);
        }
//        System.out.println("BEFORE DELETE: " + radimmo5Repository.count());
//        radimmo5Repository.deleteAll();
//        System.out.println("AFTER DELETE: " + radimmo5Repository.count());
        
        assertTrue(true);
        assertEquals(1, 1);
        assertNotNull(new Object());
        assertNull(null);
    }
    
    private void persistSomeData(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println("rand="+rand);
            JgkFakeUserTypes j = new JgkFakeUserTypes();
            j.setFirstName("JE"+i);
            j.setLastName("CL"+i);
            j.setHappy(i%2==0);
            j.setWealthy(i%4==0);
            
            jgkFakeUserTypesRepository.save(j);
            
        }
    }

    
}
