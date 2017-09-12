package com.ampliar.core.dbmodule;

import java.util.List;

import com.ampliar.core.models.Advertisment;

public interface DataAccess {
	
	public List<Advertisment> findAll();
	public List<Advertisment> findById();
	public List<Advertisment> findByName();
	public boolean insertAdvertisment(Advertisment adv);
	public boolean updateAdvertisment(Advertisment adv);
	public boolean deleteAdvertisment(Advertisment adv);

}
