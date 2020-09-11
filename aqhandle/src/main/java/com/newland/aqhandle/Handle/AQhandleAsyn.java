package com.newland.aqhandle.Handle;

import com.newland.aqhandle.Util.OracleAqUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AQhandleAsyn {
    private static final Logger logger= LoggerFactory.getLogger (AQhandleAsyn.class);

    @Async("taskExecutor")
    public void execteAsync (){
        logger.info("start executeAsync"+"--当前运行的线程名称：" + Thread.currentThread().getName());
        while (true){
            System.out.println ("11asd");
            logger.info("end executeAsync"+"--当前运行的线程名称：" + Thread.currentThread().getName());
        }

    }

}
