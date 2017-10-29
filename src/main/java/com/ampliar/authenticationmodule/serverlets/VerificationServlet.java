/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;

 
import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.temp.DataAccess;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishani
 */
public class VerificationServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            ResultSet rs=DataAccess.GetSecurityQuestion(info.getEmail());
            String question=null;
            while(rs.next())
            {
                question=rs.getString("SecurityQuestion");
            }
            request.setAttribute("question", question);
            request.getRequestDispatcher("verification.jsp").forward(request, response);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            String answer=request.getParameter("answer");
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            
            if(DataAccess.CheckSecurityAnswer(info.getEmail(), answer))
            {
                info.setStatus("success");
                int info_id=DataAccess.AddLoginInfo(info);
                if(info_id>0)
                {
                    request.getSession().setAttribute("email", info.getEmail());
                    response.sendRedirect(request.getContextPath() + "/home.jsp");
                }
            }
            else
            {
                info.setStatus("failure");
                int info_id=DataAccess.AddLoginInfo(info);
                if(info_id>0)
                {
                    response.sendRedirect(request.getContextPath() + "/home.jsp");
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    

}
