package com.jgk.springrecipes.springsecurity.openid.beans;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Customized {@code UserDetails} implementation.
 *
 */
public class OpenidCustomUserDetails extends User {
    private static final long serialVersionUID = 8739264018741303403L;
    private String email;
    private String name;
    private boolean newUser;

    public OpenidCustomUserDetails(String username, Collection<GrantedAuthority> authorities) {
        super(username, "unused", authorities);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

