package com.ampliar.dbmodule;

import com.ampliar.core.dbmodule.ConfigReader;
import com.ampliar.core.dbmodule.DataAccess;
import com.ampliar.core.dbmodule.RelationToObjectMapper;
import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.FileUploader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class MsSqlDataAccess implements DataAccess {

    private Properties props;
    private Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public MsSqlDataAccess() {
        System.out.println("msssql constructor exectuted");
        if (this.con == null) {
            getConnectionConfigurations();
            try {
                // using msssql driver and configs create and return db connection
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connString = "jdbc:sqlserver://" + props.getProperty("host") + ":" + props.getProperty("port") + ";databaseName=" + props.getProperty("database") + ";user=" + props.getProperty("dbuser") + ";password="+props.getProperty("dbpassword");
                System.out.println(connString);
                con = DriverManager.getConnection(connString);


                System.out.println("db connection established to mymsssql server");

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Advertisment> findAllAdvertisments() {
        pst = null; rs = null;
        String query = "SELECT* FROM advertisments";
        System.out.println(query);

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Advertisment> findAllAdvertismentsByCategory() {
        return null;
    }

    public Advertisment findAdvertismentById(int id) {
        pst = null; rs = null;
        String query = "SELECT* FROM advertisments WHERE ID = ?";
        System.out.println(query);

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            return new RelationToObjectMapper().createMappedRow(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByTitle(String title) {
        pst = null; rs = null;
        String query = "SELECT* FROM advertisments WHERE TITLE LIKE ?";

        System.out.println(query);

        try {
            pst = con.prepareStatement(query);
            pst.setString(1,"%"+title+"%");
            rs = pst.executeQuery();

            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        return null;
    }

    public boolean insertAdvertisment(Advertisment adv) {
        pst = null;
        String queryAdvertisment = "INSERT INTO [ampliar_demo].[dbo].[advertisments] ( [PUBLISHED_BY],[TITLE],[PRICE],[CATEGORY],[SUB_CATEGORY],[DISTRICT],[DISTRICT_LOCAL_AREA],[IMAGES],[ATTRIBUTES],[STATUS],[CREATED_AT],[UPDATED_AT] )\n" +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";

        try {
            pst = con.prepareStatement(queryAdvertisment, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, adv.getUserId());
            pst.setString(2, adv.getTitle());
            pst.setDouble(3, adv.getPrice());
            pst.setString(4, adv.getAdvertismentCategoty().getCategoryName());
            pst.setString(5, adv.getAdvertismentSubCategoty().getSubCategoryName());
            pst.setString(6, adv.getAdvertismentDistrict().getDistrictName());
            pst.setString(7, adv.getDistrictLoacalArea().getLocalAreaName());
            pst.setString(8, adv.advImageKistToJson());
            pst.setString(9, adv.objToJson());
            pst.setInt(10, 1);
            pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
            pst.setTimestamp(12, new Timestamp(System.currentTimeMillis()));


            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            if (rs.next()) {

                adv.setAdvertismentId(rs.getInt(1));

            }

            pst = null;
            for (AdvertismentImage advImage : adv.getAdvertismentImage()) {
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
        pst = null; rs = null;
        String queryAdvertisment = "UPDATE [ampliar_demo].[dbo].[advertisments] \n" +
                " SET[TITLE] = ?,\n" +
                "[PRICE] = ?,\n" +
                "[CATEGORY] = ?,\n" +
                "[SUB_CATEGORY] = ?,\n" +
                "[DISTRICT] = ?,\n" +
                "[DISTRICT_LOCAL_AREA] = ?,\n" +
                "[IMAGES] = ?,\n" +
                "[ATTRIBUTES] = ?,\n" +
                "[STATUS] = ?,\n" +
                "[UPDATED_AT] = ?\n" +
                " WHERE\n" +
                "	([ID] = ?);";

        try {
            pst = con.prepareStatement(queryAdvertisment);
            pst.setString(1, adv.getTitle());
            pst.setDouble(2, adv.getPrice());
            pst.setString(3, adv.getAdvertismentCategoty().getCategoryName());
            pst.setString(4, adv.getAdvertismentSubCategoty().getSubCategoryName());
            pst.setString(5, adv.getAdvertismentDistrict().getDistrictName());
            pst.setString(6, adv.getDistrictLoacalArea().getLocalAreaName());
            pst.setString(7, adv.advImageKistToJson());
            pst.setString(8, adv.objToJson());
            pst.setInt(9, 1);
            pst.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            pst.setInt(11, adv.getAdvertismentId());
            pst.executeUpdate();
            pst = null;
            for (AdvertismentImage advImage : adv.getAdvertismentImage()) {
                new FileUploader().uploadFile(advImage.getImage());
            }

            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        return false;

    }

    public boolean deleteAdvertisment(Advertisment adv) {
        pst = null; rs = null;
        String query = "DELETE FROM advertisments WHERE ID = ?";
        System.out.println(query);

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1,adv.getAdvertismentId());
            pst.executeUpdate();
            return true;


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return false;
    }

    private void getConnectionConfigurations() {
        ConfigReader conf = new ConfigReader();
        this.props = conf.getConfigurations();
    }

}
