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
public class PostItemListener implements Listener {

    @Override
    public boolean premethod(HttpServletRequest request) {
        
        try
        {
           
            boolean errorfound = false;

            String ad_name = request.getParameter("ad_name");
            String ad_price = request.getParameter("ad_price");
            String category = request.getParameter("category-select");
            String description = request.getParameter("description");
            String sub_category = request.getParameter("sub_category-select");

            if(ad_name.equals(""))
            {  
                errorfound = true;     
            }
            if(ad_price.equals(""))
            {
                errorfound = true;
            }
            if(description.equals(""))
            {
                errorfound = true;
            }

           return errorfound;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
          
    }

    @Override
    public void postmethod(HttpServletRequest request) {
        
        int id=Integer.parseInt(request.getAttribute("ad_id").toString());
        String ad_name = request.getAttribute("ad_name").toString();
        String ip=request.getAttribute("ipaddress").toString();
        String date=request.getAttribute("eventdate").toString();
        String time=request.getAttribute("eventtime").toString();
        String category="Cars and vehicles";
        
        try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            Statement st = con.createStatement();
           
            int i = st.executeUpdate("insert into postitem_listener(ad_id,ad_name,ip,date,time,category) values('"+id+"','" + ad_name + "','"+ip+"','"+date+"','"+time+"','"+category+"')");
          
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
         }
        
         //Insert into CSV File
        
       
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPERATOR = "\n";
        //String FILE_HEADER ="ad_id,ad_name,ipaddress,date,time,category";
        try{
            FileWriter fw = new FileWriter("D:\\SLIIT\\Year 04\\CDAP\\Analytics\\PostItemListener.csv",true);
            //fw.append(FILE_HEADER);
            fw.append(NEW_LINE_SEPERATOR);
            fw.append((char) id);
            fw.append(COMMA_DELIMITER);
            fw.append(ad_name);
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
