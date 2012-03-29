package com.jgk.springrecipes.springsecurity.openid.beans;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationWithOpenIdUserDetailsGetter implements
        UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        System.out.println("loadUserByUsername: " + username);
        new AuthenticationUserDetailsService<Authentication>() {

            @Override
            public UserDetails loadUserDetails(Authentication token)
                    throws UsernameNotFoundException {
                System.out.println("Loading user details");
                return null;
            }
        };
        return null;
    }

}
