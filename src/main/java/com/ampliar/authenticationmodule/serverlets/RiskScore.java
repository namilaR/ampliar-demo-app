/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;

 
import com.ampliar.temp.DataAccess;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Ishani
 */
public class RiskScore {
    private static double ip_weight;
    private static double browser_weight;
    private static double device_weight;
    private static double time_weight;
    private static double location_weight;
    private static double time_range;
    private static double latitude_range;
    private static double longitude_range;
    
    public static void assignvalues()
    {
        Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("G:\\Home-work\\ST2\\Netbeans\\TestProject\\src\\main\\webapp\\config\\adaptiveconfig.properties");

		// load a properties file
		prop.load(input);

		// get the property value
                RiskScore.ip_weight=Double.parseDouble(prop.getProperty("ip_weight"));
                RiskScore.browser_weight=Double.parseDouble(prop.getProperty("browser_weight"));
		RiskScore.device_weight=Double.parseDouble(prop.getProperty("device_weight"));
                RiskScore.time_weight=Double.parseDouble(prop.getProperty("time_weight"));
                RiskScore.location_weight=Double.parseDouble(prop.getProperty("location_weight"));
                RiskScore.time_range=Double.parseDouble(prop.getProperty("time_range"));
                RiskScore.latitude_range=Double.parseDouble(prop.getProperty("latitude_range"));
                RiskScore.longitude_range=Double.parseDouble(prop.getProperty("longitude_range"));
        }
        catch (IOException ex) {
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
    }
    public static double calIpScore(String email,String ip)
    {
        try
        {
            ResultSet rs=DataAccess.GetLastIPaddress(email);
            String lastip=null;
            while(rs.next())
            {
                lastip=rs.getString("userip");
            }
            
            if(ip.equals(lastip))
            {
                return 0;
            }
            else
            {
                rs=DataAccess.GetIPaddressRecords(email);   
                double rec_count=0;
                double matching_count=0;

                while(rs.next())
                {
                    rec_count++;
                    String pre_ip=rs.getString("userip");

                    if(ip.equals(pre_ip))
                    {
                        matching_count++;
                    }
                }
                double unmatching_count=rec_count-matching_count;
                double riskscore=unmatching_count/rec_count;
                return riskscore;
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
        
    }
    public static double calBrowserScore(String email,String browser)
    {
        try
        {
            ResultSet rs=DataAccess.GetLastBrowser(email);
            String lastbrowser=null;
            while(rs.next())
            {
                lastbrowser=rs.getString("browser");
            }
            
            if(browser.equals(lastbrowser))
            {
                return 0;
            }
            else
            {
                rs=DataAccess.GetBrowserRecords(email);  
                double rec_count=0;
                double matching_count=0;

                while(rs.next())
                {
                    rec_count++;
                    String pre_browser=rs.getString("browser");

                    if(browser.equals(pre_browser))
                    {
                        matching_count++;
                    }
                }
                double unmatching_count=rec_count-matching_count;
                double riskscore=unmatching_count/rec_count;
                return riskscore;
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    public static double calDeviceScore(String email,String device)
    {
        try
        {
            ResultSet rs=DataAccess.GetLastDevice(email);
            String lastdevice=null;
            while(rs.next())
            {
                lastdevice=rs.getString("device");
            }
            
            if(device.equals(lastdevice))
            {
                return 0;
            }
            else
            {
                rs=DataAccess.GetDeviceRecords(email);
                double rec_count=0;
                double matching_count=0;

                while(rs.next())
                {
                    rec_count++;
                    String pre_device=rs.getString("device");

                    if(device.equals(pre_device))
                    {
                        matching_count++;
                    }
                }
                double unmatching_count=rec_count-matching_count;
                double riskscore=unmatching_count/rec_count;
                return riskscore;
            }
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    public static double calTimeScore(String email,Date time)
    {
        try
        {
            ResultSet rs=DataAccess.GetLastTime(email);
            double rec_count=0;
            double matching_count=0;
            
            SimpleDateFormat df=new SimpleDateFormat("yy/MM/dd HH:mm:ss");
            Date previous=null;
            Date now=null;
            
            while(rs.next())
            {    
                Date pre_time=rs.getTimestamp("time");
                String pre_time2=df.format(pre_time);
                
                previous = df.parse(pre_time2);
                String nowdate=df.format(time);
                now=df.parse(nowdate);
    
            }
            long diff = now.getTime() - previous.getTime();
            
            int diffInDays = (int) ((now.getTime() - previous.getTime()) / (1000 * 60 * 60 * 24));
            
            if(diffInDays>30)
            {
                return 1;
            }
            else
            {
                rs=DataAccess.GetTimeRecords(email);
                
                while(rs.next())
                {    
                    rec_count++;
                    SimpleDateFormat tf=new SimpleDateFormat("HH:mm:ss");
                    Date pre_time=rs.getTimestamp("time");
                    
                    String pre_time2=tf.format(pre_time);
                    previous = tf.parse(pre_time2);
                    
                    String today_string=tf.format(time);
                    Date today=tf.parse(today_string);
                    //Date today=tf.parse(time);
                    
                    Calendar cal =Calendar.getInstance();
                    cal.setTime(today);
                    cal.add(Calendar.HOUR_OF_DAY, (int) RiskScore.time_range);
                    Date aftertime=cal.getTime();
                    
                    cal.add(Calendar.HOUR_OF_DAY, (int) RiskScore.time_range*(-1));
                    Date beforetime=cal.getTime();
                    
                    
                    if(previous.after(beforetime) && previous.before(aftertime)) 
                    {
                        matching_count++;
                    }
                }
                double unmatching_count=rec_count-matching_count;
                double riskscore=unmatching_count/rec_count;
                return riskscore;
                
            }
  
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    public static double calLocationScore(String email,double latitude,double longitude)
    {
        try
        {
            ResultSet rs=DataAccess.GetLastLocation(email);
            double pre_latitude=0;
            double pre_longitude=0;
            
            while(rs.next())
            {
                pre_latitude=rs.getDouble("latitude");
                pre_longitude=rs.getDouble("longitude");
            }
            
            //Check if the last location is same as now
            if(pre_latitude==latitude && pre_longitude==longitude)
            {
                return 0;
            }
            else 
            {
                double dif_latitude=Math.abs(pre_latitude-latitude);
                double dif_longitude=Math.abs(pre_longitude-longitude);
                
                //Check if last location is in the defined range(country range)
                if(dif_latitude<=RiskScore.latitude_range && dif_longitude<=RiskScore.longitude_range)
                {
                    return 0;
                }
                else
                {
                    double rec_count=0;
                    double matching_count=0;
                    
                    rs=DataAccess.GetLocationRecords(email);
                    while(rs.next())
                    {
                        rec_count++;
                        pre_latitude=rs.getDouble("latitude");
                        pre_longitude=rs.getDouble("longitude");
                        dif_latitude=Math.abs(pre_latitude-latitude);
                        dif_longitude=Math.abs(pre_longitude-longitude);
                        
                        if(dif_latitude<=3 && dif_longitude<=1)
                        {
                            matching_count++;
                        }
                    }
                    double unmatching_count=rec_count-matching_count;
                    double riskscore=unmatching_count/rec_count;
                    return riskscore;
                }
            }
            
            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }
    public static double totalRiskScore(String email,String ip,String browser,String device,
            Date time,double latitude,double longitude)
    {
        assignvalues();
        double ipScore=calIpScore(email,ip);
        double browserScore=calBrowserScore(email,browser);
        double deviceScore=calDeviceScore(email,device);
        double timeScore=calTimeScore(email,time);
        double locationScore=calLocationScore(email,latitude, longitude);
        
        double totscore=(ipScore*RiskScore.ip_weight)+(browserScore*RiskScore.browser_weight)+(deviceScore*RiskScore.device_weight)+
                (timeScore*RiskScore.time_weight)+(locationScore*RiskScore.location_weight);
        
        return totscore;
    }
    
    
}
