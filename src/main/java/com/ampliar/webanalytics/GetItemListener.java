/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.webanalytics;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author Kasuni
 */
public class GetItemListener implements Listener {
    

    @Override
    public boolean premethod(HttpServletRequest request) {
        boolean efforfound = false;
        return efforfound;
    }

    @Override
    public void postmethod(HttpServletRequest request) {
        
            String id=request.getAttribute("ad_id").toString();
            String ip=request.getAttribute("ipaddress").toString();
            String date=request.getAttribute("eventdate").toString();
            String category=request.getAttribute("category").toString();
            String time=request.getAttribute("eventtime").toString();
        // Insert into database
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
           
            int i = st.executeUpdate("insert into getitem_listener(ad_id,ipaddress,date,time,category) values('" + id + "','" + ip + "','"+date+"','"+time+"','"+category+"')");
          
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
         }
       
        //Insert into CSV File
        
       
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPERATOR = "\n";
        //String FILE_HEADER ="ad_id,ipaddress,date,time,category";
        try{
            FileWriter fw = new FileWriter("D:\\SLIIT\\Year 04\\CDAP\\Analytics\\GetItemListener.csv",true);
            //fw.append(FILE_HEADER);
            fw.append(NEW_LINE_SEPERATOR);
            fw.append(id);
            fw.append(COMMA_DELIMITER);
            fw.append(ip);
            fw.append(COMMA_DELIMITER);
            fw.append(date);
            fw.append(COMMA_DELIMITER);
            fw.append(time);
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
