package com.jgk.springrecipes.springsecurity.openid.beans;

import java.io.Serializable;

public class NormalizedOpenIdAttributes implements Serializable {
    private static final long serialVersionUID = -7392083588710749244L;
    private String userLocalIdentifier;
    private String emailAddress;
    private String fullName;
    private String loginReplacement;
 
    public NormalizedOpenIdAttributes(String userLocalIdentifier, String emailAddress, String fullName, String loginReplacement) {
        this.userLocalIdentifier = userLocalIdentifier;
        this.emailAddress = emailAddress;
        this.fullName = fullName;
        this.loginReplacement = loginReplacement;
    }
 
    public String getUserLocalIdentifier() {
        return userLocalIdentifier;
    }
 
    public String getEmailAddress() {
        return emailAddress;
    }
 
    public String getFullName() {
        return fullName;
    }
 
    public String getLoginReplacement() {
        return loginReplacement;
    }
}
