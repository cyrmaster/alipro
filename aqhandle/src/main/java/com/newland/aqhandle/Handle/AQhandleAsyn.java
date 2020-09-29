package com.newland.aqhandle.Handle;

import com.alibaba.druid.sql.ast.SQLPartitionValue;
import com.newland.aqhandle.AqhandleApplication;
import com.newland.aqhandle.DTO.*;
import com.newland.aqhandle.Dao.*;
import com.newland.aqhandle.Util.OracleAqUtil;
import oracle.AQ.AQException;
import oracle.jms.AQjmsMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class AQhandleAsyn {
    private static final Logger logger= LoggerFactory.getLogger (AQhandleAsyn.class);

    @Autowired
    OrganizationOaRepository organizationOaRepository;
    @Autowired
    OperatorOaRepository operatorOaRepository;
    @Autowired
    OperatorOragnOaRepository opOragnOaRepository;
    @Autowired
    OrganizationVirtualOaRepository organizationVirtualOaRepository;
    @Autowired
    AqHisBackRepository aqHisBackRepository;
    @Autowired
    HrAqHisBackRepository hrAqHisBackRepository;
    @Autowired
    OaAqHisBackRepository oaAqHisBackRepository;
    @Autowired
    OracleAqUtil oracleAqUtil;

    private  String userRegex="[CU]";//修改用户，修改密码，消息类型
    private  String oaRegex="[yz]";//oa消息类型
    private  String hrRegex="[nud]";//hr消息类型
    private static final List<ObjectMessage> taskQueue = Collections.synchronizedList(new LinkedList<ObjectMessage> ());

    public static List<ObjectMessage> getTaskQueue () {
        return taskQueue;
    }
    public static ObjectMessage getQueue()
    {
        if(taskQueue.remove (0)==null){
            try {
                Thread.currentThread ().sleep(5);
            } catch (InterruptedException e) {
                logger.error (e.toString ());
            }
        }
        return taskQueue.remove (0);
    }


    /*
    * 接受aq消息异步方法
    * */
    @Async("aqgetexecutor")
    @Transactional
    public void getMessage()
    {
        logger.info ("开始接受aq的线程" + "--当前运行的线程名称：" + Thread.currentThread ().getName ());
        while (true) {
            try {
                oracleAqUtil.topicSubscriber=oracleAqUtil.topicSession.createDurableSubscriber (oracleAqUtil.topic,oracleAqUtil.sSubscription);
                oracleAqUtil.topicSubscriber.setMessageListener ((message)-> {
                    logger.info ("开始获取aq消息");
                    ObjectMessage aQjmsMessage = (ObjectMessage) message;
                    taskQueue.add (aQjmsMessage);
                });
            }
            catch (Exception e) {
                logger.debug ("获取aq消息失败："+e.toString ());
            }
            try {
                Thread.currentThread ().sleep(5);
            } catch (InterruptedException e) {
                logger.error (e.toString ());
            }
        }
    }


/*
* aq搬表线程业务方法
* */
    @Async("taskExecutor")
    @Transactional
    public void execteAsync (ObjectMessage objectMessage) {
        logger.info ("开始处理消息线程" + "--当前运行的线程名称：" + Thread.currentThread ().getName ());
        logger.info ("开始获取aq消息");
        String[] reult = new String[0];
        try {
            logger.info ("message=:" + objectMessage.getObject ().toString ());
            reult = objectMessage.getObject ().toString ().split ("//|");
        } catch (JMSException e) {
            logger.error ("转换aq消息错误",e.toString ());
        }
        //业务处理，根据不同消息类型处理
        if (reult[0].trim ().equals ("N")) {
            logger.info ("AqType=" + reult[0] + "为新增用户型消息，开始-处理aq消息");
            addUserAqHandle (reult);
            logger.info ("AqType=" + reult[0] + "为新增用户型消息，成功-处理aq消息");
        }
        if (Pattern.matches (userRegex, reult[0])) {
            logger.info ("AqType=" + reult[0] + "为修改用户，修改密码型消息，开始-处理aq消息");
            updateUserAqHandle (reult);
            logger.info ("AqType=" + reult[0] + "为修改用户，修改密码型消息，结束-处理aq消息");
        }
        if (Pattern.matches (hrRegex, reult[0])) {
            logger.info ("AqType=" + reult[0] + "为新增，修改，删除hr组织型消息，开始-处理aq消息");
            hrAqHandle (reult);
            logger.info ("AqType=" + reult[0] + "为新增，修改，删除hr组织型消息，结束-处理aq消息");
        }
        if (Pattern.matches (oaRegex, reult[0])) {
            logger.info ("AqType=" + reult[0] + "为新增，修改，删除oa组织型消息，开始-处理aq消息");
            oaAqHandle (reult);
            logger.info ("AqType=" + reult[0] + "为新增，修改，删除oa组织型消息，结束-处理aq消息");
        }
        try {
            objectMessage.acknowledge ();//所有流程处理完，确认收到消息。
        } catch (JMSException e) {
            logger.error ("消息应答失败", e.toString ());
        }
        try {
            Thread.currentThread ().sleep(5);
        } catch (InterruptedException e) {
            logger.error (e.toString ());
        }
    }
    /*
    * 新增用户型消息处理函数
    * */
    public void addUserAqHandle(String reult[])
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("YYYYMMDD");//注意月份是MM
        try {
            OrganizationOa organizationOa = organizationOaRepository.findByOrgCode (reult[25]);
            Integer aqhisOperatorId = null;//aq历史表关联新插入的operator_oa的operator_id
            Integer aqhisBackOrgId = null;//aq历史表关联新插入的organization_oa的back_org_id
            if (organizationOa != null) {
                OperatorOa operatorOa = new OperatorOa ();
                int city = organizationOa.getHomeCity () % 10;
                StringBuffer buffer = new StringBuffer ();
                buffer.append (1);
                buffer.append (city);
                buffer.append (String.format ("%06d", Integer.parseInt (operatorOaRepository.getNextval ())));
                operatorOa.setOperatorId (Integer.parseInt (buffer.toString ()));
                operatorOa.setName (reult[3]);
                operatorOa.setPasswd (reult[6]);
                operatorOa.setMsisdn (Long.parseLong (reult[23]));
                operatorOa.seteMail (reult[10]);
                operatorOa.setInureTime (new Date ());
                OperatorOa operatorOaTemp = operatorOaRepository.save (operatorOa);
                if (operatorOaTemp != null) {
                    logger.info ("operator_oa-- 操作员表插入成功，operator_oa=:" + operatorOaTemp.toString ());
                    aqhisOperatorId = operatorOaTemp.getOperatorId ();
                } else {
                    logger.info ("operator_oa-- 操作员表插入失败，operator_oa=:" + operatorOa.toString ());
                }
                OperatorOrganOa operatorOrganOa = new OperatorOrganOa ();
                operatorOrganOa.setOperatorId (operatorOaTemp.getOperatorId ());
                operatorOrganOa.setOaOrgId (Integer.parseInt (organizationOa.getOrgCode ()));
                operatorOrganOa.setModifyTime (new Date ());
                OperatorOrganOa operatorOrganOaTemp = opOragnOaRepository.save (operatorOrganOa);
                if (operatorOrganOaTemp != null) {
                    logger.info ("operator_organ_oa-- 操作员机构关系表插入成功，operator_oa=:" + operatorOrganOaTemp.toString ());
                } else {
                    logger.info ("operator_organ_oa-- 操作员机构关系表插入失败，operator_oa=:" + operatorOrganOa.toString ());
                }
            } else {
                OrganizationVirtualOa organizationVirtualOa = new OrganizationVirtualOa ();
                StringBuffer buffer = new StringBuffer ();
                buffer.append (1);
                buffer.append (String.format ("%07d", Integer.parseInt (organizationVirtualOaRepository.getNextval ())));
                organizationVirtualOa.setBackOrgId (Integer.parseInt (buffer.toString ()));
                organizationVirtualOa.setName (reult[3]);
                organizationVirtualOa.setOrgProperty (1);
                organizationVirtualOa.setOrgCode (reult[25]);
                organizationVirtualOa.setInureTime (new Date ());
                OrganizationVirtualOa organizationVirtualOaTemp = organizationVirtualOaRepository.save (organizationVirtualOa);
                if (organizationVirtualOaTemp != null) {
                    logger.info ("organization_oa_virtual-- 机构虚拟表插入成功，organization_oa_virtual=:" + organizationVirtualOaTemp.toString ());
                    aqhisBackOrgId = organizationVirtualOaTemp.getBackOrgId ();
                } else {
                    logger.info ("organization_oa_virtual-- 机构虚拟表插入失败，organization_oa_virtual=:" + organizationVirtualOa.toString ());
                }
            }
            //插表操作完成，备份aq消息入历史表
            AqHisBack aqHisBack = new AqHisBack ();
            aqHisBack.setUSER_AQ_HIS_BACK_ID (Integer.parseInt (String.format ("%06d", Integer.parseInt (aqHisBackRepository.getNextval ()))));
            aqHisBack.setOPERATOR_ID (aqhisOperatorId);
            aqHisBack.setBACK_ORG_ID (aqhisBackOrgId);
            aqHisBack.setAQ_TYPE (reult[0]);
            aqHisBack.setUSRKEY (reult[1]);
            aqHisBack.setACT_KEY (reult[2]);
            aqHisBack.setUSR_LAST_NAME (reult[3]);
            aqHisBack.setUSR_FIRST_NAME (reult[4]);
            aqHisBack.setUSR_TYPE (reult[5]);
            aqHisBack.setUSR_PASSWORD (reult[6]);
            aqHisBack.setUSR_STATUS (reult[7]);
            aqHisBack.setUSR_EMP_TYPE (reult[8]);
            aqHisBack.setUSR_LOGIN (reult[9]);
            aqHisBack.setUSR_EMAIL (reult[10]);
            aqHisBack.setUSR_LOCKED (reult[11]);
            aqHisBack.setUSR_CHANGE_PWD_AT_NEXT_LOGON (reult[12]);
            aqHisBack.setUSR_UDF_DEPARTMENT (reult[13]);
            aqHisBack.setUSR_UDF_EMPLOYEE_ID (reult[14]);
            aqHisBack.setUSR_UDF_SHORT_NAME (reult[15]);
            aqHisBack.setUSR_CREATED (reult[16]);
            aqHisBack.setUSR_PROVISIONED_DATE (reult[17]);
            aqHisBack.setUSR_CREATE (simpleDateFormat.parse (reult[18]));
            aqHisBack.setUSR_UPDATE (simpleDateFormat.parse (reult[19]));
            aqHisBack.setUSR_LOGIN_ATTEMPTS_CTR (reult[20]);
            aqHisBack.setUSR_PWD_RESET_ATTEMPTS_CTR (reult[21]);
            aqHisBack.setACT_NAME (reult[22]);
            aqHisBack.setUSR_UDF_MOBILE (reult[23]);
            aqHisBack.setACT_UDF_NUM (reult[24]);
            aqHisBack.setUSR_UDF_OA_ORG (reult[25]);
            aqHisBack.setORG_NAME (reult[26]);
            aqHisBack.setUSR_UDF_OA_ORG2 (reult[27]);
            aqHisBack.setORG_NAME2 (reult[28]);
            aqHisBack.setUSR_UDF_ID_NUM (reult[29]);
            aqHisBack.setUSR_UDF_SEX (reult[30]);
            aqHisBack.setUSR_UDF_JOB_LEVEL (reult[31]);
            aqHisBack.setUSR_UDF_JOB_LEVEL2 (reult[32]);
            aqHisBack.setUSR_UDF_ORG_ORDER (reult[33]);
            aqHisBack.setUSR_UDF_ORG_ORDER2 (reult[34]);
            aqHisBack.setUSR_END_DATE (simpleDateFormat.parse (reult[35]));
            aqHisBack.setUSR_UDF_OA_NUM (reult[36]);
            aqHisBack.setUSR_UDF_SYS_MASK (reult[37]);
            aqHisBack.setUSR_UDF_BELONG_COMP (reult[38]);
            aqHisBack.setUSR_UDF_MGR_ERP_ORG (reult[39]);
            aqHisBack.setCARD_INFO (reult[40]);
            aqHisBack.setXIUGAI_YANMA (reult[41]);
            aqHisBack.setAQ_VERSION (reult[42]);
            aqHisBack.setINSURE_TIME (new Date ());
            AqHisBack aqHisBackTemp = aqHisBackRepository.save (aqHisBack);
            if (aqHisBackTemp != null) {
                logger.info ("user_aq_his_back-- AQ数据备份表插入成功，user_aq_his_back=:" + aqHisBackTemp.toString ());
            } else {
                logger.info ("user_aq_his_back-- AQ数据备份表插入失败，user_aq_his_back=:" + aqHisBack.toString ());
            }
        } catch (Exception e) {
            logger.error ("aq消息搬表失败："+e.toString ());
        }
    }


    /*
    * 修改用户，修改密码型消息处理
    * */
    public void updateUserAqHandle(String reult[])
    {
        AqHisBack aqHisBackTemp=aqHisBackRepository.findByUSRKEY (reult[1]);
        if(aqHisBackTemp!=null)
        {
            OperatorOa operatorOa = new OperatorOa ();
            operatorOa.setOperatorId (aqHisBackTemp.getOPERATOR_ID ());
            operatorOa.setName (reult[3]);
            operatorOa.setPasswd (reult[6]);
            operatorOa.setMsisdn (Long.parseLong (reult[23]));
            operatorOa.seteMail (reult[10]);
            operatorOa.setModifyTime (new Date ());
            OperatorOa operatorOaTemp = operatorOaRepository.save (operatorOa);
            if (operatorOaTemp.getOperatorId ()!= null) {
                logger.info ("operator_oa-- 操作员表更新成功，operator_oa=:" + operatorOaTemp.toString ());
            } else {
                logger.info ("operator_oa-- 操作员表更新失败，operator_oa=:" + operatorOa.toString ());
            }
        }else {
            logger.info ("对应修改操作员用户不存在,传入的消息为："+reult.toString ());
        }
        AqHisBack aqHisBack = null;
        AqHisBack ahb = null;
        try {
            //插表操作完成，备份aq消息入历史表
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("YYYYMMDD");//注意月份是MM
            aqHisBack = new AqHisBack ();
            aqHisBack.setUSER_AQ_HIS_BACK_ID (Integer.parseInt (String.format ("%06d", Integer.parseInt (aqHisBackRepository.getNextval ()))));
            aqHisBack.setOPERATOR_ID (aqHisBackTemp.getOPERATOR_ID ());
            aqHisBack.setAQ_TYPE (reult[0]);
            aqHisBack.setUSRKEY (reult[1]);
            aqHisBack.setACT_KEY (reult[2]);
            aqHisBack.setUSR_LAST_NAME (reult[3]);
            aqHisBack.setUSR_FIRST_NAME (reult[4]);
            aqHisBack.setUSR_TYPE (reult[5]);
            aqHisBack.setUSR_PASSWORD (reult[6]);
            aqHisBack.setUSR_STATUS (reult[7]);
            aqHisBack.setUSR_EMP_TYPE (reult[8]);
            aqHisBack.setUSR_LOGIN (reult[9]);
            aqHisBack.setUSR_EMAIL (reult[10]);
            aqHisBack.setUSR_LOCKED (reult[11]);
            aqHisBack.setUSR_CHANGE_PWD_AT_NEXT_LOGON (reult[12]);
            aqHisBack.setUSR_UDF_DEPARTMENT (reult[13]);
            aqHisBack.setUSR_UDF_EMPLOYEE_ID (reult[14]);
            aqHisBack.setUSR_UDF_SHORT_NAME (reult[15]);
            aqHisBack.setUSR_CREATED (reult[16]);
            aqHisBack.setUSR_PROVISIONED_DATE (reult[17]);
            aqHisBack.setUSR_CREATE (simpleDateFormat.parse (reult[18]));
            aqHisBack.setUSR_UPDATE (simpleDateFormat.parse (reult[19]));
            aqHisBack.setUSR_LOGIN_ATTEMPTS_CTR (reult[20]);
            aqHisBack.setUSR_PWD_RESET_ATTEMPTS_CTR (reult[21]);
            aqHisBack.setACT_NAME (reult[22]);
            aqHisBack.setUSR_UDF_MOBILE (reult[23]);
            aqHisBack.setACT_UDF_NUM (reult[24]);
            aqHisBack.setUSR_UDF_OA_ORG (reult[25]);
            aqHisBack.setORG_NAME (reult[26]);
            aqHisBack.setUSR_UDF_OA_ORG2 (reult[27]);
            aqHisBack.setORG_NAME2 (reult[28]);
            aqHisBack.setUSR_UDF_ID_NUM (reult[29]);
            aqHisBack.setUSR_UDF_SEX (reult[30]);
            aqHisBack.setUSR_UDF_JOB_LEVEL (reult[31]);
            aqHisBack.setUSR_UDF_JOB_LEVEL2 (reult[32]);
            aqHisBack.setUSR_UDF_ORG_ORDER (reult[33]);
            aqHisBack.setUSR_UDF_ORG_ORDER2 (reult[34]);
            aqHisBack.setUSR_END_DATE (simpleDateFormat.parse (reult[35]));
            aqHisBack.setUSR_UDF_OA_NUM (reult[36]);
            aqHisBack.setUSR_UDF_SYS_MASK (reult[37]);
            aqHisBack.setUSR_UDF_BELONG_COMP (reult[38]);
            aqHisBack.setUSR_UDF_MGR_ERP_ORG (reult[39]);
            aqHisBack.setCARD_INFO (reult[40]);
            aqHisBack.setXIUGAI_YANMA (reult[41]);
            aqHisBack.setAQ_VERSION (reult[42]);
            aqHisBack.setINSURE_TIME (new Date ());
            ahb = aqHisBackRepository.save (aqHisBack);
        } catch (Exception e) {
            logger.error ("备份数据user_aq_his_back失败"+e.toString ());
        }
        if (ahb != null) {
            logger.info ("user_aq_his_back-- AQ数据备份表插入成功，user_aq_his_back=:" + ahb.toString ());
        } else {
            logger.info ("user_aq_his_back-- AQ数据备份表插入失败，user_aq_his_back=:" + aqHisBack.toString ());
        }
    }


    /*
    处理oa组织型消息
    * */
    public void oaAqHandle(String result[])
    {
        if(result[0].equals ("x")) {
            OrganizationVirtualOa organizationVirtualOa = new OrganizationVirtualOa ();
            StringBuffer buffer = new StringBuffer ();
            buffer.append (1);
            buffer.append (String.format ("%07d", Integer.parseInt (organizationVirtualOaRepository.getNextval ())));
            organizationVirtualOa.setBackOrgId (Integer.parseInt (buffer.toString ()));
            organizationVirtualOa.setName (result[3]);
            organizationVirtualOa.setOrgProperty (1);
            organizationVirtualOa.setOrgCode (result[2]);
            organizationVirtualOa.setInureTime (new Date ());
            OrganizationVirtualOa organizationVirtualOaTemp = organizationVirtualOaRepository.save (organizationVirtualOa);
            if (organizationVirtualOaTemp != null) {
                logger.info ("organization_oa_virtual-- 机构虚拟表插入成功，organization_oa_virtual=:" + organizationVirtualOaTemp.toString ());
            } else {
                logger.info ("organization_oa_virtual-- 机构虚拟表插入失败，organization_oa_virtual=:" + organizationVirtualOa.toString ());
            }
        }
        if(result[0].equals ("y")) {
            OaAqHisBack oaAqHisBack = oaAqHisBackRepository.findByORGKEY (result[1]);
            if(oaAqHisBack!=null) {
                OrganizationVirtualOa virtualOa = organizationVirtualOaRepository.findByOrgCode (oaAqHisBack.getORG_CODE ());
                OrganizationVirtualOa organizationVirtualOa = new OrganizationVirtualOa ();
                organizationVirtualOa.setBackOrgId (virtualOa.getBackOrgId ());
                organizationVirtualOa.setName (result[3]);
                organizationVirtualOa.setOrgProperty (1);
                organizationVirtualOa.setOrgCode (result[2]);
                organizationVirtualOa.setModifyTime (new Date ());
                OrganizationVirtualOa organizationVirtualOaTemp = organizationVirtualOaRepository.save (organizationVirtualOa);
                if (organizationVirtualOaTemp != null) {
                    logger.info ("organization_oa_virtual-- 机构虚拟表更新成功，organization_oa_virtual=:" + organizationVirtualOaTemp.toString ());
                } else {
                    logger.info ("organization_oa_virtual-- 机构虚拟表更新失败，organization_oa_virtual=:" + organizationVirtualOa.toString ());
                }
            }else {
                logger.info ("对应修改的oa组织不存在,传入的消息为："+result.toString ());
            }
        }
        if(result[0].equals ("d")){
            OaAqHisBack oaAqHisBack = oaAqHisBackRepository.findByORGKEY (result[1]);
            if(oaAqHisBack!=null) {
                OrganizationVirtualOa virtualOa = organizationVirtualOaRepository.findByOrgCode (oaAqHisBack.getORG_CODE ());
                Integer res = organizationVirtualOaRepository.deleteByBackOrgId (virtualOa.getBackOrgId ());
                if (res != null) {
                    logger.info ("organization_oa_virtual-- 机构虚拟表删除" + res + "条成功");
                }
            }else {
                logger.info ("对应删除的oa组织不存在,传入的消息为："+result.toString ());
            }
        }
        //备份历史表，oa_aq_his_back
        try {
            //插表操作完成，备份aq消息入历史表
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("YYYYMMDD");//注意月份是MM
            OaAqHisBack oaAqHisBack=new OaAqHisBack ();
            oaAqHisBack.setAQ_TYPE (result[0]);
            oaAqHisBack.setORGKEY (result[1]);
            oaAqHisBack.setORG_CODE (result[2]);
            oaAqHisBack.setORG_NAME (result[3]);
            oaAqHisBack.setORG_DISABLED (result[4]);
            oaAqHisBack.setORG_CREATE (simpleDateFormat.parse (result[5]));
            oaAqHisBack.setORG_CREATEBY (result[6]);
            oaAqHisBack.setORG_UPDATE (simpleDateFormat.parse (result[7]));
            oaAqHisBack.setORG_UPDATEBY (result[8]);
            oaAqHisBack.setORG_ORDER (result[9]);
            oaAqHisBack.setINSURE_TIME (new Date ());
            OaAqHisBack ohb=oaAqHisBackRepository.save (oaAqHisBack);
            if (ohb != null) {
                logger.info ("oa_aq_his_back-- AQ数据备份表插入成功，oa_aq_his_back=:" + ohb.toString ());
            } else {
                logger.info ("oa_aq_his_back-- AQ数据备份表插入成功，oa_aq_his_back=:" + oaAqHisBack.toString ());
            }
        } catch (Exception e) {
            logger.error ("备份oa_aq_his_back表失败："+e.toString ());
        }
    }



    /*
    处理hr型消息
    * */
    public void hrAqHandle(String result[])
    {
     //hr组织消息目前业务暂无使用，先做备份处理
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("YYYYMMDD");//注意月份是MM
            HrAqHisBack hrAqHisBack=new HrAqHisBack ();
            StringBuffer buffer = new StringBuffer ();
            buffer.append (1);
            buffer.append (String.format ("%07d", Integer.parseInt (hrAqHisBackRepository.getNextval ())));
            hrAqHisBack.setHR_AQ_HIS_BACK_ID (buffer.toString ());
            hrAqHisBack.setAQ_TYPE (result[0]);
            hrAqHisBack.setACT_UDF_NUM (result[1]);
            hrAqHisBack.setACT_NAME (result[2]);
            hrAqHisBack.setACT_KEY (result[3]);
            hrAqHisBack.setACT_CUST_TYPE (result[4]);
            hrAqHisBack.setACT_STATUS (result[5]);
            hrAqHisBack.setACT_CREATE (simpleDateFormat.parse (result[6]));
            hrAqHisBack.setACT_CREATEBY (result[7]);
            hrAqHisBack.setACT_UPDATE (simpleDateFormat.parse(result[8]));
            hrAqHisBack.setACT_UPDATEBY (result[9]);
            hrAqHisBack.setACT_UDF_DSPNAME (result[10]);
            hrAqHisBack.setACT_UDF_FUNC (result[11]);
            hrAqHisBack.setACT_UDF_SUPVSR (result[12]);
            hrAqHisBack.setACT_UDF_MGR (result[13]);
            hrAqHisBack.setACT_UDF_ADDR (result[14]);
            hrAqHisBack.setACT_UDF_ZIP (result[15]);
            hrAqHisBack.setACT_UDF_TEL (result[16]);
            hrAqHisBack.setACT_UDF_FAX (result[17]);
            hrAqHisBack.setACT_UDF_ERPID (result[18]);
            hrAqHisBack.setACT_UDF_LOC (result[19]);
            hrAqHisBack.setPARENT_ACT_UDF_NUM (result[20]);
            hrAqHisBack.setACT_REMARK (result[21]);
            hrAqHisBack.setACT_UDF_COMCODE (result[22]);
            hrAqHisBack.setINSURE_TIME (new Date ());
            HrAqHisBack hrb=hrAqHisBackRepository.save (hrAqHisBack);
            if (hrb != null) {
                logger.info ("hr_aq_his_back-- AQ数据备份表插入成功，hr_aq_his_back=:" + hrb.toString ());
            } else {
                logger.info ("hr_aq_his_back-- AQ数据备份表插入失败，hr_aq_his_back=:" + hrAqHisBack.toString ());
            }
        } catch (Exception e) {
            logger.error ("备份hr_aq_his_back表失败："+e.toString ());
        }
    }

}
