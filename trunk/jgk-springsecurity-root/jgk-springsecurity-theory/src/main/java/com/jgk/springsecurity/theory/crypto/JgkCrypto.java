package com.jgk.springsecurity.theory.crypto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JgkCrypto {
    
    public static String getMd5AsText(String password) {
        return getCryptoHash("MD5", password);
    }
    public static byte[] getMd5(String password) {
        MessageDigest m=null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        return digest;
    }

    public static String getCryptoHash(String algo, String password) {
        byte[] digest = getMd5(password);
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }
}
