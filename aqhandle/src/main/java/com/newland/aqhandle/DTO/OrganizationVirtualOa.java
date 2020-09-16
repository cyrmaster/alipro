package com.newland.aqhandle.DTO;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "organization_oa_virtual")
public class OrganizationVirtualOa {
    @Id
    @Column(name="BACK_ORG_ID")
    private Integer backOrgId;
    @Column(name="NAME")
    private String name=null;
    @Column(name="ORG_TYPE")
    private Integer orgType=null;
    @Column(name="ORG_LEVEL")
    private Integer orgLevel=null;
    @Column(name="ORG_HOME_TYPE")
    private Integer orgHomeType=null;
    @Column(name="ORG_PROPERTY")
    private Integer orgProperty=null;
    @Column(name="ORG_CODE")
    private String orgCode=null;
    @Column(name="SUPER_ORG_ID")
    private Integer superOrgId=null;
    @Column(name="HOME_CITY")
    private Integer homeCity=null;
    @Column(name="HOME_COUNTY")
    private Integer homeCounty=null;
    @Column(name="STATUS")
    private Integer status=null;
    @Column(name="INURE_TIME")
    private Date inureTime=null;
    @Column(name="EXPIRE_TIME")
    private Date expireTime=null;
    @Column(name="MODIFY_OPERATOR_ID")
    private Integer modifyOperatorId=null;
    @Column(name="MODIFY_TIME")
    private Date modifyTime=null;
    @Column(name="MODIFY_CONTENT")
    private String modifyContent=null;
    @Column(name="HIS_SEQ_ID")
    private Long hisSeqId=null;
    @Column(name="DESCRIPTION")
    private String description=null;
    @Column(name="ORG_NAME")
    private String orgName=null;
    @Column(name="PERSON_LIABLE1")
    private String personLiable1=null;
    @Column(name="PERSON_LIABLE2")
    private String personLiable2=null;
    @Column(name="ORG_CUR_LEVEL")
    private Integer orgCurLevel=null;
    @Column(name="IS_CITY")
    private Integer isCity=null;

    public Integer getBackOrgId () {
        return backOrgId;
    }

    public void setBackOrgId (Integer backOrgId) {
        this.backOrgId = backOrgId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Integer getOrgType () {
        return orgType;
    }

    public void setOrgType (Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getOrgLevel () {
        return orgLevel;
    }

    public void setOrgLevel (Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getOrgHomeType () {
        return orgHomeType;
    }

    public void setOrgHomeType (Integer orgHomeType) {
        this.orgHomeType = orgHomeType;
    }

    public Integer getOrgProperty () {
        return orgProperty;
    }

    public void setOrgProperty (Integer orgProperty) {
        this.orgProperty = orgProperty;
    }

    public String getOrgCode () {
        return orgCode;
    }

    public void setOrgCode (String orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getSuperOrgId () {
        return superOrgId;
    }

    public void setSuperOrgId (Integer superOrgId) {
        this.superOrgId = superOrgId;
    }

    public Integer getHomeCity () {
        return homeCity;
    }

    public void setHomeCity (Integer homeCity) {
        this.homeCity = homeCity;
    }

    public Integer getHomeCounty () {
        return homeCounty;
    }

    public void setHomeCounty (Integer homeCounty) {
        this.homeCounty = homeCounty;
    }

    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    public Date getInureTime () {
        return inureTime;
    }

    public void setInureTime (Date inureTime) {
        this.inureTime = inureTime;
    }

    public Date getExpireTime () {
        return expireTime;
    }

    public void setExpireTime (Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getModifyOperatorId () {
        return modifyOperatorId;
    }

    public void setModifyOperatorId (Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }

    public Date getModifyTime () {
        return modifyTime;
    }

    public void setModifyTime (Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyContent () {
        return modifyContent;
    }

    public void setModifyContent (String modifyContent) {
        this.modifyContent = modifyContent;
    }

    public Long getHisSeqId () {
        return hisSeqId;
    }

    public void setHisSeqId (Long hisSeqId) {
        this.hisSeqId = hisSeqId;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getOrgName () {
        return orgName;
    }

    public void setOrgName (String orgName) {
        this.orgName = orgName;
    }

    public String getPersonLiable1 () {
        return personLiable1;
    }

    public void setPersonLiable1 (String personLiable1) {
        this.personLiable1 = personLiable1;
    }

    public String getPersonLiable2 () {
        return personLiable2;
    }

    public void setPersonLiable2 (String personLiable2) {
        this.personLiable2 = personLiable2;
    }

    public Integer getOrgCurLevel () {
        return orgCurLevel;
    }

    public void setOrgCurLevel (Integer orgCurLevel) {
        this.orgCurLevel = orgCurLevel;
    }

    public Integer getIsCity () {
        return isCity;
    }

    public void setIsCity (Integer isCity) {
        this.isCity = isCity;
    }
}
