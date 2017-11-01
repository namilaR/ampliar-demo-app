package com.ampliar.core.models;

import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

public class AdvertismentImage {
	private int id;
	private int advertismentId;
	private MultipartFile image;
	private String imageUrl;
	private int status;
	
	public AdvertismentImage(int advertismentId, MultipartFile image, int status) {
		super();
		this.advertismentId = advertismentId;
		this.image = image;
		this.imageUrl = image.getOriginalFilename();
		this.status = status;
	}
	
	
	

	public AdvertismentImage(int id, int advertismentId, String imageUrl, int status) {
		super();
		this.id = id;
		this.advertismentId = advertismentId;
		this.imageUrl = imageUrl;
		this.status = status;
	}

	public AdvertismentImage( String imageUrl) {
		super();
		this.id = id;
		this.advertismentId = advertismentId;
		this.imageUrl = imageUrl;
		this.status = status;
	}




	public AdvertismentImage(MultipartFile image) {
		super();
		this.image = image;
		this.imageUrl = image.getOriginalFilename();
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdvertismentId() {
		return advertismentId;
	}

	public void setAdvertismentId(int advertismentId) {
		this.advertismentId = advertismentId;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImageUrl(MultipartFile imageUrl) {
		this.image = imageUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String ImageMetaDataToJson(){

		Gson gson = new Gson();
		AdvertismentImage temp = new AdvertismentImage(this.image.getOriginalFilename());
		String json = gson.toJson(temp);
		//json = json.split(",\"advertismentId\":")[0] + "}";
		return json;


	}
	
	
	
}
