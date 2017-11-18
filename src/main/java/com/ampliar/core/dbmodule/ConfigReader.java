package com.ampliar.core.dbmodule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
 

public class ConfigReader {

	public Properties getConfigurations() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			//input = new FileInputStream("./config.properties");
			//input = getClass().getClassLoader().getResourceAsStream("classpath:com/ampliar/core/dbmodule/config.properties");
			input = getClass().getClassLoader().getResourceAsStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("dbms"));
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return prop;
	}
}
