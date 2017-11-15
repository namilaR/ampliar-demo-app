/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.webanalytics;


import com.ampliar.webanalytics.PostItemListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kasuni
 */
@WebServlet(name = "PostItemServlet", urlPatterns = {"/PostItemServlet"})
public class PostItemServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostItemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        
        PostItemListener pl = new PostItemListener ();
        
        boolean error = pl.premethod(request);
        
        String ad_name = request.getParameter("ad_name");
        String ad_price = request.getParameter("ad_price");
        String category = "Cars and vehicles";
        String description = request.getParameter("description");
        //String sub_category = request.getParameter("sub_category"); 
        int ad_id =0;
        if(error == false)
        { 
         PreparedStatement pst = null;
         ResultSet resultSet = null;
         
        try {
            
            String q = "insert into ads(ad_name,ad_price,description,status) values('" + ad_name + "','" + ad_price + "','" + description + "','1')";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            //Statement st = con.createStatement();
           pst = con.prepareStatement(q,Statement.RETURN_GENERATED_KEYS);
           int  i  =pst.executeUpdate();
           if(i==1)
           {
               resultSet=pst.getGeneratedKeys();
               while(resultSet.next())
               {
                   ad_id = resultSet.getInt(1);
                   String id = Integer.toString(ad_id);
               }
               
           }
           
            //int i = st.executeUpdate("insert into ads(ad_name,ad_price,description,status) values('" + ad_name + "','" + ad_price + "','" + description + "',1)");
           //Statement st1 = con.createStatement();
           // resultSet = st1.executeQuery("select * from ads order by id DESC LIMIT 1");

// New location to be redirected
            //String site = new String("/ampliar/views/list.jsp");
            //response.setStatus(response.SC_MOVED_TEMPORARILY);
            //response.setHeader("Location", site);
            } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
            }
        
    }
        else 
        {
        request.getRequestDispatcher("/views/sell.jsp").forward(request, response);
        }
     //Get IP Address    
    String ipaddress = request.getRemoteAddr();
    //String ad_id = Integer.toString(id);
    // Get event time.
    DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
    DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    String eventdate = dateFormat.format(date);
    String eventtime=timeFormat.format(date);
        
    request.setAttribute("ad_id",ad_id);
    request.setAttribute("ad_name", ad_name);
    request.setAttribute("ipaddress", ipaddress);
    request.setAttribute("eventdate", eventdate);
    request.setAttribute("eventtime", eventtime);
    request.setAttribute("category", category);
        
    pl.postmethod(request);
        
        request.getRequestDispatcher("/views/list.jsp").forward(request, response);
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
