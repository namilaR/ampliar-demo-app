/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.core.webanalytics;


import com.ampliar.webanalytics.GetItemListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kasuni
 */
@WebServlet(name = "GetItemServlet", urlPatterns = {"/GetItemServlet"})
public class GetItemServlet extends HttpServlet {
    
    
   
  

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
            out.println("<title>Servlet GetItemServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetItemServlet at " + request.getContextPath() + "</h1>");
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
        
        String ad_id = request.getParameter("ad_id");
        
        
       // processRequest(request, response);
        
        
      GetItemListener gl = new GetItemListener();
       
        
        gl.premethod(request);// Pre Event  of the Actual Event
        
        // Actual Event
        //response.setContentType("text/html; charset=utf-8");

       String category=null; 
       try {
            ResultSet resultSet = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/amplier", "root", "");
            
            Statement st = con.createStatement();
            resultSet = st.executeQuery("select * from ads where id='"+ad_id+"'");
            while (resultSet.next()) {
                category=resultSet.getString("category");
            }
            
       
        } catch (Exception e) {
            System.out.print(e);
            e.printStackTrace();
        }
    
    
    //Get Client IP Address.
    String ipaddress = request.getRemoteAddr();
    
    // Get event time.
    DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd");
    DateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
    String eventdate = dateFormat.format(date);
    String eventtime=timeFormat.format(date);
    
    request.setAttribute("ad_id", ad_id);
    request.setAttribute("ipaddress", ipaddress);
    request.setAttribute("eventdate", eventdate);
    request.setAttribute("eventtime", eventtime);
    request.setAttribute("category", category);
        
    gl.postmethod(request);
         
        //ServletContext sc  = this.getServletContext();
        //request.getAttribute("resultSet");
        //RequestDispatcher rd = sc.getRequestDispatcher("/views/ad_details.jsp");
        //rd.forward(request, response)
        
        
        request.setAttribute("ad_id", ad_id);
        request.getRequestDispatcher("/views/ad_details.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
