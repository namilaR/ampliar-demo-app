package com.ampliar.core.dbmodule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;


public class ConfigReader {

    private static final Logger logger = Logger.getLogger(ConfigReader.class);

    public Properties getConfigurations() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            //get prop file path using ResourceAsStream
            input = getClass().getClassLoader().getResourceAsStream("db.config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            if (logger.isDebugEnabled()) {
                logger.debug("DBMS : " + prop.getProperty("dbms"));
                logger.debug("DATABASE : " + prop.getProperty("database"));
                logger.debug("DB_USER : " + prop.getProperty("dbuser"));
                logger.debug("DB_PASSWORD : " + prop.getProperty("dbpassword"));
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error("IOException", e);
                }
            }

        }
        return prop;
    }
}
