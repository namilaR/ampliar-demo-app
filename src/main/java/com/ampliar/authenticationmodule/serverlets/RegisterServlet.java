/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;


import com.ampliar.authenticationmodule.data.Location;
import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.temp.DataAccess;
import eu.bitwalker.useragentutils.Browser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;


/**
 *
 * @author Ishani
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        request.getRequestDispatcher("register.jsp").forward(request, response);
        //response.sendRedirect(request.getContextPath() + "/register.jsp");
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
        User user=new User();
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setSec_question(request.getParameter("sec_question"));
        user.setSec_answer(request.getParameter("answer"));
        user.setUser_type("Customer");
        user.setStatus("Active");
        user.setAuthenticator("Local");
        
        int id=DataAccess.AddUser(user);
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        OperatingSystem agent = userAgent.getOperatingSystem();
        Browser browser=userAgent.getBrowser();
        
        if(id>0)
        {
            LoginInfo info=new LoginInfo();
            info.setEmail(request.getParameter("email"));
            String ip=info.getIPaddress(request);
            info.setUserip(ip);
            info.setDevice(agent.getDeviceType().getName());
            info.setBrowser(browser.getName());
            Location l=Location.getLocation(info.getUserip());
            double latitude=Double.parseDouble(l.getLatitude());
            double longitude=Double.parseDouble(l.getLongitude());
            info.setLatitude(latitude);
            info.setLongitude(longitude);
            info.setStatus("success");
            
            int info_id=DataAccess.AddLoginInfo(info);
            if(info_id>0)
            {
                request.getSession().setAttribute("email", user.getEmail());
                response.sendRedirect(request.getContextPath() + "/home.jsp");
            }
        }
        
    }

   

}
