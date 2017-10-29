/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;

import com.ampliar.authenticationmodule.impl.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishani
 */
public class OAuthGoogleCallbackListener extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try
        {
        
            Google google=new Google();
            String authorizationCode = google.getAuthorizationcode(request);
            if (authorizationCode != null && authorizationCode.length() > 0) {
            
                String urlParameters = "code=" + 
                  URLEncoder.encode(authorizationCode, StandardCharsets.UTF_8.name()) + 
                  "&client_id=" + URLEncoder.encode(Google.CLIENT_ID, StandardCharsets.UTF_8.name()) +
                  "&client_secret=" + URLEncoder.encode(Google.CLIENT_SECRET, StandardCharsets.UTF_8.name()) + 
                  "&redirect_uri=" + URLEncoder.encode(Google.REDIRECT_URI, StandardCharsets.UTF_8.name()) +
                  "&grant_type="+URLEncoder.encode(Google.GRANT_TYPE, StandardCharsets.UTF_8.name());
                
                // Make the access token request
                    String line=google.makeAccessTokenRequest(urlParameters, Google.TOKEN_ENDPOINT);
                
                // Isolate access token
                String accessToken=google.getAccessToken(line,null);
                
                //get profile details
                String requestUrl =
                    "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
                google.getprofiledetails(request, requestUrl, accessToken);
                request.getRequestDispatcher("/google.jsp").forward(request, response);
                
            }
            else
            {
                // Handle failure
            }
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (ProtocolException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    }
    

}
