package com.jgk.springrecipes.blackbelt.miscellaneous;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@BeforeClass
    public static void onlyOnce()
    {
        System.out.println("Something before class");
    }

	@Test
    public void testApp()
    {
        assertTrue( true );
    }
}
