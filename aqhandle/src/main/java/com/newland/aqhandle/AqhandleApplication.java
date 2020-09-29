package com.newland.aqhandle;

import com.newland.aqhandle.DTO.OperatorOa;
import com.newland.aqhandle.DTO.OrganizationOa;
import com.newland.aqhandle.Dao.OperatorOaRepository;
import com.newland.aqhandle.Dao.OperatorOragnOaRepository;
import com.newland.aqhandle.Dao.OrganizationOaRepository;
import com.newland.aqhandle.Handle.AQhandleAsyn;
import com.newland.aqhandle.Util.OracleAqUtil;
import com.sun.javafx.scene.shape.PathUtils;
import oracle.AQ.AQException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.jms.JMSException;
import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
@EnableAsync
public class AqhandleApplication implements ApplicationRunner {
    Logger logger= LoggerFactory.getLogger (AqhandleApplication.class);
    @Autowired
    OrganizationOaRepository organizationOaRepository;
    @Autowired
    OperatorOaRepository operatorOaRepository;
    @Autowired
    OperatorOragnOaRepository opOragnOaRepository;
    @Autowired
    AQhandleAsyn aQhandleAsyn;
    @Autowired
    DataSource dataSource;
    @Qualifier("aqDataSource")
    @Autowired
    private DataSource dataSource1;
    @Autowired
    private OracleAqUtil oracleAqUtil;
    private Boolean running=true;



    public static void main (String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddHHmmss"); // 规定日期格式
        String now = formatter.format(System.currentTimeMillis ());
        System.setProperty ("time",now);
        SpringApplication.run (AqhandleApplication.class, args);
    }

    @Override
    public void run (ApplicationArguments args) throws Exception {
        try {
            oracleAqUtil.initAQ ();
        } catch (ClassNotFoundException e) {
            logger.error (e.toString ());
        } catch (AQException e) {
            logger.error (e.toString ());
        } catch (JMSException e) {
            logger.error (e.toString ());
        }
        aQhandleAsyn.getMessage ();//开启获取aq消息线程池
        while (running) {
            aQhandleAsyn.execteAsync (AQhandleAsyn.getQueue ());
        }
    }
}
