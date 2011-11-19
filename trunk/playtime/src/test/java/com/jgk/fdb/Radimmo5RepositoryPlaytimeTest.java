package com.jgk.fdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jgk.fdb.domain.Radimmo5;
import com.jgk.fdb.domain.Radimmo5PK;

public class Radimmo5RepositoryPlaytimeTest extends AbstractPlaytimeTest {
    
    @Test @Rollback(value=true)
    @Transactional(propagation=Propagation.REQUIRED)
    public void testing() {
        persistSomeData(300);
//        List<Radimmo5> list = radimmo5Repository.findAll();
//        for (Radimmo5 radimmo5 : list) {
//            System.out.println(radimmo5);
//        }
        Pageable page = new PageRequest(4, 5);
        Page<Radimmo5> p = radimmo5Repository.findAll(page);
        for (Radimmo5 radimmo5 : p.getContent()) {
            System.out.println(radimmo5);
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
            Radimmo5PK pk = new Radimmo5PK(rand.nextInt()*rand.nextInt()+rand.nextInt(), rand.nextInt());
            Radimmo5 en = new Radimmo5(pk);
            radimmo5Repository.save(en);
            
        }
    }

    
}
