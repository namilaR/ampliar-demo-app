/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;

 
import com.ampliar.authenticationmodule.impl.TwitterClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.RequestToken;

/**
 *
 * @author Ishani
 */
public class OAuthTwitterCallbackListener extends HttpServlet {

    private static final long serialVersionUID =1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        TwitterClass tw=new TwitterClass();
        String verifier = tw.getVerifier(request);
            
        String output=tw.getAccessToken(verifier, request);
        response.sendRedirect(request.getContextPath() + "/twitter.jsp");
    }

    

}
