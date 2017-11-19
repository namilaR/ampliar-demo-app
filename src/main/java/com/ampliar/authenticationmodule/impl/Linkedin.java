/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.impl; 
import com.ampliar.authenticationmodule.data.LinkedinPojo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.authenticationmodule.util.OAuthTLSUtil;
import com.ampliar.core.authenticationmodule.Authentication;
import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.temp.DataAccess;
import com.google.gson.Gson;
 
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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
public class Linkedin extends Authentication{
    public static String GRANT_TYPE;
    public static String RESPONSE_TYPE;
    public static String SCOPE;
    public static String STATE;
    public static String TOKEN_ENDPOINT;
    public static String AUTH_ENDPOINT;
    public static String CLIENT_ID ;
    public static String CLIENT_SECRET;
    public static String REDIRECT_URI;
    public static String ENABLE;
    
    public Linkedin()
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
                        
                        if(connectorname.equals("Linkedin"))
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
                            STATE=element.getElementsByTagName("state").item(0).getTextContent();
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

    public String getAccessToken(String output,HttpServletRequest request) {
        String accessToken=null;
        
        try {
            JSONParser parser = new JSONParser();


            Object obj = parser.parse(output);
            JSONObject jsonobj = (JSONObject) obj;
            accessToken = jsonobj.get("access_token").toString();

        } 
        catch (ParseException e) {
            // Handle exception

		System.out.println("ERROR: " + e.getMessage());
        }
        return accessToken;    
    }

    @Override
    public void getprofiledetails(HttpServletRequest request,String linkedInUserInfoEndpoint,String accessToken){
        Map<String, String> requestProps = new HashMap<String, String>();
        requestProps.put("Content-Type", "application/x-www-form-urlencoded");
        
        Map<String, String> requestHeaders = new HashMap<String, String>();

        requestHeaders.put("Authorization", "Bearer " + accessToken);

        String resp = OAuthTLSUtil.sendRequest(linkedInUserInfoEndpoint, requestHeaders, requestProps, "GET");
        LinkedinPojo data = (LinkedinPojo)new Gson().fromJson(resp, LinkedinPojo.class);
        String email=data.getEmail();
        request.getSession().setAttribute("email", email);
        
        request.setAttribute("auth", data);
        createuser(request, "Linkedin");
        
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
