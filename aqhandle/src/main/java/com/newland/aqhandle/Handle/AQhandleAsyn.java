package com.newland.aqhandle.Handle;

import com.alibaba.druid.sql.ast.SQLPartitionValue;
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

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    @Async("taskExecutor")
    public void execteAsync () {
        logger.info ("start executeAsync" + "--当前运行的线程名称：" + Thread.currentThread ().getName ());
        OracleAqUtil oracleAqUtil = OracleAqUtil.create ();
        try {
            oracleAqUtil.initAQ ();
        } catch (ClassNotFoundException e) {
            logger.error (e.toString ());
        } catch (AQException e) {
            logger.error (e.toString ());
        } catch (JMSException e) {
            logger.error (e.toString ());
        }
        while (true) {
            try {
                oracleAqUtil.topicSubscriber=oracleAqUtil.topicSession.createDurableSubscriber (oracleAqUtil.topic,oracleAqUtil.sSubscription);
                oracleAqUtil.topicSubscriber.setMessageListener ((message)->{
                    logger.info ("开始获取aq消息");
                    ObjectMessage aQjmsMessage=(ObjectMessage)message;
                    try {
                        logger.info ("message=:"+aQjmsMessage.getObject ().toString ());
                        String []reult=aQjmsMessage.getObject ().toString ().split ("//|");
                        OrganizationOa organizationOa=organizationOaRepository.findByOrgCode (reult[25]);
                        Integer aqhisOperatorId=null;//aq历史表关联新插入的operator_oa的operator_id
                        Integer aqhisBackOrgId=null;//aq历史表关联新插入的organization_oa的back_org_id
                        if(organizationOa!=null){
                            OperatorOa operatorOa=new OperatorOa ();
                            int city=organizationOa.getHomeCity ()%10;
                            StringBuffer buffer=new StringBuffer ();
                            buffer.append (1);
                            buffer.append (city);
                            buffer.append (String.format ("%06d",Integer.parseInt (operatorOaRepository.getNextval ())));
                            operatorOa.setOperatorId (Integer.parseInt (buffer.toString ()));
                            operatorOa.setName (reult[3]);
                            operatorOa.setPasswd (reult[6]);
                            operatorOa.setMsisdn (Long.parseLong (reult[23]));
                            operatorOa.seteMail (reult[10]);
                            OperatorOa operatorOaTemp=operatorOaRepository.save (operatorOa);
                            if(operatorOaTemp!=null)
                            {
                                logger.info ("operator_oa-- 操作员表插入成功，operator_oa=:"+operatorOaTemp.toString ());
                                aqhisOperatorId=operatorOaTemp.getOperatorId ();
                            }else {
                                logger.info ("operator_oa-- 操作员表插入失败，operator_oa=:"+operatorOa.toString ());
                            }
                            OperatorOrganOa operatorOrganOa=new OperatorOrganOa ();
                            operatorOrganOa.setOperatorId (operatorOaTemp.getOperatorId ());
                            operatorOrganOa.setOaOrgId (Integer.parseInt (organizationOa.getOrgCode ()));
                            OperatorOrganOa operatorOrganOaTemp=opOragnOaRepository.save (operatorOrganOa);
                            if(operatorOrganOaTemp!=null)
                            {
                                logger.info ("operator_organ_oa-- 操作员机构关系表插入成功，operator_oa=:"+operatorOrganOaTemp.toString ());
                            }else {
                                logger.info ("operator_organ_oa-- 操作员机构关系表插入失败，operator_oa=:"+operatorOrganOa.toString ());
                            }
                        }else {
                            OrganizationVirtualOa organizationVirtualOa=new OrganizationVirtualOa ();
                            StringBuffer buffer=new StringBuffer ();
                            buffer.append (1);
                            buffer.append (String.format ("%07d",Integer.parseInt (organizationVirtualOaRepository.getNextval ())));
                            organizationVirtualOa.setBackOrgId (Integer.parseInt (buffer.toString ()));
                            organizationVirtualOa.setName (reult[3]);
                            organizationVirtualOa.setOrgProperty (1);
                            organizationVirtualOa.setOrgCode (reult[25]);
                            OrganizationVirtualOa organizationVirtualOaTemp=organizationVirtualOaRepository.save (organizationVirtualOa);
                            if(organizationVirtualOaTemp!=null)
                            {
                                logger.info ("organization_oa_virtual-- 机构虚拟表插入成功，organization_oa_virtual=:"+organizationVirtualOaTemp.toString ());
                                aqhisBackOrgId=organizationVirtualOaTemp.getBackOrgId ();
                            }else {
                                logger.info ("organization_oa_virtual-- 机构虚拟表插入失败，organization_oa_virtual=:"+organizationVirtualOa.toString ());
                            }
                        }
                        //插表操作完成，备份aq消息入历史表
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDD");//注意月份是MM
                        AqHisBack aqHisBack=new AqHisBack ();
                        aqHisBack.setOPERATOR_ID (aqhisOperatorId);
                        aqHisBack.setBACK_ORG_ID (aqhisBackOrgId);
                        aqHisBack.setU (reult[0]);
                        aqHisBack.setUSR_KEY (reult[1]);
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
                        AqHisBack aqHisBackTemp=aqHisBackRepository.save (aqHisBack);
                        if(aqHisBackTemp!=null)
                        {
                            logger.info ("aq_his_back-- AQ数据备份表插入成功，aq_his_back=:"+aqHisBackTemp.toString ());
                        }else {
                            logger.info ("aq_his_back-- AQ数据备份表插入失败，aq_his_back=:"+aqHisBack.toString ());
                        }
                    } catch (Exception e) {
                        logger.error ("aq消息搬表失败："+e.toString ());
                    }
                });
            } catch (JMSException e) {
               logger.debug ("获取aq消息失败："+e.toString ());
            }

        }
    }
}
