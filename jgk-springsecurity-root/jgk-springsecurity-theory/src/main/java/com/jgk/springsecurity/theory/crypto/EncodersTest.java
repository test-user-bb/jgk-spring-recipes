package com.jgk.springsecurity.theory.crypto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class EncodersTest {

    @Test public void testEncoders() {
//        Encryptors.standard("password", "salt");
        String password = "password";
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("");
        String result = encoder.encode(password);
        System.out.println(result);
        Assert.assertTrue(encoder.matches(password, result));
    }
}
