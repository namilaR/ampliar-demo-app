/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ampliar.authenticationmodule.impl;
import com.ampliar.authenticationmodule.data.FacebookPojo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.core.authenticationmodule.Authentication;
import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.temp.DataAccess;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Ishani
 */
public class Facebook extends Authentication{
    public static String GRANT_TYPE;
    public static String RESPONSE_TYPE;
    public static String SCOPE;
    public static String TOKEN_ENDPOINT;
    public static String AUTH_ENDPOINT;
    public static String CLIENT_ID ;
    public static String CLIENT_SECRET;
    public static String REDIRECT_URI;
    public static String ENABLE;
    
    public Facebook()
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
                        
                        if(connectorname.equals("Facebook"))
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
        return request.getParameter("code");
    }
    public void addAuthorizationHeader(HttpPost httpPost,String CLIENT_ID,String CLIENT_SECRET)
    {
        String clientCredentials = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedClientCredentials =
                    new String(Base64.encodeBase64
                            (clientCredentials.getBytes()));
            httpPost.setHeader("Authorization", "Basic " +
                    encodedClientCredentials);
    }
    public HttpResponse makeAccessTokenRequest(HttpPost httpPost) throws IOException
    {
        CloseableHttpClient httpClient =
                    HttpClients.createDefault();
            HttpResponse httpResponse = httpClient.execute(httpPost);
            return httpResponse;
    }
    public String handleAccessTokenRequest(HttpResponse httpResponse) throws IOException
    {
        Reader reader = new InputStreamReader
                    (httpResponse.getEntity().getContent());
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            return line;
    }
    @Override
    public String getAccessToken(String line,HttpServletRequest request) {
        String accessToken = null;
            String[] responseProperties = line.split("&");
            for (String responseProperty : responseProperties) {
                System.out.println(responseProperty);
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(responseProperty);
                    JSONObject jsonobj = (JSONObject) obj;
                    accessToken = jsonobj.get("access_token").toString();
                    System.out.println("Access token: " + accessToken);

                    //fileOperations.writeToFile(accessToken);

                } catch (ParseException e) {
                    System.out.println("Error while parsing the response from facebook : " + e.getMessage());
                }

            }
            return accessToken;
    }
    @Override
    public void getprofiledetails(HttpServletRequest request, String FacebookUserInfoEndpoint, String accessToken) {
            HttpPost httpPost = new HttpPost(FacebookUserInfoEndpoint);
            httpPost.addHeader("Authorization", "Bearer " + accessToken);
            List<NameValuePair> urlParameters = new
                    ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("method", "get"));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
                CloseableHttpClient httpClient =
                        HttpClients.createDefault();
                HttpResponse httpResponse = httpClient.execute(httpPost);
                //httpResponse = httpClient.execute(httpPost);
    // Extract feed data from response
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(httpResponse.getEntity().getContent()));
                String feedJson = bufferedReader.readLine();
                FacebookPojo data = (FacebookPojo)new Gson().fromJson(feedJson, FacebookPojo.class);
                httpClient.close();
                String email=data.getEmail();
                request.getSession().setAttribute("email", email);
                request.setAttribute("auth", data);
                createuser(request, "Facebook");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Facebook.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Facebook.class.getName()).log(Level.SEVERE, null, ex);
            }
    }   

    @Override
    public void createuser(HttpServletRequest request, String authenticator) {
        String email=(String) request.getSession().getAttribute("email");
        if(!new QueryHandeller().CheckFederatedUserExists(email, authenticator))
        {
            User user=new User();
            user.setEmail(email);
            user.setAuthenticator(authenticator);
            int id=new QueryHandeller().AddUser(user);
        }
    }
}
