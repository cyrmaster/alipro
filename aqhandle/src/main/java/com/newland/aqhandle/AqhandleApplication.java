package com.newland.aqhandle;

import com.newland.aqhandle.DTO.OperatorOa;
import com.newland.aqhandle.Dao.OperatorOaRepository;
import com.newland.aqhandle.Handle.AQhandleAsyn;
import com.newland.aqhandle.Util.OracleAqUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
public class AqhandleApplication implements ApplicationRunner {
    @Autowired
    OperatorOaRepository operatorOaRepository;
    @Autowired
    AQhandleAsyn aQhandleAsyn;
    @Autowired
    OracleAqUtil oracleAqUtil;
    @Autowired
    @Qualifier("aqDataSource")
    private DataSource dataSource;

    public static void main (String[] args) {
        System.out.println ("11");
        SpringApplication.run (AqhandleApplication.class, args);
    }

    @Override
    public void run (ApplicationArguments args) throws Exception {
        System.out.println (dataSource.getConnection ());
    }
}
