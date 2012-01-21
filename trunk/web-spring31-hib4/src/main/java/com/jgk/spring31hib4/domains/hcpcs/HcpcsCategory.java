package com.jgk.spring31hib4.domains.hcpcs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.jgk.spring31hib4.util.DateUtil;

@Entity
@Table(name="HCPCS_CATEGORY")
@Access(AccessType.FIELD)
public class HcpcsCategory implements Serializable {


    private static final long serialVersionUID = 378263831869129847L;

    @Id
    @SequenceGenerator(name="HCPCS_CATEGORY_SEQUENCE", sequenceName="HCPCS_CATEGORY_SEQUENCE")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HCPCS_CATEGORY_SEQUENCE")
    @Column(name="HCPCS_CATEGORY_ID", unique=true, nullable=false, length=1)
    private Long hcpcsCategoryId;
    
    @Column(name="SHORT_DESCRIPTION", length=50)
    private String shortDescription;
    
    @Column(name="LONG_DESCRIPTION", length=500)
    private String longDescription;
    
    @Column(name="PREFIX", length=5)
    private String prefix;
    
    @Column(name="RANGE_LOW", length=4)
    private String rangeLow;
    
    @Column(name="RANGE_HIGH", length=4)
    private String rangeHigh;
    
    @Column(name="SIBLING_ORDER", precision=19)
    private Integer siblingOrder;
    
    @Column(name="CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Column(name="MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    
    @Column(name="PARENT_ID", precision=19, insertable=false, updatable=false)
    private Long parentId;
    
    @ManyToOne(optional=true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = HcpcsCategory.class)
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "HCPCS_CATEGORY_ID")
    private HcpcsCategory parent;
    
    @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "PARENT_ID")//, referencedColumnName="HCPCS_CATEGORY_ID")
    @OrderColumn(name="SIBLING_ORDER", insertable=true, updatable=true, nullable=true)
    protected List<HcpcsCategory> children = new ArrayList<HcpcsCategory>();
    
    @Column(name="FGCOLOR", length=15)
    private String fgcolor="black";
    
    @Column(name="BGCOLOR",length=15)
    private String bgcolor="#A52A2A";
    
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("HcpcsCategory [hcpcsCategoryId=");
        builder.append(hcpcsCategoryId);
        builder.append(", shortDescription=");
        builder.append(shortDescription);
        builder.append(", longDescription=");
        builder.append(longDescription);
        builder.append(", prefix=");
        builder.append(prefix);
        builder.append(", rangeLow=");
        builder.append(rangeLow);
        builder.append(", rangeHigh=");
        builder.append(rangeHigh);
        builder.append(", siblingOrder=");
        builder.append(siblingOrder);
        builder.append(", createdDate=");
        builder.append(createdDate);
        builder.append(", modifiedDate=");
        builder.append(modifiedDate);
        builder.append(", parentId=");
        builder.append(parentId);
        builder.append(", parent=");
        builder.append(parent);
        builder.append(", children=");
//        builder.append(showChildren());
//        builder.append(children.size());
        builder.append(", fgcolor=");
        builder.append(fgcolor);
        builder.append(", bgcolor=");
        builder.append(bgcolor);
        builder.append("]");
        return builder.toString();
    }
    private Object showChildren() {
        StringBuilder sb = new StringBuilder();
        sb.append("No. Children="+children.size());
        sb.append(" ");
        for (HcpcsCategory cat : children) {
            if(cat==null) {
                sb.append(", NULL CAT");
            }else{
                sb.append(", CAT ID: "+cat.getHcpcsCategoryId());
            }
        }
        return sb.toString();
    }
    public boolean equals(final Object other) {
        if (!(other instanceof HcpcsCategory))
            return false;
        HcpcsCategory castOther = (HcpcsCategory) other;
        return new EqualsBuilder().append(hcpcsCategoryId,
                castOther.hcpcsCategoryId).isEquals();
    }
    public int hashCode() {
        return new HashCodeBuilder().append(hcpcsCategoryId).toHashCode();
    }
    
    public HcpcsCategory() {}
    
    public HcpcsCategory(String _shortDescription, String _longDescription,
            String _prefix, HcpcsCategory _parent, String _rangeLow, String _rangeHigh,
            Integer _siblingOrder, Date createdDate, Date modifiedDate) {
        this.shortDescription=_shortDescription;
        this.longDescription=_longDescription;
        this.prefix=_prefix;
        this.parent=_parent;
        this.rangeLow=_rangeLow;
        this.rangeHigh=_rangeHigh;
        this.siblingOrder=_siblingOrder;
        this.createdDate=DateUtil.getDateCopyOrNull(createdDate);
        this.modifiedDate=DateUtil.getDateCopyOrNull(modifiedDate);
    }
    public HcpcsCategory(Long _hcpcsCategoryId, String _shortDescription, String _longDescription,
            String _prefix, HcpcsCategory _parent, String _rangeLow, String _rangeHigh,
            Integer _siblingOrder, Date createdDate, Date modifiedDate) {
        this.hcpcsCategoryId=_hcpcsCategoryId;
        this.shortDescription=_shortDescription;
        this.longDescription=_longDescription;
        this.prefix=_prefix;
        this.parent=_parent;
        this.rangeLow=_rangeLow;
        this.rangeHigh=_rangeHigh;
        this.siblingOrder=_siblingOrder;
        this.createdDate=DateUtil.getDateCopyOrNull(createdDate);
        this.modifiedDate=DateUtil.getDateCopyOrNull(modifiedDate);
    }

    /** 
     *         
     */
    public Long getHcpcsCategoryId() {
        return hcpcsCategoryId;
    }
    /**
     * @param hcpcsCategoryId the hcpcsCategoryId to set
     */
    public void setHcpcsCategoryId(Long hcpcsCategoryId) {
        this.hcpcsCategoryId = hcpcsCategoryId;
    }

    /**
     * @param hcpcsPicId the hcpcsPicId to set
     */
    public void setHcpcsPicId(Long hcpcsCategoryId) {
        this.hcpcsCategoryId = hcpcsCategoryId;
    }

    /**
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param description the description to set
     */
    public void setShortDescription(String description) {
        this.shortDescription = description;
    }

    /**
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * @param description the description to set
     */
    public void setLongDescription(String description) {
        this.longDescription = description;
    }
    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }
    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    /**
     * @return the rangeLow
     */
    public String getRangeLow() {
        return rangeLow;
    }
    /**
     */
    public void setRangeLow(String rangeLow) {
        this.rangeLow = rangeLow;
    }
    /**
     * @return the rangeHigh
     */
    public String getRangeHigh() {
        return rangeHigh;
    }
    /**
     */
    public void setRangeHigh(String rangeHigh) {
        this.rangeHigh = rangeHigh;
    }
    /**
     * @return the siblingOrder
     */
    public Integer getSiblingOrder() {
        return siblingOrder;
    }
    /**
     * @param siblingOrder the siblingOrder to set
     */
    public void setSiblingOrder(Integer siblingOrder) {
        this.siblingOrder = siblingOrder;
    }
    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return (createdDate!=null)?new Date(createdDate.getTime()):(Date)null;
    }
    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date _createdDate) {
        this.createdDate = DateUtil.getDateCopyOrNull(_createdDate);
    }
    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return (modifiedDate!=null)?new Date(modifiedDate.getTime()):(Date)null;
    }
    /**
     * @param _modifiedDate the modifiedDate to set
     */
    public void setModifiedDate(Date _modifiedDate) {
        this.modifiedDate = DateUtil.getDateCopyOrNull(_modifiedDate);
    }
    /**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    /**
     * 
     */
    public HcpcsCategory getParent() {
        return parent;
    }
    /**
     * @param parent the parent to set
     */
    public void setParent(HcpcsCategory parent) {
        this.parent = parent;
    }
    /**
     */
    public List<HcpcsCategory> getChildren() {
        return children;
    }
    /**
     * @param children the children to set
     */
    public void setChildren(List<HcpcsCategory> children) {
        this.children = children;
    }

    public void addChild(HcpcsCategory child) {
        child.setParent(this);
        this.children.add(child);
        // if there is no display order set it as last
        if (child.getSiblingOrder() == null
                || child.getSiblingOrder().intValue() == 0) {
            child.setSiblingOrder(Integer.valueOf(this.getChildren().size()));
        }
    }
    /**
     * This is an HTML-friendly color (e.g. black or #BC8F8F)
     * @return the fgcolor
     */
    public String getFgcolor() {
        return fgcolor;
    }
    /**
     * @param fgcolor the fgcolor to set
     */
    public void setFgcolor(String fgcolor) {
        this.fgcolor = fgcolor;
    }
    /**
     * @return the bgcolor
     */
    public String getBgcolor() {
        return bgcolor;
    }
    /**
     * @param bgcolor the bgcolor to set
     */
    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

}
