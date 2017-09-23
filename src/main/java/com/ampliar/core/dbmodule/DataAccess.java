package com.ampliar.core.dbmodule;

import java.util.List;

import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;

public interface DataAccess {
	
	public List<Advertisment> findAllAdvertisments();
	public List<Advertisment> findAdvertismentById();
	public List<Advertisment> findAdvertismentByName();
	public boolean insertAdvertisment(Advertisment obj);
	public boolean updateAdvertisment(Advertisment obj);
	public boolean deleteAdvertisment(Advertisment obj);
	
	public List<District> findAllDistricts();
	public List<District> findDistrictById();
	public List<District> findDistrictByName();
	public boolean insertDistrict(District obj);
	public boolean updateDistrict(District obj);
	public boolean deleteDistrict(District obj);
	
	
	public List<DistrictLocalArea> findAllDistrictLocalAreas();
	public List<DistrictLocalArea> findDistrictLocalAreaById();
	public List<DistrictLocalArea> findDistrictLocalAreaByName();
	public boolean insertDistrictLocalArea(DistrictLocalArea obj);
	public boolean updateDistrictLocalArea(DistrictLocalArea obj);
	public boolean deleteDistrictLocalArea(DistrictLocalArea obj);

	public List<Category> findAllCategorys();
	public List<Category> findCategoryById();
	public List<Category> findCategoryByName();
	public boolean insertCategory(Category obj);
	public boolean updateCategory(Category obj);
	public boolean deleteCategory(Category obj);
	
	public List<SubCategory> findAllSubCategorys();
	public List<SubCategory> findSubCategoryById();
	public List<SubCategory> findSubCategoryByName();
	public boolean insertSubCategory(SubCategory obj);
	public boolean updateSubCategory(SubCategory obj);
	public boolean deleteSubCategory(SubCategory obj);
	



}
