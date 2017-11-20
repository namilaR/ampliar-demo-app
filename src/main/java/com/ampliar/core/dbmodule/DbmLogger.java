package com.ampliar.core.dbmodule;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DbmLogger {
    Logger logger = Logger.getLogger("DB_MODULE");
    FileHandler fh;
    private static  DbmLogger instance = null;

    private DbmLogger() {
        try {
            fh = new FileHandler("classpath:resources/dbmodule.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DbmLogger getInstance(){
        if(instance == null) {
            instance = new DbmLogger();
        }

        return instance;
    }

    public void info(String message){
        logger.info(message);
    }

    public void error(String message){
        logger.severe(message);
    }


}
