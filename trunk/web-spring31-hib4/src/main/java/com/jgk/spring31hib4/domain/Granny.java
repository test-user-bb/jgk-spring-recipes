package com.jgk.spring31hib4.domain;

import java.util.Date;

public class Granny {
    private Integer grannyId;
    private Date birthdate;
    private String firstName,lastName;
    private Jed jed;
    
    
    public Jed getJed() {
        return jed;
    }

    public void setJed(Jed jed) {
        this.jed = jed;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGrannyId() {
        return grannyId;
    }

    public void setGrannyId(Integer grannyId) {
        this.grannyId = grannyId;
    }

    public static Granny createGranny(String _firstName, String _lastName,
            Date _birthdate) {
        Granny granny = new Granny();
        granny.setFirstName(_firstName);
        granny.setLastName(_lastName);
        granny.setBirthdate(_birthdate);
        return granny;
    }

}
