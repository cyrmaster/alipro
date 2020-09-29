package com.newland.aqhandle;

import com.newland.aqhandle.DTO.*;
import com.newland.aqhandle.Dao.*;
import com.newland.aqhandle.Util.OracleAqUtil;
import oracle.AQ.AQException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith (SpringRunner.class)
@SpringBootTest(classes = {AqhandleApplication.class})
class AqhandleApplicationTests {
    Logger logger = LoggerFactory.getLogger (AqhandleApplicationTests.class);

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
    OracleAqUtil oracleAqUtil;
    @Autowired
    OaAqHisBackRepository oaAqHisBackRepository;
    private Date a;


    @Test
    void contextLoads () {
        String str = "U|35825|110000017441802|李莉香|李莉香|End-User|3FB4A95D673C738BE9CE487C259A35C6246786F6836F2AB28F1159F9C6DDEC29326BE6D2952B93E4F01DE3E124AADD4F49E63C142E86EA869614FC06754F0E0B|Disabled|Full-Time|LILIXIANG1|lilixiang1@fj.chinamobile.com|0|0||24024902|LILIXIANG1|2011-03-14 17:53:06|2011-03-14 17:53:04|2011-03-14 17:53:04|2020-06-03 09:37:22|0|0|福建公司-直属中心-网络服务室|13960761216|00240090000000030000|00240098000300060000|福建中移通信技术工程有限公司\\维护部\\数据支撑室|;;;;;||350525198210265340|F|P|;;;;;|12|;;;;;|2500-01-01|中移账申[2015]12号||||;|10000001000000000001000000000000000000000|V2.3";
        String[] reult = str.split ("\\|");
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
                OrganizationVirtualOa organizationVirtualOaTemp = organizationVirtualOaRepository.save (organizationVirtualOa);
                if (organizationVirtualOaTemp != null) {
                    logger.info ("organization_oa_virtual-- 机构虚拟表插入成功，organization_oa_virtual=:" + organizationVirtualOaTemp.toString ());
                    aqhisBackOrgId = organizationVirtualOaTemp.getBackOrgId ();
                } else {
                    logger.info ("organization_oa_virtual-- 机构虚拟表插入失败，organization_oa_virtual=:" + organizationVirtualOa.toString ());
                }
            }
            //插表操作完成，备份aq消息入历史表
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("YYYYMMDD");//注意月份是MM
            AqHisBack aqHisBack = new AqHisBack ();
            aqHisBack.setUSER_AQ_HIS_BACK_ID (7);
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
            aqHisBack.setUSR_CREATE (new Date ());
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
            AqHisBack aqHisBackTemp = aqHisBackRepository.save (aqHisBack);
            if (aqHisBackTemp != null) {
                logger.info ("aq_his_back-- AQ数据备份表插入成功，aq_his_back=:" + aqHisBackTemp.toString ());
            } else {
                logger.info ("aq_his_back-- AQ数据备份表插入失败，aq_his_back=:" + aqHisBack.toString ());
            }

        } catch (Exception e) {
            logger.error ("aq消息搬表失败：" + e.toString ());
        }
    }

    @Test
    @Transactional
    public void test1 ()
    {
      /* AqHisBack aqHisBackTemp=aqHisBackRepository.findByUSRKEY ("35825");
        if(aqHisBackTemp.getOPERATOR_ID ()!=null)
        {
            OperatorOa operatorOa = new OperatorOa ();
            operatorOa.setOperatorId (aqHisBackTemp.getOPERATOR_ID ());
            operatorOa.setName ("");
            operatorOa.setPasswd (reult[6]);
            operatorOa.setMsisdn (Long.parseLong (reult[23]));
            operatorOa.seteMail (reult[10]);
            OperatorOa operatorOaTemp = operatorOaRepository.save (operatorOa);
            if (operatorOaTemp != null) {
                logger.info ("operator_oa-- 操作员表更新成功，operator_oa=:" + operatorOaTemp.toString ());
            } else {
                logger.info ("operator_oa-- 操作员表更新失败，operator_oa=:" + operatorOa.toString ());
            }
        }*/
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat ("YYYYMMDD");//注意月份是MM

            String a = simpleDateFormat.format (new Date ());
        System.out.println (java.sql.Date.valueOf (a));
    }

}