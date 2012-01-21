package com.jgk.spring31hib4.domains.firstdb;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


@Entity
@Table(name = "RADIMEF0")
@Access(AccessType.FIELD)
public class Radimef0 implements Serializable {

    private static final long serialVersionUID = -5173827439397689137L;

    /** identifier field */
    @Id
    @Column(name="ADI_EFFTC")
    private String adiEfftc;

    /** nullable persistent field */
    @Column(name="ADI_EFFTXT")
    private String adiEfftxt;

    /** full constructor */
    public Radimef0(String adiEfftc, String adiEfftxt) {
        this.adiEfftc = adiEfftc;
        this.adiEfftxt = adiEfftxt;
    }

    /** default constructor */
    public Radimef0() {
    }

    /** minimal constructor */
    public Radimef0(String adiEfftc) {
        this.adiEfftc = adiEfftc;
    }

    /**
     * GOODBYEhibernatedoclet.id generator-class="assigned"
     * type="java.lang.String" column="ADI_EFFTC"
     * 
     */
    public String getAdiEfftc() {
        return this.adiEfftc;
    }

    public void setAdiEfftc(String adiEfftc) {
        this.adiEfftc = adiEfftc;
    }

    /**
     * GOODBYEhibernatedoclet.property column="ADI_EFFTXT" length="50"
     * 
     */
    public String getAdiEfftxt() {
        return this.adiEfftxt;
    }

    public void setAdiEfftxt(String adiEfftxt) {
        this.adiEfftxt = adiEfftxt;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Radimef0 [adiEfftc=");
        builder.append(adiEfftc);
        builder.append(", adiEfftxt=");
        builder.append(adiEfftxt);
        builder.append("]");
        return builder.toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Radimef0))
            return false;
        Radimef0 castOther = (Radimef0) other;
        return new EqualsBuilder().append(this.getAdiEfftc(),
                castOther.getAdiEfftc()).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getAdiEfftc()).toHashCode();
    }

}