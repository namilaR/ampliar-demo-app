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
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Ishani
 */
public class TwitterSigninServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        TwitterClass cl=new TwitterClass();
        //Properties props = new TwitterProperties().getProperties();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey(TwitterClass.CLIENT_ID)
        .setOAuthConsumerSecret(TwitterClass.CLIENT_SECRET)
        .setOAuthRequestTokenURL(TwitterClass.REQUEST_TOKEN_ENDPOINT)
        .setOAuthAuthorizationURL(TwitterClass.AUTH_ENDPOINT)
        .setOAuthAccessTokenURL(TwitterClass.TOKEN_ENDPOINT);
        cb.setIncludeEmailEnabled(true);
        
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
           
        request.getSession().setAttribute("twitter", twitter);
        
        
        RequestToken requestToken=cl.getRequestToken(request,twitter);
        response.sendRedirect(requestToken.getAuthenticationURL());
    }


}
