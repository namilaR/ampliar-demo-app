/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.temp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;

/**
 *
 * @author Ishani
 */
public class DataAccess {
    
    private static DBConnection dbconnection=new DBConnection();
    private static Connection conn=null;
    private static PreparedStatement pst=null;
    private static ResultSet rs=null;
    
    public static int AddUser(User user)
    {    
        try {
            String q = "Insert into users (name,email,password,user_type,status,SecurityQuestion,SecurityAnswer,authenticator) values('"+user.getName()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getUser_type()+"','"+user.getStatus()+"','"+user.getSec_question()+"','"+user.getSec_answer()+"','"+user.getAuthenticator()+"')";
            conn=dbconnection.connect();

            pst = conn.prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
            int i = pst.executeUpdate();
            int id=0;
            if(i==1)
            {
                rs=pst.getGeneratedKeys();
                
                while (rs.next()) {
                    id=rs.getInt(1);
                        
                }
            }
            return id;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        
    }
    public static boolean CheckFederatedUserExists(String email, String authenticator)
    {
        try
        {
            String sql1="select * from users where email=? and authenticator=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2,authenticator);
      
            rs=pst.executeQuery();
            return rs.next();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public static int AddLoginInfo(LoginInfo info)
    {
        try {
            String q = "Insert into login_info (email,userip,browser,device,latitude,longitude,status) values('"+info.getEmail()+"','"+info.getUserip()+"','"+info.getBrowser()+"','"+info.getDevice()+"','"+info.getLatitude()+"','"+info.getLongitude()+"','"+info.getStatus()+"')";
            conn=dbconnection.connect();

            pst = conn.prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
            int i = pst.executeUpdate();
            int id=0;
            if(i==1)
            {
                rs=pst.getGeneratedKeys();
                
                while (rs.next()) {
                    id=rs.getInt(1);
                        
                }
            }
            return id;
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public static boolean Login(String email,String password)
    {
        try
        {
            String sql1="select * from users where email=? and password=? and authenticator=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2,password);
            pst.setString(3,"Local");
      
            rs=pst.executeQuery();
            return rs.next();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static boolean CheckEmailExists(String email)
    {
        try
        {
            String sql1="select * from users where email=? and authenticator=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2,"Local");
      
            rs=pst.executeQuery();
            return rs.next();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public static ResultSet GetSecurityQuestion(String email)
    {
        try
        {
            String sql1="select SecurityQuestion from users where email=? and authenticator=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "Local");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static boolean CheckSecurityAnswer(String email,String answer)
    {
        try
        {
            String sql1="select * from users where email=? and SecurityAnswer=? and authenticator=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2,answer);
            pst.setString(3,"Local");
      
            rs=pst.executeQuery();
            return rs.next();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public static ResultSet GetIPaddressRecords(String email)
    {
        try
        {
            String sql1="select userip from login_info where email=? and status=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetLastIPaddress(String email)
    {
        try
        {
            String sql1="SELECT userip FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetBrowserRecords(String email)
    {
        try
        {
            String sql1="select browser from login_info where email=? and status=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetLastBrowser(String email)
    {
        try
        {
            String sql1="SELECT browser FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetDeviceRecords(String email)
    {
        try
        {
            String sql1="select device from login_info where email=? and status=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetLastDevice(String email)
    {
        try
        {
            String sql1="SELECT device FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetTimeRecords(String email)
    {
        try
        {
            String sql1="select time from login_info where email=? and status=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static ResultSet GetLastTime(String email)
    {
        try
        {
            String sql1="select time from login_info where email=? and status=? ORDER BY time DESC LIMIT 1";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetLocationRecords(String email)
    {
        try
        {
            String sql1="select latitude,longitude from login_info where email=? and status=?";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static ResultSet GetLastLocation(String email)
    {
        try
        {
            String sql1="SELECT latitude,longitude FROM login_info where email=? and status=? ORDER BY time DESC LIMIT 1";
            conn=dbconnection.connect();
            
            pst=conn.prepareStatement(sql1);
            pst.setString(1,email);
            pst.setString(2, "success");
      
            rs=pst.executeQuery();
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
