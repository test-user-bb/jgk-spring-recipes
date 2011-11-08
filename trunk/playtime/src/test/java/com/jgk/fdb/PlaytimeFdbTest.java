package com.jgk.fdb;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:/test-playtime-spring-beans-jpa-config.xml")
public class PlaytimeFdbTest {
    @Inject
    protected ApplicationContext applicationContext;

    @Test
    public void testing() {
        assertTrue(true);
        assertEquals(1, 1);
        assertNotNull(new Object());
        assertNull(null);
    }
}
