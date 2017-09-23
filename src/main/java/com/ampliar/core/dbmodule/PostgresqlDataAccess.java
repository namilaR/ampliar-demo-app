package com.ampliar.core.dbmodule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;

public class PostgreSQLDataAccess implements DataAccess {
	
	
	private Connection con = null;
	
	public List<Advertisment> findAllAdvertisments() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Advertisment> findAdvertismentById() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Advertisment> findAdvertismentByName() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean insertAdvertisment(Advertisment adv) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean updateAdvertisment(Advertisment adv) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean deleteAdvertisment(Advertisment adv) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<District> findAllDistricts() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<District> findDistrictById() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<District> findDistrictByName() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean insertDistrict(District obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean updateDistrict(District obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean deleteDistrict(District obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<DistrictLocalArea> findAllDistrictLocalAreas() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<DistrictLocalArea> findDistrictLocalAreaById() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<DistrictLocalArea> findDistrictLocalAreaByName() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean insertDistrictLocalArea(DistrictLocalArea obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean updateDistrictLocalArea(DistrictLocalArea obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean deleteDistrictLocalArea(DistrictLocalArea obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<Category> findAllCategorys() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Category> findCategoryById() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Category> findCategoryByName() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean insertCategory(Category obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean updateCategory(Category obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean deleteCategory(Category obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public List<SubCategory> findAllSubCategorys() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<SubCategory> findSubCategoryById() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<SubCategory> findSubCategoryByName() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean insertSubCategory(SubCategory obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean updateSubCategory(SubCategory obj) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean deleteSubCategory(SubCategory obj) {
		// TODO Auto-generated method stub
		return false;
	}



}
