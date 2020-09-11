package com.newland.aqhandle;

import com.newland.aqhandle.DTO.OperatorOa;
import com.newland.aqhandle.Dao.OperatorOaRepository;
import com.newland.aqhandle.Handle.AQhandleAsyn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class AqhandleApplication implements ApplicationRunner {
    @Autowired
    OperatorOaRepository operatorOaRepository;
    @Autowired
    AQhandleAsyn aQhandleAsyn;

    public static void main (String[] args) {
        System.out.println ("11");
        SpringApplication.run (AqhandleApplication.class, args);
    }

    @Override
    public void run (ApplicationArguments args) throws Exception {
            aQhandleAsyn.execteAsync ();
    }
}
