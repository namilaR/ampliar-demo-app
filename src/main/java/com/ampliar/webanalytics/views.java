/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ampliar.webanalytics;

import java.io.FileWriter;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 *
 * @author Kasuni
 */
public class views {
    
    // tracking Session details
    public void  pageviews(String ip, String date,String time,String page)
    {
            
        
        // Insert into a CSV file
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPERATOR = "\n";
        //String FILE_HEADER ="ipaddress,date,time,page";
        try{
            FileWriter fw = new FileWriter("C:\\Users\\DMS\\workspace\\ampliar-demo-app\\src\\main\\resources\\PageViews.csv",true);
            //fw.append(FILE_HEADER);
            fw.append(NEW_LINE_SEPERATOR);
            fw.append(ip);
            fw.append(COMMA_DELIMITER);
            fw.append(date);
            fw.append(COMMA_DELIMITER);
            fw.append(time);
            fw.append(COMMA_DELIMITER);
            fw.append(page);
         
            fw.flush();
            fw.close();
            //BufferedWriter bw = new BufferedWriter(fw);
            //PrintWriter pw = new PrintWriter(bw);
            
            //pw.println(id+","+ip+","+date);
            //pw.flush();
            //pw.close();
        
        }
        catch(Exception e){
         e.printStackTrace();
        } 

    }
    
    //tracking no of active sessions
    
    public void get_active_sessions (int count)
    {
        
    }
    
    
}
