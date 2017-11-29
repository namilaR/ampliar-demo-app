/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package com.ampliar.authenticationmodule.impl; 
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.core.authenticationmodule.Authentication;
import com.ampliar.temp.DataAccess;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;

/**
 *
 * @author Ishani
 */
public class TwitterClass extends Authentication{
    public static String REQUEST_TOKEN_ENDPOINT;
    public static String TOKEN_ENDPOINT;
    public static String AUTH_ENDPOINT;
    public static String CLIENT_ID ;
    public static String CLIENT_SECRET;
    public static String REDIRECT_URI;
    public static String ENABLE;
    
    public TwitterClass()
    {
        //assignvalues();
    }
    public static void assignvalues()
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=factory.newDocumentBuilder();

            Document doc=builder.parse("http://localhost:8080/TestProject/config/config.xml");

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
                        
                        if(connectorname.equals("Twitter"))
                        {
                            ENABLE = element.getElementsByTagName("enable").item(0).getTextContent();
                            CLIENT_ID=element.getElementsByTagName("clientid").item(0).getTextContent();
                            CLIENT_SECRET=element.getElementsByTagName("clientsecret").item(0).getTextContent();
                            TOKEN_ENDPOINT=element.getElementsByTagName("token_endpoint").item(0).getTextContent();
                            REQUEST_TOKEN_ENDPOINT=element.getElementsByTagName("requesttoken_endpoint").item(0).getTextContent();
                            AUTH_ENDPOINT=element.getElementsByTagName("authorization_endpoint").item(0).getTextContent();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public RequestToken getRequestToken(HttpServletRequest request,Twitter twitter) throws ServletException {
        RequestToken requestToken;
        try {
            StringBuffer callbackURL = request.getRequestURL();
            int index = callbackURL.lastIndexOf("/");
            callbackURL.replace(index, callbackURL.length(), "").append("/Twittercallback");

             requestToken= twitter.getOAuthRequestToken(callbackURL.toString());
            request.getSession().setAttribute("requestToken", requestToken);
            

        } catch (TwitterException e) {
            throw new ServletException(e);
        }
        return requestToken;
    }
    
    public String getVerifier(HttpServletRequest request)
    {
        String verifier=request.getParameter("oauth_verifier");
        return verifier;
    }
    
    @Override
    public String getAccessToken(String verifier,HttpServletRequest request) {
        try {
            Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
            RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
            twitter.getOAuthAccessToken(requestToken, verifier);
            request.getSession().removeAttribute("requestToken");
            return "success";
     
        } catch (TwitterException e) {
            
        }
        return null;
       
    }

    @Override
    public void getprofiledetails(HttpServletRequest request, String UserInfoEndpoint, String accessToken) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
