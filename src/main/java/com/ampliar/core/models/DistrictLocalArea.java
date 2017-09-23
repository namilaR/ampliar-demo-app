package com.ampliar.core.models;

public class DistrictLocalArea {
	private int id;
	private int districtId;
	private String localAreaName;
	private int status;
	
	public DistrictLocalArea(int districtId, String localAreaName, int status) {
		super();
		this.districtId = districtId;
		this.localAreaName = localAreaName;
		this.status = status;
	}
	
	
	
	public DistrictLocalArea(int id, int districtId, String localAreaName, int status) {
		super();
		this.id = id;
		this.districtId = districtId;
		this.localAreaName = localAreaName;
		this.status = status;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public String getLocalAreaName() {
		return localAreaName;
	}
	public void setLocalAreaName(String localAreaName) {
		this.localAreaName = localAreaName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
