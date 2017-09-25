package com.ampliar.core.models;

import org.springframework.web.multipart.MultipartFile;

public class AdvertismentImage {
	private int id;
	private int advertismentId;
	private MultipartFile image;
	private int status;
	
	public AdvertismentImage(int advertismentId, MultipartFile imageUrl, int status) {
		super();
		this.advertismentId = advertismentId;
		this.image = imageUrl;
		this.status = status;
	}
	
	

	public AdvertismentImage(MultipartFile image) {
		super();
		this.image = image;
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
	
	
	
}
