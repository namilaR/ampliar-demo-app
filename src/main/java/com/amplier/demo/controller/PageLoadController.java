package com.amplier.demo.controller;

import com.ampliar.authenticationmodule.data.Location;
import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;
import com.ampliar.authenticationmodule.impl.Facebook;
import com.ampliar.authenticationmodule.impl.Google;
import com.ampliar.authenticationmodule.impl.Linkedin;
import com.ampliar.authenticationmodule.impl.TwitterClass;
import com.ampliar.authenticationmodule.util.GenerateOTP;
import com.ampliar.authenticationmodule.util.RiskScore;
import com.ampliar.authenticationmodule.util.SendEmail;
import com.ampliar.authenticationmodule.util.FileOperations;
import com.ampliar.authenticationmodule.util.OAuthTLSUtil;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;
import com.ampliar.demo.models.Car;
import com.google.gson.Gson;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@Controller
public class PageLoadController {
	Gson gson = new Gson();
	@RequestMapping("/")
	public ModelAndView loadHomePage() {
		return new ModelAndView("index");
	}
        
        @RequestMapping(path = "/classified-all", method = RequestMethod.GET)
	public ModelAndView loadClassifiedAll() {
		return new ModelAndView("classified_all");
	}
        
        @RequestMapping(path = "/post-ad", method = RequestMethod.GET)
	public ModelAndView loadPostAd() {
		return new ModelAndView("post-ad");
	}
        
        @RequestMapping(path = "/single", method = RequestMethod.GET)
	public ModelAndView loadSingleAd() {
		return new ModelAndView("single");
	}
        
        @RequestMapping("/Facebookcallback")
	public ModelAndView OAuthFacebookCallbackListener(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
            Facebook fb=new Facebook();
            String authorizationCode = fb.getAuthorizationcode(request);

            if (authorizationCode != null && authorizationCode.length() > 0) {

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
                

            } else {
                // Handle failure
            }
            
            return loadHomePage();
	}
        
        @RequestMapping("/Linkedincallback")
	public ModelAndView OAuthLinkedinCallbackListener(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
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
                
                String linkedInUserInfoEndpoint = "https://api.linkedin.com/v1/people/~:(first-name,last-name,email-address,picture-url,public-profile-url,summary,industry)?format=json";
                
                lkd.getprofiledetails(request,linkedInUserInfoEndpoint, accessToken);
                


            } else {
                // Handle failure
            }
            return loadHomePage();
        }
        
        @RequestMapping("/Googlecallback")
	public ModelAndView OAuthGoogleCallbackListener(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
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
                
                
            }
            else
            {
                // Handle failure
            }
            return loadHomePage();
        }
        
        @RequestMapping(path = "/twittersignin", method = RequestMethod.GET)
	public void twitterSignin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
        
        @RequestMapping("/Twittercallback")
	public ModelAndView OAuthTwittetCallbackListener(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
            TwitterClass tw=new TwitterClass();
            String verifier = tw.getVerifier(request);

            String output=tw.getAccessToken(verifier, request);
            return loadHomePage();
        }
        
        @RequestMapping(path = "/register", method = RequestMethod.GET)
	public ModelAndView loadRegisterPage() {
		return new ModelAndView("register");
	}
        
        @RequestMapping(path = "/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request) {
            User user=new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setSec_question(request.getParameter("sec_question"));
            user.setSec_answer(request.getParameter("answer"));
            user.setUser_type("Customer");
            user.setStatus("Active");
            user.setAuthenticator("Local");

            int id=new QueryHandeller().AddUser(user);
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            OperatingSystem agent = userAgent.getOperatingSystem();
            Browser browser=userAgent.getBrowser();

            if(id>0)
            {
                LoginInfo info=new LoginInfo();
                info.setEmail(request.getParameter("email"));
                String ip=info.getIPaddress(request);
                info.setUserip(ip);
                info.setDevice(agent.getDeviceType().getName());
                info.setBrowser(browser.getName());
                Location l=Location.getLocation(info.getUserip());
                double latitude=Double.parseDouble(l.getLatitude());
                double longitude=Double.parseDouble(l.getLongitude());
                info.setLatitude(latitude);
                info.setLongitude(longitude);
                info.setStatus("success");

                int info_id=new QueryHandeller().AddLoginInfo(info);
                if(info_id>0)
                {
                    request.getSession().setAttribute("email", user.getEmail());
                    return loadHomePage();
                }
            }
		return loadRegisterPage();
	}
        
        @RequestMapping(path = "/login", method = RequestMethod.GET)
	public ModelAndView loadLoginPage() {
		return new ModelAndView("login");
	}
        
        @RequestMapping(path = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
            try
            {
                String email=request.getParameter("email");
                String password=request.getParameter("password");

                UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
                OperatingSystem agent = userAgent.getOperatingSystem();
                Browser browser=userAgent.getBrowser();

                if(new QueryHandeller().CheckEmailExists(email))
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

                    if(new QueryHandeller().Login(email, password)==true)
                    {
                        double risk_score=new RiskScore().totalRiskScore(email, ip, info.getBrowser(), info.getDevice(), nowdate, latitude, longitude);
                        if(risk_score<0.5)
                        {
                            info.setStatus("success");
                            int info_id=new QueryHandeller().AddLoginInfo(info);
                            if(info_id>0)
                            {
                                request.getSession().setAttribute("email", email);
                                return loadHomePage();
                            }

                        }
                        else if(risk_score>=0.5 && risk_score<0.8)
                        {
                            request.getSession().setAttribute("info",info );

                            return loadVerificationPage(request);
                        }
                        else
                        {
                            request.getSession().setAttribute("info",info );
                            return loadOTPVerificationPage(request);
                        }

                    }
                    else
                    {
                        info.setStatus("failure");
                        double risk_score=new RiskScore().totalRiskScore(email, ip, info.getBrowser(), info.getDevice(), nowdate, latitude, longitude);
                        if(risk_score>0.8)
                        {
                            String subject="Security alert";
                            String body="Dear "+email+","
                                    + "\n\n Someone tried to logged into your account "
                                    +"\n Browser: "+info.getBrowser()+"\n Device: "+info.getDevice()+"\n Country: "+l.getCountryName()+"\n City: "+l.getCity();

                            int info_id=new QueryHandeller().AddLoginInfo(info);
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
            return loadLoginPage();
	}
        
        @RequestMapping(path = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request) {
                request.getSession().invalidate();
		return loadLoginPage();
	}
        
        @RequestMapping(path = "/adaptiveSettings", method = RequestMethod.GET)
	public ModelAndView adaptiveSettings(HttpServletRequest request) {
                Properties prop = new Properties();
                InputStream input = null;

                try {
                        input = new FileInputStream("G:\\Research-Final\\ampliar-demo-app\\src\\main\\resources\\adaptiveconfig.properties");;

                        // load a properties file
                        prop.load(input);

                        request.setAttribute("prop", prop);

                        return new ModelAndView("developersettings");

                } catch (IOException ex) {
                        ex.printStackTrace();
                } finally {
                        if (input != null) {
                                try {
                                        input.close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                }
                return null;
	}
        
        @RequestMapping(path = "/adaptiveSettings", method = RequestMethod.POST)
	public ModelAndView SaveadaptiveSettings(HttpServletRequest request) {
            try
            {
                double ip_weight=Double.parseDouble(request.getParameter("ip_weight"))/100;
                double browser_weight=Double.parseDouble(request.getParameter("browser_weight"))/100;
                double device_weight=Double.parseDouble(request.getParameter("device_weight"))/100;
                double time_weight=Double.parseDouble(request.getParameter("time_weight"))/100;
                double location_weight=Double.parseDouble(request.getParameter("location_weight"))/100;
                double time_range=Double.parseDouble(request.getParameter("time_range"));
                double latitude_range=Double.parseDouble(request.getParameter("latitude_range"));
                double longitude_range=Double.parseDouble(request.getParameter("longitude_range"));

                Properties prop = new Properties();
                OutputStream output = null;
                output = new FileOutputStream("G:\\Research-Final\\ampliar-demo-app\\src\\main\\resources\\adaptiveconfig.properties");

                // set the properties value
                prop.setProperty("ip_weight", Double.toString(ip_weight));
                prop.setProperty("browser_weight", Double.toString(browser_weight));
                prop.setProperty("device_weight", Double.toString(device_weight));
                prop.setProperty("time_weight", Double.toString(time_weight));
                prop.setProperty("location_weight", Double.toString(location_weight));
                prop.setProperty("time_range", Double.toString(time_range));
                prop.setProperty("latitude_range", Double.toString(latitude_range));
                prop.setProperty("longitude_range", Double.toString(longitude_range));

                // save properties to project root folder
                prop.store(output, null);
                return adaptiveSettings(request);

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            return null;
        }
        
        @RequestMapping(path = "/verification", method = RequestMethod.GET)
	public ModelAndView loadVerificationPage(HttpServletRequest request) throws SQLException {
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            ResultSet rs=new QueryHandeller().GetSecurityQuestion(info.getEmail());
            String question=null;
            while(rs.next())
            {
                question=rs.getString("SecurityQuestion");
            }
            request.setAttribute("question", question);
            return new ModelAndView("verification");
	}
        
        @RequestMapping(path = "/verification", method = RequestMethod.POST)
	public ModelAndView VerifyUser(HttpServletRequest request) throws SQLException {
            String answer=request.getParameter("answer");
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            
            if(new QueryHandeller().CheckSecurityAnswer(info.getEmail(), answer))
            {
                info.setStatus("success");
                int info_id=new QueryHandeller().AddLoginInfo(info);
                if(info_id>0)
                {
                    request.getSession().setAttribute("email", info.getEmail());
                    return loadHomePage();
                }
            }
            else
            {
                info.setStatus("failure");
                int info_id=new QueryHandeller().AddLoginInfo(info);
                if(info_id>0)
                {
                    return loadHomePage();
                }
            }
            return null;
	}
        
        @RequestMapping(path = "/OTPVerification", method = RequestMethod.GET)
	public ModelAndView loadOTPVerificationPage(HttpServletRequest request) throws SQLException {
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            String otp=Arrays.toString(GenerateOTP.OTP(6));

            StringBuilder sb = new StringBuilder();
            boolean found = false;
            for(char c : otp.toCharArray()){
                if(Character.isDigit(c)){
                    sb.append(c);
                    found = true;
                } 
            }
            otp=sb.toString();
            String email=info.getEmail();
            String subject="One time password";
            String body="Dear "+email+","
                                    + "\n\n Use this 6-digit code to login to your account "+otp;

            request.getSession().setAttribute("otp",otp );
            SendEmail.Send(email, subject, body);
            return new ModelAndView("OTPVerification");
            
	}
	
        @RequestMapping(path = "/OTPVerification", method = RequestMethod.POST)
	public ModelAndView VerifyOTP(HttpServletRequest request) throws SQLException {
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            
            String code=request.getParameter("code");
            String otp=(String)request.getSession().getAttribute("otp");
            
            if(otp.equals(code))
            {
                info.setStatus("success");
                int info_id=new QueryHandeller().AddLoginInfo(info);
                String email=info.getEmail();
                if(info_id>0)
                {
                    request.getSession().invalidate();
                    request.getSession().setAttribute("email",email );
                    return loadHomePage();
                }
            }
            else
            {
                info.setStatus("failure");
                int info_id=new QueryHandeller().AddLoginInfo(info);
                if(info_id>0)
                {
                    request.getSession().invalidate();
                    return new ModelAndView("verificationfailure");
                }
                
            }
            return null;
            
	}
        
        @RequestMapping(path = "/insert-car", method = RequestMethod.POST)
	public ModelAndView insertCar(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {

		ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();

		for (MultipartFile multipartFile : files) {
			adimage.add(new AdvertismentImage(multipartFile));

		}

		Category cat = new Category(1, request.getParameter("Category"), 1);
		SubCategory subcat = new SubCategory(1, 1, request.getParameter("SubCategory"), 1);		
		District dis = new District(1, request.getParameter("District"), 1);
		DistrictLocalArea disLocal = new DistrictLocalArea(1, 1, request.getParameter("DistrictLocalArea"), 1);
		
		Car car = new Car(1, request.getParameter("title"), adimage, cat, subcat, dis, disLocal,
				Double.parseDouble(request.getParameter("price")), 1, request.getParameter("brand"),
				request.getParameter("model"), Integer.parseInt(request.getParameter("modelYear")),
				request.getParameter("condition"), Double.parseDouble(request.getParameter("mileage")),
				request.getParameter("bodyType"), request.getParameter("transmission"),
				request.getParameter("fuelType"), Double.parseDouble(request.getParameter("engineCapacity")),
				request.getParameter("description"));

		new QueryHandeller().insertAdvertisment(car);

		return new ModelAndView("index");
	}
	
	@RequestMapping(path = "/get-all-cars",  method = RequestMethod.GET)
	public ModelAndView getAllCars() {
		//new QueryHandeller().findAllAdvertisments(null, null);
		return new ModelAndView("index");
		
	}
	
	
}
