/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.webanalytics;

import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.core.models.Category;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kasuni
 */
public class PostItemListener implements ListenerService {

    @Override
    public void premethod(HttpServletRequest request) {
        System.out.println("Before posting the advertisement!");
        try
        {
           
            boolean errorfound = false;

            String ad_name = request.getParameter("title");
            String ad_price = request.getParameter("price");
            String category = request.getParameter("Category");
            
            String sub_category = request.getParameter("SubCategory");

            if(ad_name.equals(""))
            {  
                errorfound = true;     
            }
            if(ad_price.equals(""))
            {
                errorfound = true;
            }
            if(category.equals(""))
            {
                errorfound = true;
            }
            if(sub_category.equals(""))
            {
                errorfound = true;
            }

           if(errorfound==true)
           {
               //redirect to the same page with errors
           }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            
        }
          
    }

    @Override
    public void postmethod(HttpServletRequest request) {
        System.out.println("After posting the advertisement!");
        //Get IP Address    
        String ipaddress = request.getRemoteAddr();
        //String ad_id = Integer.toString(id);
        // Get event time.
        DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
        DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String eventdate = dateFormat.format(date);
        String eventtime=timeFormat.format(date);
        
        String ad_name = request.getParameter("title");
        Category cat = new Category(1, request.getParameter("Category"), 1);
        String category=cat.getCategoryName();
        
        //Insert into Database
        new QueryHandeller().AddPostItemEventRecord(ad_name, ipaddress, eventdate, eventtime, category);
        
         //Insert into CSV File
        
       
        String COMMA_DELIMITER = ",";
        String NEW_LINE_SEPERATOR = "\n";
        //String FILE_HEADER ="ad_name,ipaddress,date,time,category";
        try{
            FileWriter fw = new FileWriter("C:\\Users\\DMS\\workspace\\ampliar-demo-app\\src\\main\\resources\\PostItemListener.csv",true);
            //fw.append(FILE_HEADER);
            fw.append(NEW_LINE_SEPERATOR);
            fw.append(ad_name);
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
