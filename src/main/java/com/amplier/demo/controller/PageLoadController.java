package com.amplier.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;
import com.ampliar.demo.models.Car;

@Controller
public class PageLoadController {
	@RequestMapping("/")
	public ModelAndView loadHomePage() {
		return new ModelAndView("index");
	}
	
	@RequestMapping("/test")
	public ModelAndView testDb() {
		
		ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();
		adimage.add(new AdvertismentImage(1,"test/url1/",1));
		adimage.add(new AdvertismentImage(2,"test/url2/",1));
		adimage.add(new AdvertismentImage(3,"test/url3/",1));
		adimage.add(new AdvertismentImage(4,"test/url4/",1));
		
		Category cat  = new Category(1,"vehicle",1);
		SubCategory subcat = new SubCategory(1,1,"cars",1);
		
		District dis = new District(1,"colombo", 1);
		DistrictLocalArea disLocal = new DistrictLocalArea(1,1, "kaduwela", 1);
		
		
		
		
		
		
		//Car car = new Car(adimage, cat, subcat, dis, disLocal,10000.00); 
		Car car = new Car(1,"supra 2008",adimage, cat, subcat, dis, disLocal,10000.00,1,"toyota","supra",2003,"mint",20000.0,"sedan","auto","petrol",2000.0,"nego"); 
		new QueryHandeller().testMethod(car); 
		return new ModelAndView("index");
	}
}
