/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ishani
 */
public class LoginInfo {
    private String email;
    private String userip;
    private String device;
    private String browser;
    private double Latitude;
    private double longitude;
    private String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double Latitude) {
        this.Latitude = Latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
    
    public String getIPaddress(HttpServletRequest request)
    {
//        String ipAddress = request.getHeader("X-FORWARDED-FOR");  
//        if (ipAddress == null) {  
//            ipAddress = request.getRemoteAddr();  
//        }
//        return ipAddress;
        String ip=null;
        try{
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                            whatismyip.openStream()));
            ip = in.readLine(); //you get the IP as a String
            //System.out.println(ip);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return ip;
    }
}
