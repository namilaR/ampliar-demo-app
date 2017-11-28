package com.ampliar.core.dbmodule;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.core.models.Advertisment;

public interface DataAccess {
	
	public ArrayList<Advertisment> findAllAdvertisments();
	public ArrayList<Advertisment> findAllAdvertismentsByCategory();
	public Advertisment findAdvertismentById(int id);
	public ArrayList<Advertisment> findAdvertismentByTitle(String title);
	public boolean insertAdvertisment(Advertisment obj);
	public boolean updateAdvertisment(Advertisment obj);
	public boolean deleteAdvertisment(Advertisment obj);
	public int AddUser(User user);
	public boolean CheckFederatedUserExists(String email, String authenticator);
	public int AddLoginInfo(LoginInfo info);
	public boolean Login(String email,String password);
	public boolean CheckEmailExists(String email);
	public ResultSet GetSecurityQuestion(String email);
	public boolean CheckSecurityAnswer(String email,String answer);
	public ResultSet GetIPaddressRecords(String email);
	public ResultSet GetLastIPaddress(String email);
	public ResultSet GetBrowserRecords(String email);
	public ResultSet GetLastBrowser(String email);
	public ResultSet GetDeviceRecords(String email);
	public ResultSet GetLastDevice(String email);
	public ResultSet GetTimeRecords(String email);
	public ResultSet GetLastTime(String email);
	public ResultSet GetLocationRecords(String email);
	public ResultSet GetLastLocation(String email);
        public int AddGetItemEventRecord(int ad_id,String ipaddress,String date,String time,String category);
        public int AddPostItemEventRecord(String ad_name,String ip,String date,String time,String category);
        public ResultSet getVisitorCount();
        public ResultSet getHomePageViewCount();
        public ResultSet getVisitorCountWithDate();
        public ResultSet getSessionsWithDate();
        public ResultSet getPageViewCountByPage();
        public ResultSet getHomePageViewsWithDate();
        public ResultSet getListPageViewsWithDate();
        public ResultSet getSellPageViewsWithDate();

}
