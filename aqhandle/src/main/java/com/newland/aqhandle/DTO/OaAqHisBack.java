package com.newland.aqhandle.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "oa_aq_his_back")
public class OaAqHisBack {
    @Id
    private String OA_AQ_HIS_BACK_ID;

    private String AQ_TYPE;

    @Column(name = "ORG_KEY")
    private String ORGKEY;

    private String ORG_CODE;

    private String ORG_NAME;

    private String ORG_DISABLED;

    private Date ORG_CREATE;

    private String ORG_CREATEBY;

    private Date ORG_UPDATE;

    private String ORG_UPDATEBY;

    private String ORG_ORDER;

    private Date INSURE_TIME;

    public Date getINSURE_TIME () {
        return INSURE_TIME;
    }

    public void setINSURE_TIME (Date INSURE_TIME) {
        this.INSURE_TIME = INSURE_TIME;
    }

    public String getOA_AQ_HIS_BACK_ID() {
        return OA_AQ_HIS_BACK_ID;
    }

    public void setOA_AQ_HIS_BACK_ID(String OA_AQ_HIS_BACK_ID) {
        this.OA_AQ_HIS_BACK_ID = OA_AQ_HIS_BACK_ID == null ? null : OA_AQ_HIS_BACK_ID.trim();
    }

    public String getAQ_TYPE() {
        return AQ_TYPE;
    }

    public void setAQ_TYPE(String AQ_TYPE) {
        this.AQ_TYPE = AQ_TYPE == null ? null : AQ_TYPE.trim();
    }

    public String getORGKEY () {
        return ORGKEY;
    }

    public void setORGKEY (String ORGKEY) {
        this.ORGKEY = ORGKEY;
    }

    public String getORG_CODE() {
        return ORG_CODE;
    }

    public void setORG_CODE(String ORG_CODE) {
        this.ORG_CODE = ORG_CODE == null ? null : ORG_CODE.trim();
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setORG_NAME(String ORG_NAME) {
        this.ORG_NAME = ORG_NAME == null ? null : ORG_NAME.trim();
    }

    public String getORG_DISABLED() {
        return ORG_DISABLED;
    }

    public void setORG_DISABLED(String ORG_DISABLED) {
        this.ORG_DISABLED = ORG_DISABLED == null ? null : ORG_DISABLED.trim();
    }

    public Date getORG_CREATE () {
        return ORG_CREATE;
    }

    public void setORG_CREATE (Date ORG_CREATE) {
        this.ORG_CREATE = ORG_CREATE;
    }

    public String getORG_CREATEBY () {
        return ORG_CREATEBY;
    }

    public void setORG_CREATEBY (String ORG_CREATEBY) {
        this.ORG_CREATEBY = ORG_CREATEBY;
    }

    public Date getORG_UPDATE () {
        return ORG_UPDATE;
    }

    public void setORG_UPDATE (Date ORG_UPDATE) {
        this.ORG_UPDATE = ORG_UPDATE;
    }

    public String getORG_UPDATEBY() {
        return ORG_UPDATEBY;
    }

    public void setORG_UPDATEBY(String ORG_UPDATEBY) {
        this.ORG_UPDATEBY = ORG_UPDATEBY == null ? null : ORG_UPDATEBY.trim();
    }

    public String getORG_ORDER() {
        return ORG_ORDER;
    }

    public void setORG_ORDER(String ORG_ORDER) {
        this.ORG_ORDER = ORG_ORDER == null ? null : ORG_ORDER.trim();
    }
}