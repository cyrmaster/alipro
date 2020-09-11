package com.newland.aqhandle.Util;

import oracle.AQ.AQDriverManager;
import oracle.AQ.AQException;
import oracle.AQ.AQSession;
import oracle.jms.AQjmsFactory;
import oracle.jms.AQjmsMessage;
import oracle.jms.AQjmsSession;
import oracle.jms.AQjmsTopicConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class OracleAqUtil {

    static final Logger logger= LoggerFactory.getLogger (OracleAqUtil.class);
    @Qualifier("aqDataSource")
    private DataSource dataSource;

    private static AQSession aqSession = null;
    private static TopicConnection topicConnection = null;
    private static TopicSession topicSession = null;
    private static Topic topic = null;
    private static TopicSubscriber topicSubscriber = null;
    private static TopicPublisher topicPublisher = null;
    @Value ("${Aq.schema}")
    private String sSchema=null;
    @Value ("${Aq.queuename}")
    private String sQueueName=null;
    @Value ("${Aq.subscription}")
    private String sSubscription=null;

    public void initAQ ()
            throws ClassNotFoundException, AQException, JMSException {
        ConnectionFactory connectionFactory=AQjmsFactory.getConnectionFactory (dataSource);
        Connection connection=(Connection)connectionFactory.createConnection ();
        this.aqSession = AQDriverManager.createAQSession (connection);
        logger.info ("初始化 AQ 会话，成功");

        topicConnection = AQjmsTopicConnectionFactory
                .createTopicConnection (connection);
        logger.info ("创建TopicConnection，成功");

        topicConnection.start ();
        logger.info ("启动TopicConnection，成功");

        topicSession = topicConnection.createTopicSession (true, 0);
        logger.info ("创建TopicSession，成功");

        topic = ((AQjmsSession) topicSession).getTopic (sSchema, sQueueName);
        logger.info ("获取Topic[" + sSchema + "][" + sQueueName + "]");

        logger.info ("初始化 AQ 连接，成功");
    }
    public void getMessageOnListener()
    {
        try {
            topicSubscriber=topicSession.createDurableSubscriber (topic,sSubscription);
            topicSubscriber.setMessageListener ((message)->{
                    logger.info ("开始获取aq消息");
                    AQjmsMessage aQjmsMessage=(AQjmsMessage)message;
                    logger.info ("message=:"+aQjmsMessage.toString ());
            });
        } catch (JMSException e) {
            e.printStackTrace ();
        }

    }
    public String getMessageFromQueue (long lWaitTime) throws JMSException, SQLException {
        if (sSubscription == null) {
            logger.warn ("未指定订阅者，不能作为消息接收方");
            return null;
        }
        //判断是否有创建过订阅
        if (topicSubscriber == null) {
            //未创建则立即创建
            topicSubscriber = topicSession.createDurableSubscriber (topic, sSubscription);
            logger.debug ("创建持久化订阅[" + sSubscription + "]，成功");
        }
        TextMessage textMessage = null;
        // 传入0表示接收到再返回，大于0表示等待N毫秒后返回
        textMessage = (TextMessage) topicSubscriber.receive (lWaitTime);
        String strMessage = null;
        if (textMessage != null) {
            strMessage = textMessage.getText ();
            logger.info ("接收到的消息[" + strMessage + "]");
            this.topicSession.commit ();
            //this.db_conn.commit();
        } // else {
        // logger.debug("没有接收到消息");
        // }
        return strMessage;
    }

    public boolean sendMessageToQueue (String sMsg) throws JMSException, SQLException {
        //创建消息发布者
        if (topicPublisher == null) {
            //未创建则立即创建
            this.topicPublisher = this.topicSession.createPublisher (this.topic);
            logger.debug ("创建消息发布者，成功");
        }

        TextMessage textMessage = this.topicSession.createTextMessage ();
        //发布到指定消息队列
        textMessage.setJMSCorrelationID (this.sQueueName);
        textMessage.setText (sMsg);
        topicPublisher.publish (textMessage);
        this.topicSession.commit ();
        //this.db_conn.commit();
        logger.info ("发送的消息[" + sMsg + "]");
        return true;
    }
    // isExitUnsubscribe 退出时是否取消订阅
    public void closeAQ (boolean isExitUnsubscribe) {
        try {
            if (topicSubscriber != null) {
                if (isExitUnsubscribe) {
                    // 有指定退出时取消订阅，取消订阅后不会再收到分发给此订阅者的AQ消息
                    if (topicSession != null) {
                        topicSession.unsubscribe (sSubscription);
                        logger.info ("取消持久化订阅[" + sSubscription + "]，成功");
                    } else {
                        logger.warn ("未创建TopicSession，无法取消持久化订阅[" + sSubscription + "]");
                    }
                }
                topicSubscriber.close ();
                topicSubscriber = null;
                logger.info ("关闭TopicSubscriber成功");
            }
            if (topicPublisher != null) {
                topicPublisher.close ();
                topicPublisher = null;
                logger.info ("关闭TopicPublisher成功");
            }
            if (topicSession != null) {
                topicSession.close ();
                topicSession = null;
                logger.info ("关闭TopicSession成功");
            }
            if (topicConnection != null) {
                topicConnection.close ();
                topicConnection = null;
                logger.info ("关闭TopicConnection成功");
            }
            if (this.aqSession != null) {
                if (aqSession != null) {
                    aqSession.close ();
                    aqSession = null;
                    logger.info ("释放 AQ 会话，成功");
                }
            }
            logger.info ("关闭 AQ 连接，成功");
        } catch (JMSException ex) {
            logger.error ("关闭 AQ 连接发生异常：" + ex.getMessage ());
        }
    }
}
