/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ishani.oauth.testproject.servlets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishani
 */
public class AdaptiveSettingsServlet extends HttpServlet {

    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Properties prop = new Properties();
	InputStream input = null;

	try {

		input = new FileInputStream("G:\\Home-work\\ST2\\Netbeans\\TestProject\\src\\main\\webapp\\config\\adaptiveconfig.properties");

		// load a properties file
		prop.load(input);

		// get the property value
//                double ip_weight=Double.parseDouble(prop.getProperty("ip_weight"));
//                double browser_weight=Double.parseDouble(prop.getProperty("browser_weight"));
//		double device_weight=Double.parseDouble(prop.getProperty("device_weight"));
//                double time_weight=Double.parseDouble(prop.getProperty("time_weight"));
//                double location_weight=Double.parseDouble(prop.getProperty("location_weight"));
//                double time_range=Double.parseDouble(prop.getProperty("time_range"));
//                double latitude_range=Double.parseDouble(prop.getProperty("latitude_range"));
//                double longitude_range=Double.parseDouble(prop.getProperty("longitude_range"));
                
                request.setAttribute("prop", prop);
                
                request.getRequestDispatcher("developersettings.jsp").forward(request, response);

	} catch (IOException ex) {
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

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            double ip_weight=Double.parseDouble(request.getParameter("ip_weight"))/100;
            double browser_weight=Double.parseDouble(request.getParameter("browser_weight"))/100;
            double device_weight=Double.parseDouble(request.getParameter("device_weight"))/100;
            double time_weight=Double.parseDouble(request.getParameter("time_weight"))/100;
            double location_weight=Double.parseDouble(request.getParameter("location_weight"))/100;
            double time_range=Double.parseDouble(request.getParameter("time_range"));
            double latitude_range=Double.parseDouble(request.getParameter("latitude_range"));
            double longitude_range=Double.parseDouble(request.getParameter("longitude_range"));
            
            Properties prop = new Properties();
            OutputStream output = null;
            output = new FileOutputStream("G:\\Home-work\\ST2\\Netbeans\\TestProject\\src\\main\\webapp\\config\\adaptiveconfig.properties");

            // set the properties value
            prop.setProperty("ip_weight", Double.toString(ip_weight));
            prop.setProperty("browser_weight", Double.toString(browser_weight));
            prop.setProperty("device_weight", Double.toString(device_weight));
            prop.setProperty("time_weight", Double.toString(time_weight));
            prop.setProperty("location_weight", Double.toString(location_weight));
            prop.setProperty("time_range", Double.toString(time_range));
            prop.setProperty("latitude_range", Double.toString(latitude_range));
            prop.setProperty("longitude_range", Double.toString(longitude_range));
            
            // save properties to project root folder
            prop.store(output, null);
            response.sendRedirect(request.getContextPath() + "/AdaptiveSettings");

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    

}
