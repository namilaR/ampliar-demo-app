package com.ampliar.dbmodule;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.ampliar.core.dbmodule.ConfigReader;
import com.ampliar.core.dbmodule.DataAccess;
import com.ampliar.core.dbmodule.DynamicClassHandeller;
import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.FileUploader;
import com.ampliar.core.models.SubCategory;
import com.mysql.jdbc.Util;

public class MySqlDataAccess implements DataAccess {

	private Properties props;
	private Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public MySqlDataAccess() {
		System.out.println("mysql constructor exectuted");
		if (this.con == null) {
			getConnectionConfigurations();
			try {
				// using mysql driver and configs create and return db connection
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(
						"jdbc:mysql://" + props.getProperty("host") + ":" + props.getProperty("port") + "/"
								+ props.getProperty("database"),
						props.getProperty("dbuser"), props.getProperty("dbpassword"));

				System.out.println("db connection established to mysql server");

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Advertisment> findAllAdvertisments(String categoryType,String subCategoryType) {
		pst = null; rs = null;
		String query = "SELECT\n" +
						"	*\n" +
						"FROM\n" +
						"	advertisments\n" +
						"INNER JOIN advertisment_images ON advertisment_images.ADVERTISMENT_ID = advertisments.ID \n";
		
		if(categoryType != null) {
			query = query.split("WHERE")[0];
			query += "WHERE advertisments.CATEGORY = '" + categoryType + "' ";
		}
		
		if(subCategoryType!= null) {
			query = query.split("WHERE")[0];
			query += "WHERE advertisments.SUB_CATEGORY = '" + subCategoryType + "' ";
		}
		
		if(categoryType != null && subCategoryType!= null) {
			query = query.split("WHERE")[0];
			query += "WHERE advertisments.CATEGORY = " + categoryType + " AND advertisments.SUB_CATEGORY = '" + subCategoryType + "' ";
		}
		
		System.out.println(query);
		
		try {
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			
			
			
			return new DynamicClassHandeller().createDynamicClassList(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return null;
	}
	


	public boolean insertAdvertisment(Advertisment adv) {

		String queryAdvertisment = "INSERT INTO `ampliar_demo`.`advertisments` (\n" + "	`PUBLISHED_BY`,\n"
				+ "	`TITLE`,\n" + "	`PRICE`,\n" + "	`CATEGORY`,\n" + "	`SUB_CATEGORY`,\n" + "	`DISTRICT`,\n"
				+ "	`DISTRICT_LOCAL_AREA`,\n" + "	`ATTRIBUTES`,\n" + "	`STATUS`,\n" + "	`CREATED_AT`,\n"
				+ "	`UPDATED_AT`\n" + ")\n" + "VALUES\n" + "	(\n" + "		?,\n" + "		?,\n" + "		?,\n"
				+ "		?,\n" + "		?,\n" + "		?,\n" + "		?,\n" + "		?,\n" + "		?,\n"
				+ "		?,\n" + "		?\n" + "	);";

		try {
			pst = con.prepareStatement(queryAdvertisment, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, adv.getUserId());
			pst.setString(2, adv.getTitle());
			pst.setDouble(3, adv.getPrice());
			pst.setString(4, adv.getAdvertismentCategoty().getCategoryName());
			pst.setString(5, adv.getAdvertismentSubCategoty().getSubCategoryName());
			pst.setString(6, adv.getAdvertismentDistrict().getDistrictName());
			pst.setString(7, adv.getDistrictLoacalArea().getLocalAreaName());
			pst.setString(8, adv.objToJson());
			pst.setInt(9, 1);
			pst.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));

			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {

				adv.setAdvertismentId(rs.getInt(1));

			}

			pst = null;
			for (AdvertismentImage advImage : adv.getAdvertismentImage()) {
				advImage.getImage();

				String query = "INSERT INTO `ampliar_demo`.`advertisment_images` (\n" + "	`ADVERTISMENT_ID`,\n"
						+ "	`IMAGE_URL`,\n" + "	`STATUS`,\n" + "	`CREATED_AT`,\n" + "	`UPDATED_AT`\n" + ")\n"
						+ "VALUES\n" + "	(\n" + "		?,\n" + "		?,\n" + "		?,\n" + "		?,\n"
						+ "		?\n" + "	);";

				pst = con.prepareStatement(query);
				pst.setInt(1, adv.getAdvertismentId());
				pst.setString(2, advImage.getImage().getOriginalFilename());
				pst.setInt(3, 1);
				pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				pst.execute();
				new FileUploader().uploadFile(advImage.getImage());
			}

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return false;
	}

	public boolean updateAdvertisment(Advertisment adv) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteAdvertisment(Advertisment adv) {
		// TODO Auto-generated method stub
		return false;
	}

	private void getConnectionConfigurations() {
		ConfigReader conf = new ConfigReader();
		this.props = conf.getConfigurations();
	}

	public Advertisment findAdvertismentById(int id) {
		pst = null; rs = null;
		String query = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	advertisments\n" +
				"INNER JOIN advertisment_images ON advertisment_images.ADVERTISMENT_ID = advertisments.ID \n" +
				"WHERE advertisments.ID = " + id;
		
		System.out.println(query);
		
		try {
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			
			return new DynamicClassHandeller().createDynamicClass(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	public ArrayList<Advertisment> findAdvertismentByTitle(String title) {
		pst = null; rs = null;
		String query = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	advertisments\n" +
				"INNER JOIN advertisment_images ON advertisment_images.ADVERTISMENT_ID = advertisments.ID \n" +
				"WHERE advertisments.TITLE LIKE '%" + title + "%'";
		
		System.out.println(query);
		
		try {
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			
			return new DynamicClassHandeller().createDynamicClassList(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		return null;
	}

	

}
