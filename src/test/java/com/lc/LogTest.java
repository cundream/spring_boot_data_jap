package com.lc;


import org.apache.log4j.Logger;

public class LogTest {
    private final  Logger log = Logger.getLogger(this.getClass());


    private  void get(){

        log.info("我知道，现在在做日志");
    }
    public static void main(String[] args) {
        LogTest t= new LogTest();
        t.get();
        System.out.println("dddddddddd");
    }

}
