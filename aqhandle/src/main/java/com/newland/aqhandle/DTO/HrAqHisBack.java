package com.newland.aqhandle.DTO;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "hr_aq_his_back")
public class HrAqHisBack {
    @Id
    private String HR_AQ_HIS_BACK_ID;

    private String AQ_TYPE;

    private String ACT_UDF_NUM;

    private String ACT_NAME;

    private String ACT_KEY;

    private String ACT_CUST_TYPE;

    private String ACT_STATUS;

    private Date ACT_CREATE;

    private String ACT_CREATEBY;

    private Date ACT_UPDATE;

    private String ACT_UPDATEBY;

    private String ACT_UDF_DSPNAME;

    private String ACT_UDF_FUNC;

    private String ACT_UDF_SUPVSR;

    private String ACT_UDF_MGR;

    private String ACT_UDF_ADDR;

    private String ACT_UDF_ZIP;

    private String ACT_UDF_TEL;

    private String ACT_UDF_FAX;

    private String ACT_UDF_ERPID;

    private String ACT_UDF_LOC;

    private String PARENT_ACT_UDF_NUM;

    private String ACT_REMARK;

    private String ACT_UDF_COMCODE;

    private Date INSURE_TIME;

    public Date getINSURE_TIME () {
        return INSURE_TIME;
    }

    public void setINSURE_TIME (Date INSURE_TIME) {
        this.INSURE_TIME = INSURE_TIME;
    }

    public String getHR_AQ_HIS_BACK_ID() {
        return HR_AQ_HIS_BACK_ID;
    }

    public void setHR_AQ_HIS_BACK_ID(String HR_AQ_HIS_BACK_ID) {
        this.HR_AQ_HIS_BACK_ID = HR_AQ_HIS_BACK_ID == null ? null : HR_AQ_HIS_BACK_ID.trim();
    }

    public String getAQ_TYPE() {
        return AQ_TYPE;
    }

    public void setAQ_TYPE(String AQ_TYPE) {
        this.AQ_TYPE = AQ_TYPE == null ? null : AQ_TYPE.trim();
    }

    public String getACT_UDF_NUM() {
        return ACT_UDF_NUM;
    }

    public void setACT_UDF_NUM(String ACT_UDF_NUM) {
        this.ACT_UDF_NUM = ACT_UDF_NUM == null ? null : ACT_UDF_NUM.trim();
    }

    public String getACT_NAME() {
        return ACT_NAME;
    }

    public void setACT_NAME(String ACT_NAME) {
        this.ACT_NAME = ACT_NAME == null ? null : ACT_NAME.trim();
    }

    public String getACT_KEY() {
        return ACT_KEY;
    }

    public void setACT_KEY(String ACT_KEY) {
        this.ACT_KEY = ACT_KEY == null ? null : ACT_KEY.trim();
    }

    public String getACT_CUST_TYPE() {
        return ACT_CUST_TYPE;
    }

    public void setACT_CUST_TYPE(String ACT_CUST_TYPE) {
        this.ACT_CUST_TYPE = ACT_CUST_TYPE == null ? null : ACT_CUST_TYPE.trim();
    }

    public String getACT_STATUS() {
        return ACT_STATUS;
    }

    public void setACT_STATUS(String ACT_STATUS) {
        this.ACT_STATUS = ACT_STATUS == null ? null : ACT_STATUS.trim();
    }

    public Date getACT_CREATE () {
        return ACT_CREATE;
    }

    public void setACT_CREATE (Date ACT_CREATE) {
        this.ACT_CREATE = ACT_CREATE;
    }

    public String getACT_CREATEBY () {
        return ACT_CREATEBY;
    }

    public void setACT_CREATEBY (String ACT_CREATEBY) {
        this.ACT_CREATEBY = ACT_CREATEBY;
    }

    public Date getACT_UPDATE () {
        return ACT_UPDATE;
    }

    public void setACT_UPDATE (Date ACT_UPDATE) {
        this.ACT_UPDATE = ACT_UPDATE;
    }

    public String getACT_UPDATEBY() {
        return ACT_UPDATEBY;
    }

    public void setACT_UPDATEBY(String ACT_UPDATEBY) {
        this.ACT_UPDATEBY = ACT_UPDATEBY == null ? null : ACT_UPDATEBY.trim();
    }

    public String getACT_UDF_DSPNAME() {
        return ACT_UDF_DSPNAME;
    }

    public void setACT_UDF_DSPNAME(String ACT_UDF_DSPNAME) {
        this.ACT_UDF_DSPNAME = ACT_UDF_DSPNAME == null ? null : ACT_UDF_DSPNAME.trim();
    }

    public String getACT_UDF_FUNC() {
        return ACT_UDF_FUNC;
    }

    public void setACT_UDF_FUNC(String ACT_UDF_FUNC) {
        this.ACT_UDF_FUNC = ACT_UDF_FUNC == null ? null : ACT_UDF_FUNC.trim();
    }

    public String getACT_UDF_SUPVSR() {
        return ACT_UDF_SUPVSR;
    }

    public void setACT_UDF_SUPVSR(String ACT_UDF_SUPVSR) {
        this.ACT_UDF_SUPVSR = ACT_UDF_SUPVSR == null ? null : ACT_UDF_SUPVSR.trim();
    }

    public String getACT_UDF_MGR() {
        return ACT_UDF_MGR;
    }

    public void setACT_UDF_MGR(String ACT_UDF_MGR) {
        this.ACT_UDF_MGR = ACT_UDF_MGR == null ? null : ACT_UDF_MGR.trim();
    }

    public String getACT_UDF_ADDR() {
        return ACT_UDF_ADDR;
    }

    public void setACT_UDF_ADDR(String ACT_UDF_ADDR) {
        this.ACT_UDF_ADDR = ACT_UDF_ADDR == null ? null : ACT_UDF_ADDR.trim();
    }

    public String getACT_UDF_ZIP() {
        return ACT_UDF_ZIP;
    }

    public void setACT_UDF_ZIP(String ACT_UDF_ZIP) {
        this.ACT_UDF_ZIP = ACT_UDF_ZIP == null ? null : ACT_UDF_ZIP.trim();
    }

    public String getACT_UDF_TEL() {
        return ACT_UDF_TEL;
    }

    public void setACT_UDF_TEL(String ACT_UDF_TEL) {
        this.ACT_UDF_TEL = ACT_UDF_TEL;
    }

    public String getACT_UDF_FAX() {
        return ACT_UDF_FAX;
    }

    public void setACT_UDF_FAX(String ACT_UDF_FAX) {
        this.ACT_UDF_FAX = ACT_UDF_FAX == null ? null : ACT_UDF_FAX.trim();
    }

    public String getACT_UDF_ERPID() {
        return ACT_UDF_ERPID;
    }

    public void setACT_UDF_ERPID(String ACT_UDF_ERPID) {
        this.ACT_UDF_ERPID = ACT_UDF_ERPID == null ? null : ACT_UDF_ERPID.trim();
    }

    public String getACT_UDF_LOC() {
        return ACT_UDF_LOC;
    }

    public void setACT_UDF_LOC(String ACT_UDF_LOC) {
        this.ACT_UDF_LOC = ACT_UDF_LOC == null ? null : ACT_UDF_LOC.trim();
    }

    public String getPARENT_ACT_UDF_NUM() {
        return PARENT_ACT_UDF_NUM;
    }

    public void setPARENT_ACT_UDF_NUM(String PARENT_ACT_UDF_NUM) {
        this.PARENT_ACT_UDF_NUM = PARENT_ACT_UDF_NUM == null ? null : PARENT_ACT_UDF_NUM.trim();
    }

    public String getACT_REMARK() {
        return ACT_REMARK;
    }

    public void setACT_REMARK(String ACT_REMARK) {
        this.ACT_REMARK = ACT_REMARK == null ? null : ACT_REMARK.trim();
    }

    public String getACT_UDF_COMCODE() {
        return ACT_UDF_COMCODE;
    }

    public void setACT_UDF_COMCODE(String ACT_UDF_COMCODE) {
        this.ACT_UDF_COMCODE = ACT_UDF_COMCODE == null ? null : ACT_UDF_COMCODE.trim();
    }
}