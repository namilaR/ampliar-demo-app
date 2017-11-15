/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ampliar.webanalytics;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.DefaultPieDataset;



/**
 *
 * @author Kasuni
 */
public class PieChart_DB {
    public static void main( String[ ] args )throws Exception {
      
      String viewpages[] = {
         "HOME PAGE",   
         "SELL",   
         "LIST",            
        
      };
      
      /* Create MySQL Database Connection */
      /*Class.forName( "com.mysql.jdbc.Driver" );
      Connection connect = DriverManager.getConnection( 
         "jdbc:mysql://localhost:3306/jf_testdb" ,     
         "root",     
         "root123");*/
      
      DBConnection DBcon = new DBConnection ();
      Connection connect = DBcon.connect();
      
      
      Statement statement = connect.createStatement( );
      ResultSet resultSet = statement.executeQuery("select page,COUNT(*) AS count from page_views group by page" );
      DefaultPieDataset dataset = new DefaultPieDataset( );
      
      while( resultSet.next( ) ) {
         dataset.setValue( 
         resultSet.getString( "page" ) ,
         Double.parseDouble( resultSet.getString( "count" )));
      }
      
      JFreeChart chart = ChartFactory.createPieChart(
         "Page Views",   // chart title           
         dataset,          // data           
         true,             // include legend          
         true,           
         false );

      int width = 560;    /* Width of the image */
      int height = 370;   /* Height of the image */ 
      File pieChart = new File( "Pie_Chart.jpeg" );
      ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
   }
    
}
