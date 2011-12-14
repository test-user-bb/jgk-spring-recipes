package com.jgk.spring31hib4.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Jed {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Jed [personId=");
        builder.append(personId);
        builder.append(", birthdate=");
        builder.append(birthdate);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", grannies=");
        builder.append(grannies);
        builder.append("]");
        return builder.toString();
    }

    private Integer personId;
    private Date birthdate;
    private String firstName,lastName;
    private Set<Granny> grannies;
    
    public void addGranny(Granny granny) {
        if(grannies==null) {
            grannies=new HashSet<Granny>();
        }
        granny.setJed(this);
        grannies.add(granny);
    }

    public Set<Granny> getGrannies() {
        return grannies;
    }

    public void setGrannies(Set<Granny> grannies) {
        this.grannies = grannies;
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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public static Jed create(String _firstName, String _lastName, Date _birthdate) {
        Jed jed = new Jed();
        jed.setFirstName(_firstName);
        jed.setLastName(_lastName);
        jed.setBirthdate(_birthdate);
        return jed;
    }
}
