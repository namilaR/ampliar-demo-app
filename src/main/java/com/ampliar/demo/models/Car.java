package com.ampliar.demo.models;

import java.sql.Date;
import java.util.ArrayList;

import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;
import com.google.gson.Gson;

public class Car extends Advertisment {
	private String brand;
	private String model;
	private int modelYear;
	private String condition;
	private double mileage;
	private String bodyType;
	private String transmission;
	private String fuelType;
	private double engineCapacity;
	private String description;
	
	
	
	
	

	public Car() {
		super();
		System.out.println("Car created");
	}

	public Car(int userId, String title, ArrayList<AdvertismentImage> advertismentImage, Category advertismentCategoty,
			SubCategory advertismentSubCategoty, District advertismentDistrict, DistrictLocalArea districtLoacalArea,
			double price, int status, String brand, String model, int modelYear,
			String condition, double mileage, String bodyType, String transmission, String fuelType,
			double engineCapacity, String description) {
		super(userId, title, advertismentImage, advertismentCategoty, advertismentSubCategoty, advertismentDistrict,
				districtLoacalArea, price, status );
		this.brand = brand;
		this.model = model;
		this.modelYear = modelYear;
		this.condition = condition;
		this.mileage = mileage;
		this.bodyType = bodyType;
		this.transmission = transmission;
		this.fuelType = fuelType;
		this.engineCapacity = engineCapacity;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Car [brand=" + brand + ", model=" + model + ", modelYear=" + modelYear + ", condition=" + condition
				+ ", mileage=" + mileage + ", bodyType=" + bodyType + ", transmission=" + transmission + ", fuelType="
				+ fuelType + ", engineCapacity=" + engineCapacity + ", description=" + description + ", advertismentId="
				+ advertismentId + ", userId=" + userId + ", title=" + title + ", advertismentImage="
				+ advertismentImage + ", advertismentCategoty=" + advertismentCategoty + ", advertismentSubCategoty="
				+ advertismentSubCategoty + ", advertismentDistrict=" + advertismentDistrict + ", districtLoacalArea="
				+ districtLoacalArea + ", price=" + price + ", status=" + status + ", createAt=" + createAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	
	
}
