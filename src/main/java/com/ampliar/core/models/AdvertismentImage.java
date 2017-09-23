package com.ampliar.core.models;

public class AdvertismentImage {
	private int id;
	private int advertismentId;
	private String imageUrl;
	private int status;
	
	public AdvertismentImage(int advertismentId, String imageUrl, int status) {
		super();
		this.advertismentId = advertismentId;
		this.imageUrl = imageUrl;
		this.status = status;
	}
	
	
}
