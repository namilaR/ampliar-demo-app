package com.ampliar.demo.models;

import java.util.ArrayList;

import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;

public class Mobile extends Advertisment {
	private String condition;
	private String brand;
	private String model;
	private String Authenticity;
	private String bluetooth;
	private String camera;
	
	
	
	public Mobile() {
		super();
	}



	public Mobile(int userId, String title, ArrayList<AdvertismentImage> advertismentImage,
			Category advertismentCategoty, SubCategory advertismentSubCategoty, District advertismentDistrict,
			DistrictLocalArea districtLoacalArea, double price, int status, String condition, String brand,
			String model, String authenticity, String bluetooth, String camera) {
		super(userId, title, advertismentImage, advertismentCategoty, advertismentSubCategoty, advertismentDistrict,
				districtLoacalArea, price, status);
		this.condition = condition;
		this.brand = brand;
		this.model = model;
		Authenticity = authenticity;
		this.bluetooth = bluetooth;
		this.camera = camera;
	}



	@Override
	public String toString() {
		return "Mobile [condition=" + condition + ", brand=" + brand + ", model=" + model + ", Authenticity="
				+ Authenticity + ", bluetooth=" + bluetooth + ", camera=" + camera + ", advertismentId="
				+ advertismentId + ", userId=" + userId + ", title=" + title + ", advertismentImage="
				+ advertismentImage + ", advertismentCategoty=" + advertismentCategoty + ", advertismentSubCategoty="
				+ advertismentSubCategoty + ", advertismentDistrict=" + advertismentDistrict + ", districtLoacalArea="
				+ districtLoacalArea + ", price=" + price + ", status=" + status + ", createAt=" + createAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	

}
