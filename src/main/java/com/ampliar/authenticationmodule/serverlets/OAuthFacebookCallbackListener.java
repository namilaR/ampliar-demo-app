/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.serverlets;
import com.ampliar.authenticationmodule.util.FileOperations;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import com.ampliar.authenticationmodule.impl.*;

/**
 *
 * @author Ishani
 */
public class OAuthFacebookCallbackListener extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FileOperations fileOperations = new FileOperations();
        
        Facebook fb=new Facebook();
        String authorizationCode = fb.getAuthorizationcode(request);
        
        if (authorizationCode != null && authorizationCode.length() > 0) {
            /*final String TOKEN_ENDPOINT =
                    "https://graph.facebook.com/oauth/access_token";
            final String GRANT_TYPE = "authorization_code";
            final String REDIRECT_URI = "http://localhost:8080/TestProject/Facebookcallback";
            final String CLIENT_ID = "1741364576163534";
            final String CLIENT_SECRET = "d23e5754aa74dad41e8eebfe16f3b764";*/
            // Generate POST request
            HttpPost httpPost = new HttpPost(Facebook.TOKEN_ENDPOINT +
                    "?grant_type=" + URLEncoder.encode(Facebook.GRANT_TYPE,
                    StandardCharsets.UTF_8.name()) +
                    "&code=" + URLEncoder.encode(authorizationCode,
                    StandardCharsets.UTF_8.name()) +
                    "&redirect_uri=" + URLEncoder.encode(Facebook.REDIRECT_URI,
                    StandardCharsets.UTF_8.name()) +
                    "&client_id=" + URLEncoder.encode(Facebook.CLIENT_ID,
                    StandardCharsets.UTF_8.name()));
// Add "Authorization" header with encoded client credentials
            fb.addAuthorizationHeader(httpPost,Facebook.CLIENT_ID,Facebook.CLIENT_SECRET);
// Make the access token request
            HttpResponse httpResponse=fb.makeAccessTokenRequest(httpPost);
// Handle acscess token response
            String line=fb.handleAccessTokenRequest(httpResponse);
// Isolate access token
            String accessToken=fb.getAccessToken(line,null);
            // Request profile and feed data with access token
// Request feed data with access token
            String requestUrl =
                    "https://graph.facebook.com/v2.9/me?fields=id,name,gender,link,email";
            fb.getprofiledetails(request,requestUrl, accessToken);
            
            //FacebookPojo data = (FacebookPojo)new Gson().fromJson(feedJson, FacebookPojo.class);
            //httpClient.close();

            //Generate a unique key
            int randomNum = ThreadLocalRandom.current().nextInt(1, 9999999);
            //Add the facebook response data to the resources map along with the key
            //ResourceDataHolder.getInstance().addResource(String.valueOf(randomNum), feedJson);

            //fileOperations.writeToFile(feedJson);

            //request.setAttribute("user_resource", feedJson);

            //request.getRequestDispatcher("userdata.jsp").forward(request, response);

            
            request.getRequestDispatcher("/facebook.jsp").forward(request, response);
            //response.sendRedirect("userdata.jsp?key=" + String.valueOf(randomNum));

        } else {
// Handle failure
        }
    }



}
