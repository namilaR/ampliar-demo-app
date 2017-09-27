package com.ampliar.core.dbmodule;

import java.util.ArrayList;
import java.util.List;

import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;

public interface DataAccess {
	
	public ArrayList<Advertisment> findAllAdvertisments(String categoryType,String subCategoryType);
	public Advertisment findAdvertismentById(int id);
	public Advertisment findAdvertismentByName(String title);
	public boolean insertAdvertisment(Advertisment obj);
	public boolean updateAdvertisment(Advertisment obj);
	public boolean deleteAdvertisment(Advertisment obj);



}
