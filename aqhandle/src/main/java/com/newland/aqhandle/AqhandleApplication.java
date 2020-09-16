package com.newland.aqhandle;

import com.newland.aqhandle.DTO.OperatorOa;
import com.newland.aqhandle.DTO.OrganizationOa;
import com.newland.aqhandle.Dao.OperatorOaRepository;
import com.newland.aqhandle.Dao.OperatorOragnOaRepository;
import com.newland.aqhandle.Dao.OrganizationOaRepository;
import com.newland.aqhandle.Handle.AQhandleAsyn;
import com.newland.aqhandle.Util.OracleAqUtil;
import com.sun.javafx.scene.shape.PathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
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
    OracleAqUtil oracleAqUtil;
    @Autowired
    DataSource dataSource;


    public static void main (String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddHHmmss"); // 规定日期格式
        String now = formatter.format(System.currentTimeMillis ());
        System.setProperty ("time",now);
        SpringApplication.run (AqhandleApplication.class, args);
    }

    @Override
    public void run (ApplicationArguments args) throws Exception {
        StringBuffer buffer=new StringBuffer ();
        buffer.append (1);
        buffer.append (6);
        buffer.append (String.format ("%06d",Integer.parseInt (operatorOaRepository.getNextval ())));
        System.out.println (buffer.toString ());

    }
}
