package com.newland.aqhandle.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "organization_oa")
public class OrganizationOa {
    @Id
    @Column(name="ORG_ID")
    private Integer orgId;
    @Column(name="NAME")
    private String name;
    @Column(name="ORG_TYPE")
    private Integer orgType;
    @Column(name="ORG_LEVEL")
    private Integer orgLevel;
    @Column(name="ORG_HOME_TYPE")
    private Integer orgHomeType;
    @Column(name="ORG_PROPERTY")
    private Integer orgProperty;
    @Column(name="ORG_CODE")
    private String orgCode;
    @Column(name="SUPER_ORG_ID")
    private Integer superOrgId;
    @Column(name="HOME_CITY")
    private Integer homeCity;
    @Column(name="HOME_COUNTY")
    private Integer homeCounty;
    @Column(name="STATUS")
    private Integer status;
    @Column(name="INURE_TIME")
    private Date inureTime;
    @Column(name="EXPIRE_TIME")
    private Date expireTime;
    @Column(name="MODIFY_OPERATOR_ID")
    private Integer modifyOperatorId;
    @Column(name="MODIFY_TIME")
    private Date modifyTime;
    @Column(name="MODIFY_CONTENT")
    private String modifyContent;
    @Column(name="HIS_SEQ_ID")
    private Long hisSeqId;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="ORG_NAME")
    private String orgName;
    @Column(name="PERSON_LIABLE1")
    private String personLiable1;
    @Column(name="PERSON_LIABLE2")
    private String personLiable2;
    @Column(name="ORG_CUR_LEVEL")
    private Integer orgCurLevel;
    @Column(name="IS_CITY")
    private Integer isCity;
   
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Integer getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Integer orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Integer getOrgHomeType() {
        return orgHomeType;
    }

    public void setOrgHomeType(Integer orgHomeType) {
        this.orgHomeType = orgHomeType;
    }

    public Integer getOrgProperty() {
        return orgProperty;
    }

    public void setOrgProperty(Integer orgProperty) {
        this.orgProperty = orgProperty;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Integer getSuperOrgId() {
        return superOrgId;
    }

    public void setSuperOrgId(Integer superOrgId) {
        this.superOrgId = superOrgId;
    }

    public Integer getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(Integer homeCity) {
        this.homeCity = homeCity;
    }

    public Integer getHomeCounty() {
        return homeCounty;
    }

    public void setHomeCounty(Integer homeCounty) {
        this.homeCounty = homeCounty;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInureTime() {
        return inureTime;
    }

    public void setInureTime(Date inureTime) {
        this.inureTime = inureTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getModifyOperatorId() {
        return modifyOperatorId;
    }

    public void setModifyOperatorId(Integer modifyOperatorId) {
        this.modifyOperatorId = modifyOperatorId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyContent() {
        return modifyContent;
    }

    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent == null ? null : modifyContent.trim();
    }

    public Long getHisSeqId() {
        return hisSeqId;
    }

    public void setHisSeqId(Long hisSeqId) {
        this.hisSeqId = hisSeqId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getPersonLiable1() {
        return personLiable1;
    }

    public void setPersonLiable1(String personLiable1) {
        this.personLiable1 = personLiable1 == null ? null : personLiable1.trim();
    }

    public String getPersonLiable2() {
        return personLiable2;
    }

    public void setPersonLiable2(String personLiable2) {
        this.personLiable2 = personLiable2 == null ? null : personLiable2.trim();
    }

    public Integer getOrgCurLevel() {
        return orgCurLevel;
    }

    public void setOrgCurLevel(Integer orgCurLevel) {
        this.orgCurLevel = orgCurLevel;
    }

    public Integer getIsCity() {
        return isCity;
    }

    public void setIsCity(Integer isCity) {
        this.isCity = isCity;
    }
}