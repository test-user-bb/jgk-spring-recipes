package com.jgk.springsecurity.theory.crypto;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class EncodersTest {

    @Test public void testEncoders() {
//        Encryptors.standard("password", "salt");
        String password = "password";
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("");
        String result = encoder.encode(password);
//        System.out.println(result);
        Assert.assertTrue(encoder.matches(password, result));
        try {
            try {
                System.out.println(new String(MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8"))));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        MessageDigest m=null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        m.reset();
        String plaintext = password;
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        System.out.println(hashtext);
        System.out.println(hashtext.length());
    }
}
