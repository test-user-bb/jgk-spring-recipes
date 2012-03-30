package com.jgk.springrecipes.springsecurity.jakub.beans;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

@Component("myShaPasswordEncoder")
public class MyShaPasswordEncoder extends ShaPasswordEncoder {

    public MyShaPasswordEncoder() {
        super();
    }

    public MyShaPasswordEncoder(int strength) {
        super(strength);
    }

    @Override
    public String encodePassword(String rawPass, Object salt) {
        return super.encodePassword(rawPass, salt);
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return super.isPasswordValid(encPass, rawPass, salt);
    }

    @Override
    public String getAlgorithm() {
        return super.getAlgorithm();
    }

    @Override
    public void setIterations(int iterations) {
        super.setIterations(iterations);
    }

    @Override
    protected String[] demergePasswordAndSalt(String mergedPasswordSalt) {
        return super.demergePasswordAndSalt(mergedPasswordSalt);
    }

    @Override
    protected String mergePasswordAndSalt(String password, Object salt,
            boolean strict) {
        return super.mergePasswordAndSalt(password, salt, strict);
    }

}
