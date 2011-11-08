package com.jgk.fdb.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "JGK_RADIMMO5")
@Access(AccessType.FIELD)
public class Radimmo5 implements Serializable {

    private static final long serialVersionUID = 6476049399763701809L;

    /** identifier field */
    @EmbeddedId
    private Radimmo5PK comp_id;

    /** nullable persistent field */
    @Column(name = "IAMIDENTN", length = 1)
    private String iamidentn;

    /** nullable persistent field */
    @Column(name = "IAMTEXTN", length = 76)
    private String iamtextn;

    /** nullable persistent field */
    @Column(name = "IAMREFCAT", length = 1)
    private String iamrefcat;

    public String toString() {
        return new ToStringBuilder(this).append("comp_id", comp_id)
                .append("iamidentn", iamidentn).append("iamtextn", iamtextn)
                .append("iamrefcat", iamrefcat).toString();
    }

    public boolean equals(final Object other) {
        if (!(other instanceof Radimmo5))
            return false;
        Radimmo5 castOther = (Radimmo5) other;
        return new EqualsBuilder().append(comp_id, castOther.comp_id)
                .append(iamidentn, castOther.iamidentn)
                .append(iamtextn, castOther.iamtextn)
                .append(iamrefcat, castOther.iamrefcat).isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(comp_id).append(iamidentn)
                .append(iamtextn).append(iamrefcat).toHashCode();
    }

    /** full constructor */
    public Radimmo5(Radimmo5PK comp_id, String iamidentn,
            String iamtextn, String iamrefcat) {
        this.comp_id = comp_id;
        this.iamidentn = iamidentn;
        this.iamtextn = iamtextn;
        this.iamrefcat = iamrefcat;
    }

    /** default constructor */
    public Radimmo5() {
    }

    /** minimal constructor */
    public Radimmo5(Radimmo5PK comp_id) {
        this.comp_id = comp_id;
    }

    /**
     * GOODBYEhibernatedoclet.id generator-class="assigned"
     * 
     */
    public Radimmo5PK getComp_id() {
        return this.comp_id;
    }

    public void setComp_id(Radimmo5PK comp_id) {
        this.comp_id = comp_id;
    }

    /**
     * GOODBYEhibernatedoclet.property column="IAMIDENTN" length="1"
     * 
     */
    public String getIamidentn() {
        return this.iamidentn;
    }

    public void setIamidentn(String iamidentn) {
        this.iamidentn = iamidentn;
    }

    /**
     * GOODBYEhibernatedoclet.property column="IAMTEXTN" length="76"
     * 
     */
    public String getIamtextn() {
        return this.iamtextn;
    }

    public void setIamtextn(String iamtextn) {
        this.iamtextn = iamtextn;
    }

    /**
     * GOODBYEhibernatedoclet.property column="IAMREFCAT" length="1"
     * 
     */
    public String getIamrefcat() {
        return this.iamrefcat;
    }

    public void setIamrefcat(String iamrefcat) {
        this.iamrefcat = iamrefcat;
    }

}
