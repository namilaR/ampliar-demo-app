package com.ampliar.core.models;

public class District {
	private int id;
	private String districtName;
	private int status;
	
	public District(int id,String districtName, int status) {
		super();
		this.id = id;
		this.districtName = districtName;
		this.status = status;
	}
	
	

	public District(String districtName) {
		super();
		this.districtName = districtName;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
