/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;

 
import com.ampliar.authenticationmodule.impl.Linkedin;
import com.ampliar.authenticationmodule.util.OAuthTLSUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ishani
 */
public class OAuthLinkedinCallbackListener extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private final String linkedInUserInfoEndpoint = "https://api.linkedin.com/v1/people/~:(first-name,last-name,email-address,picture-url,public-profile-url,summary,industry)?format=json";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Linkedin lkd=new Linkedin();
        
        
        // Detect the presence of an authorization code
        String authorizationCode = lkd.getAuthorizationcode(request);
        if (authorizationCode != null && authorizationCode.length() > 0) {


            // Generate POST request

            String url = Linkedin.TOKEN_ENDPOINT
                    + "?grant_type=" + URLEncoder.encode(Linkedin.GRANT_TYPE, StandardCharsets.UTF_8.name())
                    + "&code=" + URLEncoder.encode(authorizationCode, StandardCharsets.UTF_8.name())
                    + "&redirect_uri=" + URLEncoder.encode(Linkedin.REDIRECT_URI, StandardCharsets.UTF_8.name())
                    + "&client_id=" + URLEncoder.encode(Linkedin.CLIENT_ID, StandardCharsets.UTF_8.name())
                    + "&client_secret=" + URLEncoder.encode(Linkedin.CLIENT_SECRET, StandardCharsets.UTF_8.name());

            Map<String, String> requestProps = new HashMap<String, String>();
            requestProps.put("Content-Type", "application/x-www-form-urlencoded");

            String output = OAuthTLSUtil.sendRequest(url, null, requestProps, "POST");

            String accessToken = lkd.getAccessToken(output,null);

            lkd.getprofiledetails(request,linkedInUserInfoEndpoint, accessToken);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
            

        } else {
            // Handle failure
        }

    }
    

}
