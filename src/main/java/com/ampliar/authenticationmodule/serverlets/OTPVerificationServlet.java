/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets; 
import com.ampliar.authenticationmodule.data.LoginInfo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import com.ampliar.temp.DataAccess;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishani
 */
public class OTPVerificationServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
        String otp=Arrays.toString(GenerateOTP.OTP(6));
        
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for(char c : otp.toCharArray()){
            if(Character.isDigit(c)){
                sb.append(c);
                found = true;
            } 
        }
        otp=sb.toString();
        String email=info.getEmail();
        String subject="One time password";
        String body="Dear "+email+","
				+ "\n\n Use this 6-digit code to login to your account "+otp;
        
        request.getSession().setAttribute("otp",otp );
        //SendEmail.Send(email, subject, body);
        request.getRequestDispatcher("OTPVerification.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            
            String code=request.getParameter("code");
            String otp=(String)request.getSession().getAttribute("otp");
            
            if(otp.equals(code))
            {
                info.setStatus("success");
                int info_id=DataAccess.AddLoginInfo(info);
                if(info_id>0)
                {
                    request.getSession().setAttribute("email", info.getEmail());
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
