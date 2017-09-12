package com.ampliar.core.dbmodule;

import java.util.List;
import java.util.Properties;

import com.ampliar.core.models.Advertisment;

public class QueryHandeller  implements DataAccess{
	PostgresqlDataAccess psql = new PostgresqlDataAccess();
	MySqlDataAccess mysql = new MySqlDataAccess();
	ConfigReader conf = new ConfigReader();
	public List<Advertisment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Advertisment> findById() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Advertisment> findByName() {
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



}
