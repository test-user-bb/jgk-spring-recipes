package com.jgk.springrecipes.springsecurity.jakub.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

@Repository(value="myDaoAuthenticationProvider")
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    
    @Autowired
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        System.out.println("MyDaoAuthenticationProvider.setUserDetailsService");
        super.setUserDetailsService(userDetailsService);
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        System.out.println("MyDaoAuthenticationProvider.authenticate");
        return super.authenticate(authentication);
    }

}
