package com.ampliar.dbmodule;

import com.ampliar.core.dbmodule.DataAccess;
import com.ampliar.core.models.Advertisment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class OracleDataAccess implements DataAccess {

    private Properties props;
    private Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public ArrayList<Advertisment> findAllAdvertisments() {

        return null;
    }

    public ArrayList<Advertisment> findAllAdvertismentsByCategory() {

        return null;
    }

    public Advertisment findAdvertismentById(int id) {

        return null;
    }

    public ArrayList<Advertisment> findAdvertismentByTitle(String title) {

        return null;
    }

    public boolean insertAdvertisment(Advertisment obj) {

        return false;
    }

    public boolean updateAdvertisment(Advertisment obj) {

        return false;
    }

    public boolean deleteAdvertisment(Advertisment obj) {

        return false;
    }
}
