/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.webanalytics;

import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.demo.models.Mobile;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Kasuni
 */
public class GetItemListener implements ListenerService {
    

    @Override
    public void premethod(HttpServletRequest request) {
        System.out.println("logBefore() is running!");
    }

    @Override
    public void postmethod(HttpServletRequest request) {
            System.out.println("logAfter() is running!");
            //Get Client IP Address.
            String ipaddress = request.getRemoteAddr();

            // Get event time.
            DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
            DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            String eventdate = dateFormat.format(date);
            String eventtime=timeFormat.format(date);
            
            //Returned string
            String returnString=(String) request.getAttribute("returnString");
            Mobile data = (Mobile)new Gson().fromJson(returnString, Mobile.class);
            int id=data.getAdvertismentId();
            String category=request.getAttribute("category").toString();
            String time=request.getAttribute("eventtime").toString();
        // Insert into database
        new QueryHandeller().AddGetItemEventRecord(id,ipaddress, eventdate, eventtime, category);
       
        

        //Insert into CSV File
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPERATOR = "\n";
        //String FILE_HEADER ="ad_id,ipaddress,date,time,category";
        try{
            FileWriter fw = new FileWriter("D:\\SLIIT\\Year 04\\CDAP\\Analytics\\GetItemListener.csv",true);
            //fw.append(FILE_HEADER);
            fw.append(NEW_LINE_SEPERATOR);
            fw.append(Integer.toString(id));
            fw.append(COMMA_DELIMITER);
            fw.append(ipaddress);
            fw.append(COMMA_DELIMITER);
            fw.append(eventdate);
            fw.append(COMMA_DELIMITER);
            fw.append(eventtime);
            fw.append(COMMA_DELIMITER);
            fw.append(category);
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
    
}
