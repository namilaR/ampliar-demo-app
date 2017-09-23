package com.ampliar.core.models;

public class SubCategory {
	
	private int id;
	private int categoryId;
	private String subCategoryName;
	private int status;
	
	public SubCategory(int categoryId, String subCategoryName, int status) {
		super();
		this.categoryId = categoryId;
		this.subCategoryName = subCategoryName;
		this.status = status;
	}
	
	
	
	public SubCategory(int id, int categoryId, String subCategoryName, int status) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.subCategoryName = subCategoryName;
		this.status = status;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
