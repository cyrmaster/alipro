package com.newland.aqhandle;

import com.newland.aqhandle.Util.OracleAqUtil;
import oracle.AQ.AQException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.JMSException;

@RunWith (SpringRunner.class)
@SpringBootTest(classes = {AqhandleApplication.class})
class AqhandleApplicationTests {

    @Autowired
    OracleAqUtil oracleAqUtil;
    @Test
    void contextLoads () {
        try {
            oracleAqUtil.initAQ ();
            oracleAqUtil.getMessageOnListener ();
        } catch (ClassNotFoundException e) {
            e.printStackTrace ();
        } catch (AQException e) {
            e.printStackTrace ();
        } catch (JMSException e) {
            e.printStackTrace ();
        }
    }

}
