package com.ampliar.core.dbmodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

	private Properties props;

	public Connection getConnection() {
		getConnectionConfigurations();
		Connection con = null;
		try {

			if (props.getProperty("dbms").equalsIgnoreCase("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				// con = DriverManager.getConnection(props.getProperty("host"),
				// props.getProperty("dbuser"),
				// props.getProperty("dbpassword"));

				con = DriverManager.getConnection(
						"jdbc:mysql://" + props.getProperty("host") + ":" + props.getProperty("port") + "/"
								+ props.getProperty("database"),
						props.getProperty("dbuser"), props.getProperty("dbpassword"));
				System.out.println("db connection established to mysql server");
			} else if (props.getProperty("dbms").equalsIgnoreCase("postgresql")) {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(
						"jdbc:postgresql://" + props.getProperty("host") + ":" + props.getProperty("port") + "/"
								+ props.getProperty("database"),
						props.getProperty("dbuser"), props.getProperty("dbpassword"));

				System.out.println("db connection established to postgresql server");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return con;
	}

	public void getConnectionConfigurations() {
		ConfigReader conf = new ConfigReader();
		this.props = conf.getConfigurations();
	}

}
