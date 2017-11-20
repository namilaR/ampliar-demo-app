/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ampliar.authenticationmodule.impl;
import com.ampliar.authenticationmodule.data.GooglePojo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.authenticationmodule.util.GsonUtility;
import com.ampliar.core.authenticationmodule.Authentication;
import com.ampliar.temp.DataAccess;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Ishani
 */
public class Google extends Authentication{
    public static String GRANT_TYPE;
    public static String RESPONSE_TYPE;
    public static String SCOPE;
    public static String APPROVAL_PROMPT;
    public static String TOKEN_ENDPOINT;
    public static String AUTH_ENDPOINT;
    public static String CLIENT_ID ;
    public static String CLIENT_SECRET;
    public static String REDIRECT_URI;
    public static String ENABLE;
    
    public Google()
    {
        //assignvalues();
    }
    public static void assignvalues()
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();

            Document doc=builder.parse("http://localhost:8080/ampliar-demo-app/resources/config/config.xml");

            NodeList connectorslist = doc.getElementsByTagName("connectors");
            for (int i = 0; i < connectorslist.getLength(); ++i)
            {
                //NodeList single=connectors.getElementsByTagName("connector");
                Element connectors = (Element) connectorslist.item(i);
                //String connectorname = singleconnector.getAttribute("name");
                
                
                NodeList connectorlist = connectors.getElementsByTagName("connector");
                for (int j = 0; j < connectorlist.getLength(); ++j)
                {
                    Element connector = (Element) connectorlist.item(j);
                    
                    Node node=connectorlist.item(j);
                    if(node.getNodeType()==Node.ELEMENT_NODE)
                    {
                        Element element=(Element) node;
                        String connectorname = element.getAttribute("name");
                        //String enabledattr = element.getAttribute("enable");
                        
                        if(connectorname.equals("Google"))
                        {
                            ENABLE = element.getElementsByTagName("enable").item(0).getTextContent();
                            CLIENT_ID=element.getElementsByTagName("clientid").item(0).getTextContent();
                            CLIENT_SECRET=element.getElementsByTagName("clientsecret").item(0).getTextContent();
                            TOKEN_ENDPOINT=element.getElementsByTagName("token_endpoint").item(0).getTextContent();
                            REDIRECT_URI=element.getElementsByTagName("client_endpoint").item(0).getTextContent();
                            GRANT_TYPE=element.getElementsByTagName("grant_type").item(0).getTextContent();
                            AUTH_ENDPOINT=element.getElementsByTagName("authorization_endpoint").item(0).getTextContent();
                            RESPONSE_TYPE=element.getElementsByTagName("response_type").item(0).getTextContent();
                            SCOPE=element.getElementsByTagName("scope").item(0).getTextContent();
                            APPROVAL_PROMPT=element.getElementsByTagName("approval_prompt").item(0).getTextContent();
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
    @Override
    public String getAuthorizationcode(HttpServletRequest request) {
        String code = request.getParameter("code");
        return code;
    }
    
    public String makeAccessTokenRequest(String urlParameters,String tokenEndPoint) throws IOException
    {
        URL url = new URL(tokenEndPoint);
        URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(
                  conn.getOutputStream());
                writer.write(urlParameters);
                writer.flush();
                String line1 = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                  conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null)
                {
                  line1 = line1 + line;
                }
        
            return line1;
    }
    
    @Override
    public String getAccessToken(String line,HttpServletRequest request) {
            String accessToken = GsonUtility.getJsonElementString("access_token", line);
            return accessToken;
      
    }

    @Override
    public void getprofiledetails(HttpServletRequest request, String GoogleUserInfoEndpoint, String accessToken) {
        try
        {
                URL url = new URL(GoogleUserInfoEndpoint + accessToken);
                URLConnection conn = url.openConnection();
                String line1 = "";
                String line="";
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                  conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                  line1 = line1 + line;
                }
                GooglePojo data = (GooglePojo)new Gson().fromJson(line1, GooglePojo.class);
                String email=data.getEmail();
                request.getSession().setAttribute("email", email);
                
                reader.close();
                request.setAttribute("auth", data);
                createuser(request, "Google");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
                
    }

    @Override
    public void createuser(HttpServletRequest request, String authenticator) {
        String email=(String) request.getSession().getAttribute("email");
        if(!DataAccess.CheckFederatedUserExists(email, authenticator))
        {
            User user=new User();
            user.setEmail(email);
            user.setAuthenticator(authenticator);
            int id=DataAccess.AddUser(user);
        }
    }
    
}
