/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;


import com.ampliar.authenticationmodule.data.Location;
import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.temp.DataAccess;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishani
 */
public class LoginServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            String email=request.getParameter("email");
            String password=request.getParameter("password");

            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            OperatingSystem agent = userAgent.getOperatingSystem();
            Browser browser=userAgent.getBrowser();

            if(DataAccess.CheckEmailExists(email))
            {
                LoginInfo info=new LoginInfo();
                info.setEmail(request.getParameter("email"));
                //String ip=info.getIPaddress(request);
                String ip="110.120.100.80";
                info.setUserip(ip);
                info.setDevice(agent.getDeviceType().getName());
                info.setBrowser(browser.getName());
                Location l=Location.getLocation(info.getUserip());
                double latitude=Double.parseDouble(l.getLatitude());
                double longitude=Double.parseDouble(l.getLongitude());
                info.setLatitude(latitude);
                info.setLongitude(longitude);

                Date nowdate=new Date();
                SimpleDateFormat df=new SimpleDateFormat("yy/MM/dd HH:mm:ss");
                String now=df.format(nowdate);

                if(DataAccess.Login(email, password)==true)
                {
                    double risk_score=RiskScore.totalRiskScore(email, ip, info.getBrowser(), info.getDevice(), nowdate, latitude, longitude);
                    if(risk_score<0.5)
                    {
                        info.setStatus("success");
                        int info_id=DataAccess.AddLoginInfo(info);
                        if(info_id>0)
                        {
                            request.getSession().setAttribute("email", email);
                            response.sendRedirect(request.getContextPath() + "/home.jsp");
                        }

                    }
                    else if(risk_score>=0.5 && risk_score<0.8)
                    {
                        request.getSession().setAttribute("info",info );
                        
                        response.sendRedirect(request.getContextPath() + "/Verification");
                    }
                    else
                    {
                        request.getSession().setAttribute("info",info );
                        response.sendRedirect(request.getContextPath() + "/OTPVerification");
                    }

                }
                else
                {
                    info.setStatus("failure");
                    double risk_score=RiskScore.totalRiskScore(email, ip, info.getBrowser(), info.getDevice(), nowdate, latitude, longitude);
                    if(risk_score>0.8)
                    {
                        String subject="Security alert";
                        String body="Dear "+email+","
				+ "\n\n Someone tried to logged into your account "
                                +"\n Browser: "+info.getBrowser()+"\n Device: "+info.getDevice()+"\n Country: "+l.getCountryName()+"\n City: "+l.getCity();
                        
                        int info_id=DataAccess.AddLoginInfo(info);
                        if(info_id>0)
                        {
                            SendEmail.Send(email,subject , body);
                        }
                    }
                    
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    

}
