package com.ampliar.dbmodule;

import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.core.dbmodule.ConfigReader;
import com.ampliar.core.dbmodule.DataAccess;
import com.ampliar.core.dbmodule.RelationToObjectMapper;
import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.FileUploader;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class PostgreSqlDataAccess implements DataAccess {

    private Properties props;
    private Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static final Logger logger = Logger.getLogger(PostgreSqlDataAccess.class);


    public PostgreSqlDataAccess(){
        debug("postgreSql constructor exectuted");
        if (this.con == null) {
            getConnectionConfigurations();
            try {
                // using postgreSql driver and configs create and return db connection
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(
                        "jdbc:postgresql://" + props.getProperty("host") + ":" + props.getProperty("port") + "/"
                                + props.getProperty("database"),
                        props.getProperty("dbuser"), props.getProperty("dbpassword"));

                debug("db connection established to postgreSql server");

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                logger.error("ClassNotFoundException",e);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                logger.error("SQLException",e);
            }
        }
    }

    public ArrayList<Advertisment> findAllAdvertisments() {
        pst = null; rs = null;
        String query = "SELECT* FROM advertisments";
        debug(query);

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException",e);
        }

        return null;
    }

    public ArrayList<Advertisment> findAllAdvertismentsByCategory() {
        return null;
    }

    public Advertisment findAdvertismentById(int id) {
        pst = null; rs = null;
        String query = "SELECT* FROM advertisments WHERE ID = ?";
        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1,id);
            rs = pst.executeQuery();
            return new RelationToObjectMapper().createMappedRow(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException",e);
        }

        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByCategory(String Category) {
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentBySubCategory(String subCategory) {
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByDistrict(String district) {
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByDistrictLocalArea(String districtLocalArea) {
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByTitle(String title) {
        pst = null; rs = null;
        String query = "SELECT* FROM advertisments WHERE title ILIKE ?";

        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setString(1,"%"+title+"%");
            rs = pst.executeQuery();

            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException",e);
        }



        return null;
    }

    public boolean insertAdvertisment(Advertisment adv) {
        pst = null;

        String queryAdvertisment = "INSERT INTO \"public\".\"advertisments\" (  \"published_by\", \"title\", \"price\", \"category\", \"sub_category\", \"district\", \"district_local_area\", \"images\", \"attributes\", \"status\", \"created_at\", \"updated_at\" )\n" +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, to_json(?::json), to_json(?::json), ?, ?, ? );";
        debug(queryAdvertisment);

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
            logger.error("SQLException",e);

        }

        return false;
    }

    public boolean updateAdvertisment(Advertisment adv) {
        pst = null; rs = null;
        String queryAdvertisment = "UPDATE \"public\".\"advertisments\" \n" +
                " SET \"title\" = ?,\n" +
                " \"price\" = ?,\n" +
                " \"category\" = ?,\n" +
                " \"sub_category\" = ?,\n" +
                " \"district\" = ?,\n" +
                " \"district_local_area\" = ?,\n" +
                " \"images\" = to_json(?::json),\n" +
                " \"attributes\" = to_json(?::json),\n" +
                " \"status\" = ?,\n" +
                " \"updated_at\" = ?\n" +
                " WHERE\n" +
                "	(\"id\" = ?);";

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
            logger.error("SQLException",e);

        }

        return false;

    }

    public boolean deleteAdvertisment(Advertisment adv) {
        pst = null; rs = null;
        String query = "DELETE FROM advertisments WHERE ID = ?";
        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1,adv.getAdvertismentId());
            pst.executeUpdate();
            return true;


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            logger.error("SQLException",e);
        }


        return false;
    }

    public int AddUser(User user) {
        return 0;
    }

    public boolean CheckFederatedUserExists(String email, String authenticator) {
        return false;
    }

    public int AddLoginInfo(LoginInfo info) {
        return 0;
    }

    public boolean Login(String email, String password) {
        return false;
    }

    public boolean CheckEmailExists(String email) {
        return false;
    }

    public ResultSet GetSecurityQuestion(String email) {
        return null;
    }

    public boolean CheckSecurityAnswer(String email, String answer) {
        return false;
    }

    public ResultSet GetIPaddressRecords(String email) {
        return null;
    }

    public ResultSet GetLastIPaddress(String email) {
        return null;
    }

    public ResultSet GetBrowserRecords(String email) {
        return null;
    }

    public ResultSet GetLastBrowser(String email) {
        return null;
    }

    public ResultSet GetDeviceRecords(String email) {
        return null;
    }

    public ResultSet GetLastDevice(String email) {
        return null;
    }

    public ResultSet GetTimeRecords(String email) {
        return null;
    }

    public ResultSet GetLastTime(String email) {
        return null;
    }

    public ResultSet GetLocationRecords(String email) {
        return null;
    }

    public ResultSet GetLastLocation(String email) {
        return null;
    }

    private void getConnectionConfigurations() {
        ConfigReader conf = new ConfigReader();
        this.props = conf.getConfigurations();
    }

    private void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

}
