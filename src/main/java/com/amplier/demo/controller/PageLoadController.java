package com.amplier.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;
import com.ampliar.demo.models.Car;
import com.google.gson.Gson;

@Controller
public class PageLoadController {
	Gson gson = new Gson();
	@RequestMapping("/")
	public ModelAndView loadHomePage() {
		return new ModelAndView("index");
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
		new QueryHandeller().findAllAdvertisments();
		return new ModelAndView("index");
		
	}
	
	
}
