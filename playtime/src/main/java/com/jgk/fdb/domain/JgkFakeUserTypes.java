package com.jgk.fdb.domain;

import java.sql.Clob;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "JGK_FAKE_USER_TYPES")
@Access(AccessType.FIELD)
public class JgkFakeUserTypes {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("JgkFakeUserTypes [happy=");
        builder.append(happy);
        builder.append(", wealthy=");
        builder.append(wealthy);
        builder.append(", id=");
        builder.append(id);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append("]");
        return builder.toString();
    }
    @Type(type="org.hibernate.type.YesNoType")
    private boolean happy;
    
    @Type(type="org.hibernate.type.TrueFalseType")
    private boolean wealthy;

    @Type(type="org.hibernate.type.ClobType")
    private Clob veryLongName;
    
    public boolean isHappy() {
        return happy;
    }
    public void setHappy(boolean happy) {
        this.happy = happy;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public void setWealthy(boolean wealthy) {
        this.wealthy = wealthy;
    }
    public boolean isWealthy() {
        return wealthy;
    }
    public void setVeryLongName(Clob veryLongName) {
        this.veryLongName = veryLongName;
    }
    public Clob getVeryLongName() {
        return veryLongName;
    }
    @Id
    @Column(name="MY_ID", nullable=false, precision=19)
    @SequenceGenerator(name="FAKE_SEQUENCE", sequenceName="FAKE_SEQUENCE", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAKE_SEQUENCE")
    private Integer id;

    private String firstName;
    private String lastName;
    
    
    
}
