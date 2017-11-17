package com.amplier.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ampliar.core.dbmodule.QueryHandeller;
import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;
import com.ampliar.demo.models.Mobile;
import com.google.gson.Gson;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	Gson gson = new Gson();

	@RequestMapping(value = "/api-get-all-ads", method = RequestMethod.GET, produces = "application/json")
	public String getAllCarsApi() {
		ArrayList<Advertisment> tempAds = new ArrayList<Advertisment>();
		tempAds = new QueryHandeller().findAllAdvertisments(null, null);
		return gson.toJson(tempAds);

	}
	
	@RequestMapping(value = "/api-get-add/{id}", method = RequestMethod.GET, produces = "application/json")
	public String getAddById(@PathVariable String id) {
		Advertisment tempAdd = null;
		tempAdd = new QueryHandeller().findAdvertismentById(Integer.parseInt(id));
		return gson.toJson(tempAdd);

	}

	@RequestMapping(value = "/api-get-add-title", method = RequestMethod.POST, produces = "application/json")
	public String getAddByTitle(HttpServletRequest request) {
		ArrayList<Advertisment> tempAds = new ArrayList<Advertisment>();
		tempAds = new QueryHandeller().findAdvertismentByTitle(request.getParameter("title"));
		return gson.toJson(tempAds);

	}
	
//	@RequestMapping(value="/api-insert-mobile",method = RequestMethod.POST)
//	public String apiInsertMobile(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
//		ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();
//
//		for (MultipartFile multipartFile : files) {
//			adimage.add(new AdvertismentImage(multipartFile));
//
//		}
//
//		Category cat = new Category(1, request.getParameter("Category"), 1);
//		SubCategory subcat = new SubCategory(1, 1, request.getParameter("SubCategory"), 1);		
//		District dis = new District(1, request.getParameter("District"), 1);
//		DistrictLocalArea disLocal = new DistrictLocalArea(1, 1, request.getParameter("DistrictLocalArea"), 1);
//		
//		Mobile mobile = new Mobile(1, request.getParameter("title"), adimage, cat, subcat, dis, disLocal,
//				Double.parseDouble(request.getParameter("price")), 1, request.getParameter("condition"), request.getParameter("brand"),
//				request.getParameter("model"), request.getParameter("authenticity"), request.getParameter("bluetooth"),
//				request.getParameter("camera"));
//		
//		new QueryHandeller().insertAdvertisment(mobile);
//		
//		return null;
//		
//	}

}
