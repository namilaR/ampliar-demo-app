/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ishani
 */
public class DBConnection {
    String sourceURL;
    public DBConnection(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
    
        sourceURL=new String("jdbc:mysql://localhost:3306/ampliar_demo");
    }
    catch (ClassNotFoundException e){
        JOptionPane.showMessageDialog(null, e);
    }
    }
    
    public Connection connect(){
        Connection dbConn=null;
        
        try{
            dbConn=DriverManager.getConnection(sourceURL,"root","1234");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e+" DB connection failure");
        }
        return dbConn;
    }
    
    public void con_close(Connection dbConn){
        try{
            dbConn.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,e+" Db connection closing failure");
        }
    }
}
