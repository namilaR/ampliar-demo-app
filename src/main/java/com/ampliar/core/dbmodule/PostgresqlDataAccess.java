package com.ampliar.core.dbmodule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.ampliar.core.models.Advertisment;

public class PostgresqlDataAccess implements DataAccess {
	
	private DbConnection dbconn = new DbConnection();
	private Connection con = dbconn.getConnection();
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
