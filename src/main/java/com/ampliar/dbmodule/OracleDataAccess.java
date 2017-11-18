package com.ampliar.dbmodule;

import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.core.dbmodule.DataAccess;
import com.ampliar.core.models.Advertisment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class OracleDataAccess implements DataAccess {

    private Properties props;
    private Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ArrayList<Advertisment> findAllAdvertisments() {

        return null;
    }

    public ArrayList<Advertisment> findAllAdvertismentsByCategory() {

        return null;
    }

    public Advertisment findAdvertismentById(int id) {

        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByTitle(String title) {

        return null;
    }

    public boolean insertAdvertisment(Advertisment obj) {

        return false;
    }

    public boolean updateAdvertisment(Advertisment obj) {

        return false;
    }

    public boolean deleteAdvertisment(Advertisment obj) {

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
}
