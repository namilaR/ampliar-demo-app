package com.ampliar.core.dbmodule;

import java.util.ArrayList;
import com.ampliar.core.models.Advertisment;

public interface DataAccess {
	
	public ArrayList<Advertisment> findAllAdvertisments();
	public ArrayList<Advertisment> findAllAdvertismentsByCategory();
	public Advertisment findAdvertismentById(int id);
	public ArrayList<Advertisment> findAdvertismentByTitle(String title);
	public boolean insertAdvertisment(Advertisment obj);
	public boolean updateAdvertisment(Advertisment obj);
	public boolean deleteAdvertisment(Advertisment obj);


}
