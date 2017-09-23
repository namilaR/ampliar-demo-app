package com.ampliar.core.models;

import java.sql.Date;
import java.util.ArrayList;

import com.google.gson.Gson;

public abstract class Advertisment {
	protected int advertismentId;
	protected int userId;
	protected String title;
	protected ArrayList<AdvertismentImage> advertismentImage;
	protected Category advertismentCategoty;
	protected SubCategory advertismentSubCategoty;
	protected District advertismentDistrict;
	protected DistrictLocalArea districtLoacalArea;
	protected double price;
	protected int status;
	protected Date createAt;
	protected Date updatedAt;

	public Advertisment() {

	}

	public Advertisment(int userId, String title, ArrayList<AdvertismentImage> advertismentImage,
			Category advertismentCategoty, SubCategory advertismentSubCategoty, District advertismentDistrict,
			DistrictLocalArea districtLoacalArea, double price, int status) {
		super();
		this.userId = userId;
		this.title = title;
		this.advertismentImage = advertismentImage;
		this.advertismentCategoty = advertismentCategoty;
		this.advertismentSubCategoty = advertismentSubCategoty;
		this.advertismentDistrict = advertismentDistrict;
		this.districtLoacalArea = districtLoacalArea;
		this.price = price;
		this.status = status;

	}

	public int getAdvertismentId() {
		return advertismentId;
	}

	public void setAdvertismentId(int advertismentId) {
		this.advertismentId = advertismentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ArrayList<AdvertismentImage> getAdvertismentImage() {
		return advertismentImage;
	}

	public void setAdvertismentImage(ArrayList<AdvertismentImage> advertismentImage) {
		this.advertismentImage = advertismentImage;
	}

	public Category getAdvertismentCategoty() {
		return advertismentCategoty;
	}

	public void setAdvertismentCategoty(Category advertismentCategoty) {
		this.advertismentCategoty = advertismentCategoty;
	}

	public SubCategory getAdvertismentSubCategoty() {
		return advertismentSubCategoty;
	}

	public void setAdvertismentSubCategoty(SubCategory advertismentSubCategoty) {
		this.advertismentSubCategoty = advertismentSubCategoty;
	}

	public District getAdvertismentDistrict() {
		return advertismentDistrict;
	}

	public void setAdvertismentDistrict(District advertismentDistrict) {
		this.advertismentDistrict = advertismentDistrict;
	}

	public DistrictLocalArea getDistrictLoacalArea() {
		return districtLoacalArea;
	}

	public void setDistrictLoacalArea(DistrictLocalArea districtLoacalArea) {
		this.districtLoacalArea = districtLoacalArea;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String objToJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		json = json.split(",\"advertismentId\":")[0] + "}";
		return json;
	}
}
