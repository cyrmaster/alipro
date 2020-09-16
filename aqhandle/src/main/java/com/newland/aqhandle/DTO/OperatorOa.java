package com.newland.aqhandle.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name = "operator_oa")
public class OperatorOa {
    @Id
    private Integer operatorId=null;

    private String name=null;

    private String passwd=null;

    @Column(name="PASSWD_CHANGE_INTERVAL")
    private Integer passwdChangeIntegererval=null;

    @Column(name="LAST_PASSWD_CHANGE_TIME")
    private Date lastPasswdChangeTime=null;

    private Integer status=null;

    @Column(name="IDENTIFY_ID")
    private String identifyId=null;

    @Column(name="INURE_TIME")
    private Date inureTime=null;

    @Column(name="EXPIRE_TIME")
    private Date expireTime=null;

    @Column(name="LAST_LOGIN_TIME")
    private Date lastLogIntegerime=null;

    @Column(name="LAST_LOGIN_SEQ")
    private Long lastLoginSeq=null;

    @Column(name="PASSWD_REPEAT_CNT")
    private Integer passwdRepeatCnt=null;

    @Column(name="LOCK_FLAG")
    private Integer lockFlag=null;

    @Column(name="OPERATOR_ALIAS")
    private String operatorAlias=null;

    private Integer gender=null;

    private Integer headship=null;

    @Column(name="HEADSHIP_DESC")
    private String headshipDesc=null;

    private Date birthday=null;

    @Column(name="IC_TYPE")
    private Integer icType=null;

    @Column(name="IC_NO")
    private String icNo=null;

    @Column(name="JOIN_JOB_DATE")
    private Date joinJobDate=null;

    @Column(name="POLITICS_FACE")
    private Integer politicsFace=null;

    @Column(name="EDUCATE_LEVEL")
    private Integer educateLevel=null;

    @Column(name="MAJOR_SPECIALITY")
    private String majorSpeciality=null;

    @Column(name="RECV_MSG_TYPE")
    private Integer recvMsgType=null;

    private Long msisdn=null;

    @Column(name="E_MAIL")
    private String eMail=null;

    private String nation=null;

    private String description=null;

    @Column(name="MODIFY_OPERATOR_ID")
    private Integer modifyOperatorId=null;

    @Column(name="MODIFY_TIME")
    private Date modifyTime=null;

    @Column(name="MODIFY_CONTENT")
    private String modifyContent=null;

    @Column(name="HIS_SEQ_ID")
    private Long hisSeqId=null;

    @Column(name="OA_USER_ID")
    private Long oaUserId=null;

    @Column(name="OA_ACCOUNT_ID")
    private String oaAccountId=null;

    @Column(name="OA_EMPLOYEE_NO")
    private Integer oaEmployeeNo=null;

    public Integer getOperatorId () {
        return operatorId;
    }

    public void setOperatorId (Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getPasswdChangeIntegererval() {
        return passwdChangeIntegererval;
    }

    public void setPasswdChangeIntegererval(Integer passwdChangeIntegererval) {
        this.passwdChangeIntegererval = passwdChangeIntegererval;
    }

    public Date getLastPasswdChangeTime() {
        return lastPasswdChangeTime;
    }

    public void setLastPasswdChangeTime(Date lastPasswdChangeTime) {
        this.lastPasswdChangeTime = lastPasswdChangeTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId == null ? null : identifyId.trim();
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

    public Date getLastLogIntegerime() {
        return lastLogIntegerime;
    }

    public void setLastLogIntegerime(Date lastLogIntegerime) {
        this.lastLogIntegerime = lastLogIntegerime;
    }

    public Long getLastLoginSeq() {
        return lastLoginSeq;
    }

    public void setLastLoginSeq(Long lastLoginSeq) {
        this.lastLoginSeq = lastLoginSeq;
    }

    public Integer getPasswdRepeatCnt() {
        return passwdRepeatCnt;
    }

    public void setPasswdRepeatCnt(Integer passwdRepeatCnt) {
        this.passwdRepeatCnt = passwdRepeatCnt;
    }

    public Integer getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Integer lockFlag) {
        this.lockFlag = lockFlag;
    }

    public String getOperatorAlias() {
        return operatorAlias;
    }

    public void setOperatorAlias(String operatorAlias) {
        this.operatorAlias = operatorAlias == null ? null : operatorAlias.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getHeadship() {
        return headship;
    }

    public void setHeadship(Integer headship) {
        this.headship = headship;
    }

    public String getHeadshipDesc() {
        return headshipDesc;
    }

    public void setHeadshipDesc(String headshipDesc) {
        this.headshipDesc = headshipDesc == null ? null : headshipDesc.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getIcType() {
        return icType;
    }

    public void setIcType(Integer icType) {
        this.icType = icType;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo == null ? null : icNo.trim();
    }

    public Date getJoinJobDate() {
        return joinJobDate;
    }

    public void setJoinJobDate(Date joinJobDate) {
        this.joinJobDate = joinJobDate;
    }

    public Integer getPoliticsFace() {
        return politicsFace;
    }

    public void setPoliticsFace(Integer politicsFace) {
        this.politicsFace = politicsFace;
    }

    public Integer getEducateLevel() {
        return educateLevel;
    }

    public void setEducateLevel(Integer educateLevel) {
        this.educateLevel = educateLevel;
    }

    public String getMajorSpeciality() {
        return majorSpeciality;
    }

    public void setMajorSpeciality(String majorSpeciality) {
        this.majorSpeciality = majorSpeciality == null ? null : majorSpeciality.trim();
    }

    public Integer getRecvMsgType() {
        return recvMsgType;
    }

    public void setRecvMsgType(Integer recvMsgType) {
        this.recvMsgType = recvMsgType;
    }

    public Long getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(Long msisdn) {
        this.msisdn = msisdn;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail == null ? null : eMail.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Long getOaUserId() {
        return oaUserId;
    }

    public void setOaUserId(Long oaUserId) {
        this.oaUserId = oaUserId;
    }

    public String getOaAccountId() {
        return oaAccountId;
    }

    public void setOaAccountId(String oaAccountId) {
        this.oaAccountId = oaAccountId == null ? null : oaAccountId.trim();
    }

    public Integer getOaEmployeeNo() {
        return oaEmployeeNo;
    }

    public void setOaEmployeeNo(Integer oaEmployeeNo) {
        this.oaEmployeeNo = oaEmployeeNo;
    }

    @Override
    public String toString () {
        return "OperatorOa{" +
                "operatorId='" + operatorId + '\'' +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", passwdChangeIntegererval=" + passwdChangeIntegererval +
                ", lastPasswdChangeTime=" + lastPasswdChangeTime +
                ", status=" + status +
                ", identifyId='" + identifyId + '\'' +
                ", inureTime=" + inureTime +
                ", expireTime=" + expireTime +
                ", lastLogIntegerime=" + lastLogIntegerime +
                ", lastLoginSeq=" + lastLoginSeq +
                ", passwdRepeatCnt=" + passwdRepeatCnt +
                ", lockFlag=" + lockFlag +
                ", operatorAlias='" + operatorAlias + '\'' +
                ", gender=" + gender +
                ", headship=" + headship +
                ", headshipDesc='" + headshipDesc + '\'' +
                ", birthday=" + birthday +
                ", icType=" + icType +
                ", icNo='" + icNo + '\'' +
                ", joinJobDate=" + joinJobDate +
                ", politicsFace=" + politicsFace +
                ", educateLevel=" + educateLevel +
                ", majorSpeciality='" + majorSpeciality + '\'' +
                ", recvMsgType=" + recvMsgType +
                ", msisdn=" + msisdn +
                ", eMail='" + eMail + '\'' +
                ", nation='" + nation + '\'' +
                ", description='" + description + '\'' +
                ", modifyOperatorId=" + modifyOperatorId +
                ", modifyTime=" + modifyTime +
                ", modifyContent='" + modifyContent + '\'' +
                ", hisSeqId=" + hisSeqId +
                ", oaUserId=" + oaUserId +
                ", oaAccountId='" + oaAccountId + '\'' +
                ", oaEmployeeNo=" + oaEmployeeNo +
                '}';
    }
}