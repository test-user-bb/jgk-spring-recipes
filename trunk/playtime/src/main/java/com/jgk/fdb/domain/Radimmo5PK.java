package com.jgk.fdb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Embeddable
public class Radimmo5PK implements Serializable {

    private static final long serialVersionUID = 2043060007967090161L;

    /** identifier field */
    @Column(name="DDI_MONOX")
    private Integer ddiMonox;

    /** identifier field */
    @Column(name="ADI_MONOSN")
    private Integer adiMonosn;

    /** full constructor */
    public Radimmo5PK(Integer ddiMonox, Integer adiMonosn) {
        this.ddiMonox = ddiMonox;
        this.adiMonosn = adiMonosn;
    }

    /** default constructor */
    public Radimmo5PK() {
    }

    /**
     * GOODBYEhibernatedoclet.property column="DDI_MONOX" length="5"
     * 
     */
    public Integer getDdiMonox() {
        return this.ddiMonox;
    }

    public void setDdiMonox(Integer ddiMonox) {
        this.ddiMonox = ddiMonox;
    }

    /**
     * GOODBYEhibernatedoclet.property column="ADI_MONOSN" length="3"
     * 
     */
    public Integer getAdiMonosn() {
        return this.adiMonosn;
    }

    public void setAdiMonosn(Integer adiMonosn) {
        this.adiMonosn = adiMonosn;
    }

    public String toString() {
        return new ToStringBuilder(this).append("ddiMonox", getDdiMonox())
                .append("adiMonosn", getAdiMonosn()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Radimmo5PK))
            return false;
        Radimmo5PK castOther = (Radimmo5PK) other;
        return new EqualsBuilder()
                .append(this.getDdiMonox(), castOther.getDdiMonox())
                .append(this.getAdiMonosn(), castOther.getAdiMonosn())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getDdiMonox())
                .append(getAdiMonosn()).toHashCode();
    }

}
