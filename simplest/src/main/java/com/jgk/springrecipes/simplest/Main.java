package com.jgk.springrecipes.simplest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
				"classpath:com/jgk/springrecipes/simplest/simplest-application-config.xml"});

    }
}
