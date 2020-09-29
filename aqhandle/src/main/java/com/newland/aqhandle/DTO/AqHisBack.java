package com.newland.aqhandle.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "user_aq_his_back")
public class AqHisBack {
    @Id
    private Integer USER_AQ_HIS_BACK_ID;

    private Integer OPERATOR_ID;//aq历史表关联新插入的operator_oa的operator_id

    private Integer BACK_ORG_ID;

    private String AQ_TYPE;
    @Column(name = "USR_KEY")
    private String USRKEY;

    private String ACT_KEY;

    private String USR_LAST_NAME;

    private String USR_FIRST_NAME;

    private String USR_TYPE;

    private String USR_PASSWORD;

    private String USR_STATUS;

    private String USR_EMP_TYPE;

    private String USR_LOGIN;

    private String USR_EMAIL;

    private String USR_LOCKED;

    private String USR_CHANGE_PWD_AT_NEXT_LOGON;

    private String USR_UDF_DEPARTMENT;

    private String USR_UDF_EMPLOYEE_ID;

    private String USR_UDF_SHORT_NAME;

    private String USR_CREATED;

    private String USR_PROVISIONED_DATE;

    private Date USR_CREATE;

    private Date USR_UPDATE;

    private String USR_LOGIN_ATTEMPTS_CTR;

    private String USR_PWD_RESET_ATTEMPTS_CTR;

    private String ACT_NAME;

    private String USR_UDF_MOBILE;

    private String ACT_UDF_NUM;

    private String USR_UDF_OA_ORG;

    private String ORG_NAME;

    private String USR_UDF_OA_ORG2;

    private String ORG_NAME2;

    private String USR_UDF_ID_NUM;

    private String USR_UDF_SEX;

    private String USR_UDF_JOB_LEVEL;

    private String USR_UDF_JOB_LEVEL2;

    private String USR_UDF_ORG_ORDER;

    private String USR_UDF_ORG_ORDER2;

    private Date USR_END_DATE;

    private String USR_UDF_OA_NUM;

    private String USR_UDF_SYS_MASK;

    private String USR_UDF_BELONG_COMP;

    private String USR_UDF_MGR_ERP_ORG;

    private String CARD_INFO;

    private String XIUGAI_YANMA;

    private String AQ_VERSION;

    private Date INSURE_TIME;

    public Date getINSURE_TIME () {
        return INSURE_TIME;
    }

    public void setINSURE_TIME (Date INSURE_TIME) {
        this.INSURE_TIME = INSURE_TIME;
    }

    public Integer getUSER_AQ_HIS_BACK_ID () {
        return USER_AQ_HIS_BACK_ID;
    }

    public void setUSER_AQ_HIS_BACK_ID (Integer USER_AQ_HIS_BACK_ID) {
        this.USER_AQ_HIS_BACK_ID = USER_AQ_HIS_BACK_ID;
    }

    public Integer getOPERATOR_ID () {
        return OPERATOR_ID;
    }

    public void setOPERATOR_ID (Integer OPERATOR_ID) {
        this.OPERATOR_ID = OPERATOR_ID;
    }

    public Integer getBACK_ORG_ID () {
        return BACK_ORG_ID;
    }

    public void setBACK_ORG_ID (Integer BACK_ORG_ID) {
        this.BACK_ORG_ID = BACK_ORG_ID;
    }

    public String getAQ_TYPE () {
        return AQ_TYPE;
    }

    public void setAQ_TYPE (String AQ_TYPE) {
        this.AQ_TYPE = AQ_TYPE;
    }

    public String getUSRKEY () {
        return USRKEY;
    }

    public void setUSRKEY (String USRKEY) {
        this.USRKEY = USRKEY;
    }

    public String getACT_KEY() {
        return ACT_KEY;
    }

    public void setACT_KEY(String ACT_KEY) {
        this.ACT_KEY = ACT_KEY == null ? null : ACT_KEY.trim();
    }

    public String getUSR_LAST_NAME() {
        return USR_LAST_NAME;
    }

    public void setUSR_LAST_NAME(String USR_LAST_NAME) {
        this.USR_LAST_NAME = USR_LAST_NAME == null ? null : USR_LAST_NAME.trim();
    }

    public String getUSR_FIRST_NAME() {
        return USR_FIRST_NAME;
    }

    public void setUSR_FIRST_NAME(String USR_FIRST_NAME) {
        this.USR_FIRST_NAME = USR_FIRST_NAME == null ? null : USR_FIRST_NAME.trim();
    }

    public String getUSR_TYPE() {
        return USR_TYPE;
    }

    public void setUSR_TYPE(String USR_TYPE) {
        this.USR_TYPE = USR_TYPE == null ? null : USR_TYPE.trim();
    }

    public String getUSR_PASSWORD() {
        return USR_PASSWORD;
    }

    public void setUSR_PASSWORD(String USR_PASSWORD) {
        this.USR_PASSWORD = USR_PASSWORD == null ? null : USR_PASSWORD.trim();
    }

    public String getUSR_STATUS() {
        return USR_STATUS;
    }

    public void setUSR_STATUS(String USR_STATUS) {
        this.USR_STATUS = USR_STATUS == null ? null : USR_STATUS.trim();
    }

    public String getUSR_EMP_TYPE() {
        return USR_EMP_TYPE;
    }

    public void setUSR_EMP_TYPE(String USR_EMP_TYPE) {
        this.USR_EMP_TYPE = USR_EMP_TYPE == null ? null : USR_EMP_TYPE.trim();
    }

    public String getUSR_LOGIN() {
        return USR_LOGIN;
    }

    public void setUSR_LOGIN(String USR_LOGIN) {
        this.USR_LOGIN = USR_LOGIN == null ? null : USR_LOGIN.trim();
    }

    public String getUSR_EMAIL() {
        return USR_EMAIL;
    }

    public void setUSR_EMAIL(String USR_EMAIL) {
        this.USR_EMAIL = USR_EMAIL == null ? null : USR_EMAIL.trim();
    }

    public String getUSR_LOCKED() {
        return USR_LOCKED;
    }

    public void setUSR_LOCKED(String USR_LOCKED) {
        this.USR_LOCKED = USR_LOCKED == null ? null : USR_LOCKED.trim();
    }

    public String getUSR_CHANGE_PWD_AT_NEXT_LOGON() {
        return USR_CHANGE_PWD_AT_NEXT_LOGON;
    }

    public void setUSR_CHANGE_PWD_AT_NEXT_LOGON(String USR_CHANGE_PWD_AT_NEXT_LOGON) {
        this.USR_CHANGE_PWD_AT_NEXT_LOGON = USR_CHANGE_PWD_AT_NEXT_LOGON == null ? null : USR_CHANGE_PWD_AT_NEXT_LOGON.trim();
    }

    public String getUSR_UDF_DEPARTMENT() {
        return USR_UDF_DEPARTMENT;
    }

    public void setUSR_UDF_DEPARTMENT(String USR_UDF_DEPARTMENT) {
        this.USR_UDF_DEPARTMENT = USR_UDF_DEPARTMENT == null ? null : USR_UDF_DEPARTMENT.trim();
    }

    public String getUSR_UDF_EMPLOYEE_ID() {
        return USR_UDF_EMPLOYEE_ID;
    }

    public void setUSR_UDF_EMPLOYEE_ID(String USR_UDF_EMPLOYEE_ID) {
        this.USR_UDF_EMPLOYEE_ID = USR_UDF_EMPLOYEE_ID == null ? null : USR_UDF_EMPLOYEE_ID.trim();
    }

    public String getUSR_UDF_SHORT_NAME() {
        return USR_UDF_SHORT_NAME;
    }

    public void setUSR_UDF_SHORT_NAME(String USR_UDF_SHORT_NAME) {
        this.USR_UDF_SHORT_NAME = USR_UDF_SHORT_NAME == null ? null : USR_UDF_SHORT_NAME.trim();
    }

    public String getUSR_CREATED() {
        return USR_CREATED;
    }

    public void setUSR_CREATED(String USR_CREATED) {
        this.USR_CREATED = USR_CREATED == null ? null : USR_CREATED.trim();
    }

    public String getUSR_PROVISIONED_DATE() {
        return USR_PROVISIONED_DATE;
    }

    public void setUSR_PROVISIONED_DATE(String USR_PROVISIONED_DATE) {
        this.USR_PROVISIONED_DATE = USR_PROVISIONED_DATE == null ? null : USR_PROVISIONED_DATE.trim();
    }

    public Date getUSR_CREATE() {
        return USR_CREATE;
    }

    public void setUSR_CREATE(Date USR_CREATE) {
        this.USR_CREATE = USR_CREATE;
    }

    public Date getUSR_UPDATE() {
        return USR_UPDATE;
    }

    public void setUSR_UPDATE(Date USR_UPDATE) {
        this.USR_UPDATE = USR_UPDATE;
    }

    public String getUSR_LOGIN_ATTEMPTS_CTR() {
        return USR_LOGIN_ATTEMPTS_CTR;
    }

    public void setUSR_LOGIN_ATTEMPTS_CTR(String USR_LOGIN_ATTEMPTS_CTR) {
        this.USR_LOGIN_ATTEMPTS_CTR = USR_LOGIN_ATTEMPTS_CTR == null ? null : USR_LOGIN_ATTEMPTS_CTR.trim();
    }

    public String getUSR_PWD_RESET_ATTEMPTS_CTR() {
        return USR_PWD_RESET_ATTEMPTS_CTR;
    }

    public void setUSR_PWD_RESET_ATTEMPTS_CTR(String USR_PWD_RESET_ATTEMPTS_CTR) {
        this.USR_PWD_RESET_ATTEMPTS_CTR = USR_PWD_RESET_ATTEMPTS_CTR == null ? null : USR_PWD_RESET_ATTEMPTS_CTR.trim();
    }

    public String getACT_NAME() {
        return ACT_NAME;
    }

    public void setACT_NAME(String ACT_NAME) {
        this.ACT_NAME = ACT_NAME == null ? null : ACT_NAME.trim();
    }

    public String getUSR_UDF_MOBILE() {
        return USR_UDF_MOBILE;
    }

    public void setUSR_UDF_MOBILE(String USR_UDF_MOBILE) {
        this.USR_UDF_MOBILE = USR_UDF_MOBILE == null ? null : USR_UDF_MOBILE.trim();
    }

    public String getACT_UDF_NUM() {
        return ACT_UDF_NUM;
    }

    public void setACT_UDF_NUM(String ACT_UDF_NUM) {
        this.ACT_UDF_NUM = ACT_UDF_NUM == null ? null : ACT_UDF_NUM.trim();
    }

    public String getUSR_UDF_OA_ORG() {
        return USR_UDF_OA_ORG;
    }

    public void setUSR_UDF_OA_ORG(String USR_UDF_OA_ORG) {
        this.USR_UDF_OA_ORG = USR_UDF_OA_ORG == null ? null : USR_UDF_OA_ORG.trim();
    }

    public String getORG_NAME() {
        return ORG_NAME;
    }

    public void setORG_NAME(String ORG_NAME) {
        this.ORG_NAME = ORG_NAME == null ? null : ORG_NAME.trim();
    }

    public String getUSR_UDF_OA_ORG2() {
        return USR_UDF_OA_ORG2;
    }

    public void setUSR_UDF_OA_ORG2(String USR_UDF_OA_ORG2) {
        this.USR_UDF_OA_ORG2 = USR_UDF_OA_ORG2 == null ? null : USR_UDF_OA_ORG2.trim();
    }

    public String getORG_NAME2() {
        return ORG_NAME2;
    }

    public void setORG_NAME2(String ORG_NAME2) {
        this.ORG_NAME2 = ORG_NAME2 == null ? null : ORG_NAME2.trim();
    }

    public String getUSR_UDF_ID_NUM() {
        return USR_UDF_ID_NUM;
    }

    public void setUSR_UDF_ID_NUM(String USR_UDF_ID_NUM) {
        this.USR_UDF_ID_NUM = USR_UDF_ID_NUM == null ? null : USR_UDF_ID_NUM.trim();
    }

    public String getUSR_UDF_SEX() {
        return USR_UDF_SEX;
    }

    public void setUSR_UDF_SEX(String USR_UDF_SEX) {
        this.USR_UDF_SEX = USR_UDF_SEX == null ? null : USR_UDF_SEX.trim();
    }

    public String getUSR_UDF_JOB_LEVEL() {
        return USR_UDF_JOB_LEVEL;
    }

    public void setUSR_UDF_JOB_LEVEL(String USR_UDF_JOB_LEVEL) {
        this.USR_UDF_JOB_LEVEL = USR_UDF_JOB_LEVEL == null ? null : USR_UDF_JOB_LEVEL.trim();
    }

    public String getUSR_UDF_JOB_LEVEL2() {
        return USR_UDF_JOB_LEVEL2;
    }

    public void setUSR_UDF_JOB_LEVEL2(String USR_UDF_JOB_LEVEL2) {
        this.USR_UDF_JOB_LEVEL2 = USR_UDF_JOB_LEVEL2 == null ? null : USR_UDF_JOB_LEVEL2.trim();
    }

    public String getUSR_UDF_ORG_ORDER() {
        return USR_UDF_ORG_ORDER;
    }

    public void setUSR_UDF_ORG_ORDER(String USR_UDF_ORG_ORDER) {
        this.USR_UDF_ORG_ORDER = USR_UDF_ORG_ORDER == null ? null : USR_UDF_ORG_ORDER.trim();
    }

    public String getUSR_UDF_ORG_ORDER2() {
        return USR_UDF_ORG_ORDER2;
    }

    public void setUSR_UDF_ORG_ORDER2(String USR_UDF_ORG_ORDER2) {
        this.USR_UDF_ORG_ORDER2 = USR_UDF_ORG_ORDER2 == null ? null : USR_UDF_ORG_ORDER2.trim();
    }

    public Date getUSR_END_DATE() {
        return USR_END_DATE;
    }

    public void setUSR_END_DATE(Date USR_END_DATE) {
        this.USR_END_DATE = USR_END_DATE;
    }

    public String getUSR_UDF_OA_NUM() {
        return USR_UDF_OA_NUM;
    }

    public void setUSR_UDF_OA_NUM(String USR_UDF_OA_NUM) {
        this.USR_UDF_OA_NUM = USR_UDF_OA_NUM == null ? null : USR_UDF_OA_NUM.trim();
    }

    public String getUSR_UDF_SYS_MASK() {
        return USR_UDF_SYS_MASK;
    }

    public void setUSR_UDF_SYS_MASK(String USR_UDF_SYS_MASK) {
        this.USR_UDF_SYS_MASK = USR_UDF_SYS_MASK == null ? null : USR_UDF_SYS_MASK.trim();
    }

    public String getUSR_UDF_BELONG_COMP() {
        return USR_UDF_BELONG_COMP;
    }

    public void setUSR_UDF_BELONG_COMP(String USR_UDF_BELONG_COMP) {
        this.USR_UDF_BELONG_COMP = USR_UDF_BELONG_COMP == null ? null : USR_UDF_BELONG_COMP.trim();
    }

    public String getUSR_UDF_MGR_ERP_ORG() {
        return USR_UDF_MGR_ERP_ORG;
    }

    public void setUSR_UDF_MGR_ERP_ORG(String USR_UDF_MGR_ERP_ORG) {
        this.USR_UDF_MGR_ERP_ORG = USR_UDF_MGR_ERP_ORG == null ? null : USR_UDF_MGR_ERP_ORG.trim();
    }

    public String getCARD_INFO() {
        return CARD_INFO;
    }

    public void setCARD_INFO(String CARD_INFO) {
        this.CARD_INFO = CARD_INFO == null ? null : CARD_INFO.trim();
    }

    public String getXIUGAI_YANMA() {
        return XIUGAI_YANMA;
    }

    public void setXIUGAI_YANMA(String XIUGAI_YANMA) {
        this.XIUGAI_YANMA = XIUGAI_YANMA == null ? null : XIUGAI_YANMA.trim();
    }

    public String getAQ_VERSION() {
        return AQ_VERSION;
    }

    public void setAQ_VERSION(String AQ_VERSION) {
        this.AQ_VERSION = AQ_VERSION == null ? null : AQ_VERSION.trim();
    }
}