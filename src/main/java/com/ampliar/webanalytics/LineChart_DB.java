/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.webanalytics;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Kasuni
 */
public class LineChart_DB extends ApplicationFrame {

    public LineChart_DB(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Date","Number of Views",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
        
        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }
    
    /* Create MySQL Database Connection */
    DBConnection DBcon_l = new DBConnection ();
    Connection connect_l = DBcon_l.connect();
    
    
    
    
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        
        
        return dataset;
    }
    
}
