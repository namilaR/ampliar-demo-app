package com.ampliar.dbmodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.core.dbmodule.ConfigReader;
import com.ampliar.core.dbmodule.DataAccess;
import com.ampliar.core.dbmodule.RelationToObjectMapper;
import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.FileUploader;
import org.apache.log4j.Logger;

public class MySqlDataAccess implements DataAccess {

    private Properties props;
    private Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static final Logger logger = Logger.getLogger(MySqlDataAccess.class);

    public MySqlDataAccess() {
        debug("mysql constructor exectuted");
        if (this.con == null) {
            getConnectionConfigurations();
            try {
                // using mysql driver and configs create and return db connection
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mysql://" + props.getProperty("host") + ":" + props.getProperty("port") + "/"
                                + props.getProperty("database"),
                        props.getProperty("dbuser"), props.getProperty("dbpassword"));

                debug("db connection established to mysql server");

            } catch (ClassNotFoundException e) {
                logger.error("ClassNotFoundException", e);

            } catch (SQLException e) {
                logger.error("SQLException", e);
            }
        }
    }

    public ArrayList<Advertisment> findAllAdvertisments() {
        pst = null;
        rs = null;
        String query = "SELECT* FROM advertisments";
        debug(query);

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            logger.error("SQLException", e);
        }

        return null;
    }

    public ArrayList<Advertisment> findAllAdvertismentsByCategory() {
        return null;
    }

    public Advertisment findAdvertismentById(int id) {
        pst = null;
        rs = null;
        String query = "SELECT* FROM advertisments WHERE ID = ?";
        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            return new RelationToObjectMapper().createMappedRow(rs);

        } catch (SQLException e) {
            logger.error("SQLException", e);
        }

        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByCategory(String Category) {
        pst = null;
        rs = null;
        String query = "SELECT* FROM advertisments WHERE CATEGORY = ?";

        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, Category);
            rs = pst.executeQuery();

            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            logger.error("SQLException", e);
        }
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentBySubCategory(String subCategory) {
                pst = null;
        rs = null;
        String query = "SELECT* FROM advertisments WHERE SUB_CATEGORY = ?";

        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, subCategory);
            rs = pst.executeQuery();

            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            logger.error("SQLException", e);
        }
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByDistrict(String district) {
                pst = null;
        rs = null;
        String query = "SELECT* FROM advertisments WHERE DISTRICT = ?";

        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, district);
            rs = pst.executeQuery();

            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            logger.error("SQLException", e);
        }
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByDistrictLocalArea(String districtLocalArea) {
                pst = null;
        rs = null;
        String query = "SELECT* FROM advertisments WHERE DISTRICT_LOCAL_AREA = ?";

        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, districtLocalArea);
            rs = pst.executeQuery();

            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            logger.error("SQLException", e);
        }
        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByTitle(String title) {
        pst = null;
        rs = null;
        String query = "SELECT* FROM advertisments WHERE TITLE LIKE ?";

        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, "%" + title + "%");
            rs = pst.executeQuery();

            return new RelationToObjectMapper().createMappedRowList(rs);

        } catch (SQLException e) {
            logger.error("SQLException", e);
        }


        return null;
    }

    public boolean insertAdvertisment(Advertisment adv) {
        pst = null;
        String queryAdvertisment = "INSERT INTO `ampliar_demo`.`advertisments` (  `PUBLISHED_BY`, `TITLE`, `PRICE`, `CATEGORY`, `SUB_CATEGORY`, `DISTRICT`, `DISTRICT_LOCAL_AREA`, `IMAGES`, `ATTRIBUTES`, `STATUS`, `CREATED_AT`, `UPDATED_AT` )\n" +
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
            logger.error("SQLException", e);

        }

        return false;
    }

    public boolean updateAdvertisment(Advertisment adv) {
        pst = null;
        rs = null;
        String queryAdvertisment = "UPDATE `ampliar_demo`.`advertisments`\n" +
                " SET `TITLE` = ?,\n" +
                " `PRICE` = ?,\n" +
                " `CATEGORY` = ?,\n" +
                " `SUB_CATEGORY` = ?,\n" +
                " `DISTRICT` = ?,\n" +
                " `DISTRICT_LOCAL_AREA` = ?,\n" +
                " `IMAGES` = ?,\n" +
                " `ATTRIBUTES` = ?,\n" +
                " `STATUS` = ?,\n" +
                " `UPDATED_AT` = ?\n" +
                " WHERE\n" +
                "	(`ID` = ?);";

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
            logger.error("SQLException", e);

        }

        return false;

    }

    public boolean deleteAdvertisment(Advertisment adv) {
        pst = null;
        rs = null;
        String query = "DELETE FROM advertisments WHERE ID = ?";
        debug(query);

        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, adv.getAdvertismentId());
            pst.executeUpdate();
            return true;


        } catch (SQLException e) {
            logger.error("SQLException", e);
        }


        return false;
    }


    public int AddUser(User user) {
        pst = null;
        String queryUser = "INSERT INTO `ampliar_demo`.`users` (  `name`, `email`, `password`, `user_type`, `status`, `SecurityQuestion`, `SecurityAnswer`, `authenticator`)\n" +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            pst = con.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getUser_type());
            pst.setString(5, user.getStatus());
            pst.setString(6, user.getSec_question());
            pst.setString(7, user.getSec_answer());
            pst.setString(8, user.getAuthenticator());


            int i = pst.executeUpdate();
            int id = 0;
            if (i == 1) {
                rs = pst.getGeneratedKeys();

                while (rs.next()) {
                    id = rs.getInt(1);

                }
            }

            return id;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;

        }
    }

    public boolean CheckFederatedUserExists(String email, String authenticator) {
        pst = null;
        String queryLogin = "select * from users where email=? and authenticator=?";

        try {
            pst = con.prepareStatement(queryLogin);
            pst.setString(1, email);
            pst.setString(2, authenticator);

            rs = pst.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int AddLoginInfo(LoginInfo info) {
        pst = null;
        String queryUserInfo = "INSERT INTO `ampliar_demo`.`login_info` (  `email`, `userip`, `browser`, `device`, `latitude`, `longitude`, `status`)\n" +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?);";

        try {
            pst = con.prepareStatement(queryUserInfo, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, info.getEmail());
            pst.setString(2, info.getUserip());
            pst.setString(3, info.getBrowser());
            pst.setString(4, info.getDevice());
            pst.setDouble(5, info.getLatitude());
            pst.setDouble(6, info.getLongitude());
            pst.setString(7, info.getStatus());

            int i = pst.executeUpdate();
            int id = 0;
            if (i == 1) {
                rs = pst.getGeneratedKeys();

                while (rs.next()) {
                    id = rs.getInt(1);

                }
            }

            return id;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;

        }
    }

    public boolean Login(String email, String password) {
        pst = null;
        String queryLogin = "select * from users where email=? and password=? and authenticator=?";

        try {
            pst = con.prepareStatement(queryLogin);
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, "Local");

            rs = pst.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean CheckEmailExists(String email) {
        pst = null;
        String queryCheckEmailExists = "select * from users where email=? and authenticator=?";

        try {
            pst = con.prepareStatement(queryCheckEmailExists);
            pst.setString(1, email);
            pst.setString(2, "Local");

            rs = pst.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ResultSet GetSecurityQuestion(String email) {
        pst = null;
        String queryGetSecurityQuestion = "select SecurityQuestion from users where email=? and authenticator=?";

        try {
            pst = con.prepareStatement(queryGetSecurityQuestion);
            pst.setString(1, email);
            pst.setString(2, "Local");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public boolean CheckSecurityAnswer(String email, String answer) {
        pst = null;
                String queryCheckSecurityAnswer="select * from users where email=? and SecurityAnswer=? and authenticator=?";

		try
                {
                    pst=con.prepareStatement(queryCheckSecurityAnswer);
                    pst.setString(1,email);
                    pst.setString(2,answer);
                    pst.setString(3,"Local");

                    rs=pst.executeQuery();
                    return rs.next();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
    }

    public int AddGetItemEventRecord(int ad_id, String ipaddress, String date, String time, String category) {
        pst = null;
        String queryUser = "INSERT INTO `ampliar_demo`.`getitem_listener` (  `ad_id`, `ipaddress`, `date`, `time`, `category`)\n" +
                "VALUES ( ?, ?, ?, ?, ?);";

        try {
            pst = con.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, ad_id);
            pst.setString(2, ipaddress);
            pst.setString(3, date);
            pst.setString(4, time);
            pst.setString(5, category);

            int i = pst.executeUpdate();
            int id = 0;
            if (i == 1) {
                rs = pst.getGeneratedKeys();

                while (rs.next()) {
                    id = rs.getInt(1);

                }
            }

            return id;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;

        }
    }

    public int AddPostItemEventRecord(String ad_name, String ipaddress, String date, String time, String category) {
        pst = null;
        String queryUser = "INSERT INTO `ampliar_demo`.`postitem_listener` (  `ad_name` ,`ip`, `date`, `time`, `category`)\n" +
                "VALUES ( ?,?, ?, ?, ?);";

        try {
            pst = con.prepareStatement(queryUser, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, ad_name);
            pst.setString(2, ipaddress);
            pst.setString(3, date);
            pst.setString(4, time);
            pst.setString(5, category);

            int i = pst.executeUpdate();
            int id = 0;
            if (i == 1) {
                rs = pst.getGeneratedKeys();

                while (rs.next()) {
                    id = rs.getInt(1);

                }
            }

            return id;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;

        }
    }


    public ResultSet getVisitorCount() {
        pst = null;
        String querygetVisitorCount = "select COUNT(*) AS total,COUNT(DISTINCT ip) AS visitors from page_views";

        try {
            pst = con.prepareStatement(querygetVisitorCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ResultSet getHomePageViewCount() {
        pst = null;
        String querygetHomePageViewCount = "select COUNT(*) AS total from page_views where page='Home'";

        try {
            pst = con.prepareStatement(querygetHomePageViewCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ResultSet getVisitorCountWithDate() {
        pst = null;
        String querygetVisitorCountWithDate = "SELECT date,COUNT(DISTINCT ip) AS visitors FROM `page_views` group by date";

        try {
            pst = con.prepareStatement(querygetVisitorCountWithDate);

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ResultSet getSessionsWithDate() {
        pst = null;
        String querygetSessionsWithDate = "select date,COUNT(*) AS sessions from page_views group by date";

        try {
            pst = con.prepareStatement(querygetSessionsWithDate);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public ResultSet GetIPaddressRecords(String email) {
        pst = null;
        String queryGetIPaddressRecords = "select userip from login_info where email=? and status=?";

        try {
            pst = con.prepareStatement(queryGetIPaddressRecords);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetLastIPaddress(String email) {
        pst = null;
        String queryGetLastIPaddress = "SELECT userip FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";

        try {
            pst = con.prepareStatement(queryGetLastIPaddress);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetBrowserRecords(String email) {
        pst = null;
        String queryGetBrowserRecords = "select browser from login_info where email=? and status=?";

        try {
            pst = con.prepareStatement(queryGetBrowserRecords);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetLastBrowser(String email) {
        pst = null;
        String queryGetLastBrowser = "SELECT browser FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";

        try {
            pst = con.prepareStatement(queryGetLastBrowser);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetDeviceRecords(String email) {
        pst = null;
        String queryGetDeviceRecords = "select device from login_info where email=? and status=?";

        try {
            pst = con.prepareStatement(queryGetDeviceRecords);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetLastDevice(String email) {
        pst = null;
        String queryGetLastDevice = "SELECT device FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";

        try {
            pst = con.prepareStatement(queryGetLastDevice);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetTimeRecords(String email) {
        pst = null;
        String queryGetTimeRecords = "select time from login_info where email=? and status=?";

        try {
            pst = con.prepareStatement(queryGetTimeRecords);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetLastTime(String email) {
        pst = null;
        String queryGetLastTime = "select time from login_info where email=? and status=? ORDER BY time DESC LIMIT 1";

        try {
            pst = con.prepareStatement(queryGetLastTime);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetLocationRecords(String email) {
        pst = null;
        String queryGetLocationRecords = "select latitude,longitude from login_info where email=? and status=?";

        try {
            pst = con.prepareStatement(queryGetLocationRecords);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet GetLastLocation(String email) {
        pst = null;
        String queryGetLastLocation = "SELECT latitude,longitude FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";

        try {
            pst = con.prepareStatement(queryGetLastLocation);
            pst.setString(1, email);
            pst.setString(2, "success");

            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
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

    @Override
    public ResultSet getPageViewCountByPage() {
        pst = null;
        String querygetPageViewCountByPage = "SELECT page,COUNT(*) as views FROM `page_views` group by page";

        try {
            pst = con.prepareStatement(querygetPageViewCountByPage);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getHomePageViewsWithDate() {
        pst = null;
        String querygetHomePageViewsWithDate = "SELECT date,COUNT(*) as views FROM `page_views` where page='Home' group by date";

        try {
            pst = con.prepareStatement(querygetHomePageViewsWithDate);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getListPageViewsWithDate() {
        pst = null;
        String querygetListPageViewsWithDate = "SELECT date,COUNT(*) as views FROM `page_views` where page='List' group by date";

        try {
            pst = con.prepareStatement(querygetListPageViewsWithDate);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getSellPageViewsWithDate() {
        pst = null;
        String querygetSellPageViewsWithDate = "SELECT date,COUNT(*) as views FROM `page_views` where page='Sell' group by date";

        try {
            pst = con.prepareStatement(querygetSellPageViewsWithDate);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getPostedAdCategoryCount() {
        pst = null;
        String querygetPostedAdCategoryCount = "SELECT category,COUNT(*) as views FROM `postitem_listener` group by category";

        try {
            pst = con.prepareStatement(querygetPostedAdCategoryCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getCarsCategoryCount() {
        pst = null;
        String querygetCarsCategoryCount = "SELECT date,COUNT(*) as views FROM `postitem_listener` where category='Vehicle' group by date";

        try {
            pst = con.prepareStatement(querygetCarsCategoryCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getPropertyCategoryCount() {
        pst = null;
        String querygetPropertyCategoryCount = "SELECT date,COUNT(*) as views FROM `postitem_listener` where category='Property' group by date";

        try {
            pst = con.prepareStatement(querygetPropertyCategoryCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getElectronicsCategoryCount() {
        pst = null;
        String querygetElectronicsCategoryCount = "SELECT date,COUNT(*) as views FROM `postitem_listener` where category='Electronics and Appliances' group by date";

        try {
            pst = con.prepareStatement(querygetElectronicsCategoryCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getBrowserCount() {
        pst = null;
        String querygetBrowserCount = "SELECT browser,COUNT(*) as views FROM `login_info` group by browser";

        try {
            pst = con.prepareStatement(querygetBrowserCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getDeviceCount() {
        pst = null;
        String querygetDeviceCount = "SELECT device,COUNT(*) as views FROM `login_info` group by device";

        try {
            pst = con.prepareStatement(querygetDeviceCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getAuthenticatorCount() {
        pst = null;
        String querygetAuthenticatorCount = "SELECT authenticator,COUNT(*) as views FROM `users` group by authenticator";

        try {
            pst = con.prepareStatement(querygetAuthenticatorCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getLoginCount() {
        pst = null;
        String querygetLoginCount = "SELECT time,COUNT(*) as logins FROM `login_info` group by time";

        try {
            pst = con.prepareStatement(querygetLoginCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getViewAdCategoryCount() {
        pst = null;
        String querygetViewAdCategoryCount = "SELECT category,COUNT(*) as views FROM `getitem_listener` group by category";

        try {
            pst = con.prepareStatement(querygetViewAdCategoryCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getViewAdCarCategoryCount() {
        pst = null;
        String querygetViewAdCarCategoryCount = "SELECT date,COUNT(*) as views FROM `getitem_listener` where category='Vehicle'";

        try {
            pst = con.prepareStatement(querygetViewAdCarCategoryCount);


            rs = pst.executeQuery();
            return rs;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
