package com.ampliar.core.dbmodule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;

public class QueryHandeller implements DataAccess {
	private Properties props = null;

	Class[] adv = new Class[1];

	public QueryHandeller() {
		this.props = new ConfigReader().getConfigurations();

	}

	public ArrayList<Advertisment> findAllAdvertisments() {

		Class params[] = new Class[2];
		params[0] = String.class;
		params[1] = String.class;

		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAllAdvertisments = clazz.getDeclaredMethod("findAllAdvertisments", null);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAllAdvertisments.invoke(obj);


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch blocka
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}

	public ArrayList<Advertisment> findAllAdvertismentsByCategory() {

		return null;
	}

	public Advertisment findAdvertismentById(int id) {
		Class params[] = new Class[2];
		params[0] = String.class;
		params[1] = String.class;
		
		Class<?> clazz;
		try {
			
			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentById = clazz.getDeclaredMethod("findAdvertismentById", Integer.TYPE);
			Object obj = clazz.newInstance();
			return (Advertisment) findAdvertismentById.invoke(obj, id);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch blocka
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public ArrayList<Advertisment> findAdvertismentByTitle(String title) {
		Class params[] = new Class[2];
		params[0] = String.class;
		params[1] = String.class;
		
		Class<?> clazz;
		try {
			
			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentByTitle = clazz.getDeclaredMethod("findAdvertismentByTitle", String.class);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAdvertismentByTitle.invoke(obj, title);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch blocka
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

		
	}

	public boolean insertAdvertisment(Advertisment add) {
		try {
			
			Class<?> clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method mInsertAdd = clazz.getDeclaredMethod("insertAdvertisment", Advertisment.class);
			Object obj = clazz.newInstance();
			mInsertAdd.invoke(obj, add);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean updateAdvertisment(Advertisment adv) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteAdvertisment(Advertisment adv) {
		// TODO Auto-generated method stub
		return false;
	}

	public void testMethod(Advertisment add) {

	}

}
