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
	Advertisment tempAdd = null;

	@RequestMapping(value = "/api-get-all-ads", method = RequestMethod.GET, produces = "application/json")
	public String getAllCarsApi() {
		ArrayList<Advertisment> tempAds = new ArrayList<Advertisment>();
		tempAds = new QueryHandeller().findAllAdvertisments();
		return gson.toJson(tempAds);

	}
	
	@RequestMapping(value = "/api-get-add-by-id/{id}", method = RequestMethod.GET, produces = "application/json")
	public String getAddById(@PathVariable String id) {

		tempAdd = new QueryHandeller().findAdvertismentById(Integer.parseInt(id));
		System.out.println("ID " + id );
		return gson.toJson(tempAdd);

	}

	@RequestMapping(value = "/api-get-add-by-title/{title}", method = RequestMethod.GET, produces = "application/json")
	public String getAddByTitle(@PathVariable String title) {
		ArrayList<Advertisment> tempAds = new ArrayList<Advertisment>();
		tempAds = new QueryHandeller().findAdvertismentByTitle(title);
		System.out.println("ID " + title );
		return gson.toJson(tempAds);

	}

	@RequestMapping(value = "/api-get-add-title", method = RequestMethod.POST, produces = "application/json")
	public String getAddByTitle(HttpServletRequest request) {
		ArrayList<Advertisment> tempAds = new ArrayList<Advertisment>();
		tempAds = new QueryHandeller().findAdvertismentByTitle(request.getParameter("title"));
		return gson.toJson(tempAds);

	}
	
	@RequestMapping(value="/api-insert-mobile",method = RequestMethod.POST)
	public String apiInsertMobile(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
		ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();

		for (MultipartFile multipartFile : files) {
			adimage.add(new AdvertismentImage(multipartFile));

		}

		Category cat = new Category(1, request.getParameter("Category"), 1);
		SubCategory subcat = new SubCategory(1, 1, request.getParameter("SubCategory"), 1);		
		District dis = new District(1, request.getParameter("District"), 1);
		DistrictLocalArea disLocal = new DistrictLocalArea(1, 1, request.getParameter("DistrictLocalArea"), 1);
		
		Mobile mobile = new Mobile(1, request.getParameter("title"), adimage, cat, subcat, dis, disLocal,
				Double.parseDouble(request.getParameter("price")), 1, request.getParameter("condition"), request.getParameter("brand"),
				request.getParameter("model"), request.getParameter("authenticity"), request.getParameter("bluetooth"),
				request.getParameter("camera"));
		
		new QueryHandeller().insertAdvertisment(mobile);
		
		return null;
		
	}

	@RequestMapping(value="/api-update-mobile",method = RequestMethod.POST)
	public String apiUpdateMobile(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {
		ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();

		for (MultipartFile multipartFile : files) {
			adimage.add(new AdvertismentImage(multipartFile));

		}

		Category cat = new Category(1, request.getParameter("Category"), 1);
		SubCategory subcat = new SubCategory(1, 1, request.getParameter("SubCategory"), 1);
		District dis = new District(1, request.getParameter("District"), 1);
		DistrictLocalArea disLocal = new DistrictLocalArea(1, 1, request.getParameter("DistrictLocalArea"), 1);

		Mobile mobile = (Mobile) tempAdd;
		mobile.setTitle(request.getParameter("title"));
		mobile.setAdvertismentImage(adimage);
		mobile.setAdvertismentCategoty(cat);
		mobile.setAdvertismentSubCategoty(subcat);
		mobile.setAdvertismentDistrict(dis);
		mobile.setDistrictLoacalArea(disLocal);
		mobile.setPrice(Double.parseDouble(request.getParameter("price")));

		mobile.setCondition(request.getParameter("condition"));
		mobile.setBrand( request.getParameter("brand"));
		mobile.setModel(request.getParameter("model"));
		mobile.setAuthenticity(request.getParameter("authenticity"));
		mobile.setBluetooth(request.getParameter("bluetooth"));
		mobile.setCamera(request.getParameter("camera"));

		new QueryHandeller().updateAdvertisment(mobile);
		tempAdd = null;

		return null;

	}

	@RequestMapping(value="/api-delete-advertisment",method = RequestMethod.POST)
	public String apiDeleteAdvertisment(HttpServletRequest request, @RequestParam("files") MultipartFile[] files) {


		new QueryHandeller().deleteAdvertisment(tempAdd);
		tempAdd = null;

		return null;

	}

}
