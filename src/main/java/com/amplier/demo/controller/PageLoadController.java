package com.amplier.demo.controller;

import com.ampliar.authenticationmodule.data.Location;
import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.serverlets.GenerateOTP;
import com.ampliar.authenticationmodule.serverlets.RiskScore;
import com.ampliar.authenticationmodule.serverlets.SendEmail;
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
import com.ampliar.temp.DataAccess;
import com.google.gson.Gson;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageLoadController {
	Gson gson = new Gson();
	@RequestMapping("/")
	public ModelAndView loadHomePage() {
		return new ModelAndView("index");
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

                if(DataAccess.CheckEmailExists(email))
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

                    if(DataAccess.Login(email, password)==true)
                    {
                        double risk_score=RiskScore.totalRiskScore(email, ip, info.getBrowser(), info.getDevice(), nowdate, latitude, longitude);
                        if(risk_score<0.5)
                        {
                            info.setStatus("success");
                            int info_id=DataAccess.AddLoginInfo(info);
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
                        double risk_score=RiskScore.totalRiskScore(email, ip, info.getBrowser(), info.getDevice(), nowdate, latitude, longitude);
                        if(risk_score>0.8)
                        {
                            String subject="Security alert";
                            String body="Dear "+email+","
                                    + "\n\n Someone tried to logged into your account "
                                    +"\n Browser: "+info.getBrowser()+"\n Device: "+info.getDevice()+"\n Country: "+l.getCountryName()+"\n City: "+l.getCity();

                            int info_id=DataAccess.AddLoginInfo(info);
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
        
        @RequestMapping(path = "/verification", method = RequestMethod.GET)
	public ModelAndView loadVerificationPage(HttpServletRequest request) throws SQLException {
            LoginInfo info=(LoginInfo)request.getSession().getAttribute("info");
            ResultSet rs=DataAccess.GetSecurityQuestion(info.getEmail());
            String question=null;
            while(rs.next())
            {
                question=rs.getString("SecurityQuestion");
            }
            request.setAttribute("question", question);
            return new ModelAndView("verification");
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
		new QueryHandeller().findAllAdvertisments(null, null);
		return new ModelAndView("index");
		
	}
	
	
}
