/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.webanalytics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kasuni
 */
public class DBHandler {
    DBConnection pDBConn =null;
    
    public DBHandler()
    {
        pDBConn = new DBConnection();
    }
    public ResultSet getItems()
    {
     Connection dbConn=pDBConn.connect();
    String query = "SELECT * from ads";
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            
            ResultSet reSet = statement.executeQuery();
            return reSet;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            return null;
        }
        
    }
    public ResultSet getItem(String ID){
        Connection dbConn=pDBConn.connect();
        String query = "SELECT * from ads where id="+ID;
        
        try {
            PreparedStatement statement = dbConn.prepareStatement(query);
            
            ResultSet reSet1 = statement.executeQuery();
            return reSet1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex);
            return null;
        }
    
    }
}
