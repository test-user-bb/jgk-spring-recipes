package com.jgk.springrecipes.springsecurity.autoconfig.passwordencoded.beans;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value="myNonEncodingPasswordEncoder")
public class MyNonEncodingPasswordEncoder implements PasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object salt) {
        return rawPass;
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return encPass.equals(rawPass);
    }

}
