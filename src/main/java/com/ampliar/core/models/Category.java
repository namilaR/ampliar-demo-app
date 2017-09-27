package com.ampliar.core.models;

public class Category {	
	private int id;
	private String categoryName;
	private int status;
	
	
	
	public Category() {
		super();
	}
	
	

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}



	public Category(String categoryName, int status) {
		super();
		this.categoryName = categoryName;
		this.status = status;
	}

	public Category(int id, String categoryName, int status) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	

}
