package com.example.mdbspringboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {

    private static Logger log = LoggerFactory.getLogger(Logging.class);

    private static Logging logging;

    private Logging(){

    }

    public static Logging getLog(){

        if(logging == null){
            logging = new Logging();
        }
        return logging;
    }


    public void info(String message){
        log.info(message);
    }

    public void error(String message){
        log.error(message);
    }

    public void warn(String message){
        log.warn(message);
    }

    public void debug(String message){
        log.debug(message);
    }

    public void info(String message,double value){
        log.info(message,value);
    }

    public void info(String message,int value){
        log.info(message,value);
    }

}
