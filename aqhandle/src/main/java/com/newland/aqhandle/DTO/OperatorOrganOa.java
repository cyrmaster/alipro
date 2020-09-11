package com.newland.aqhandle.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="operator_organ_oa")
public class OperatorOrganOa {
    @Id
    @Column(name="OPERATOR_ID")
    private Long operatorId;
    @Column(name="OA_ORG_ID")
    private Integer oaOrgId;
    @Column(name="ERP_ORG_ID")
    private Integer erpOrgId;
    @Column(name="OPERATOR_TYPE")
    private Integer operatorType;
    @Column(name="OPERATOR_SUB_TYPE")
    private Integer operatorSubType;
    @Column(name="IS_AUTHORITY")
    private Integer isAuthority;
    @Column(name="MODIFY_OPERATOR_ID")
    private Integer modifyOperatorId;
    @Column(name="MODIFY_TIME")
    private Date modifyTime;
    @Column(name="MODIFY_CONTENT")
    private String modifyContent;
    @Column(name="HIS_SEQ_ID")
    private Long hisSeqId;
    @Column(name="SUB_ERP_ORG_ID")
    private Integer subErpOrgId;

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getOaOrgId() {
        return oaOrgId;
    }

    public void setOaOrgId(Integer oaOrgId) {
        this.oaOrgId = oaOrgId;
    }

    public Integer getErpOrgId() {
        return erpOrgId;
    }

    public void setErpOrgId(Integer erpOrgId) {
        this.erpOrgId = erpOrgId;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public Integer getOperatorSubType() {
        return operatorSubType;
    }

    public void setOperatorSubType(Integer operatorSubType) {
        this.operatorSubType = operatorSubType;
    }

    public Integer getIsAuthority() {
        return isAuthority;
    }

    public void setIsAuthority(Integer isAuthority) {
        this.isAuthority = isAuthority;
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

    public Integer getSubErpOrgId() {
        return subErpOrgId;
    }

    public void setSubErpOrgId(Integer subErpOrgId) {
        this.subErpOrgId = subErpOrgId;
    }
}