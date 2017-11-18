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
import org.apache.log4j.Logger;

public class QueryHandeller implements DataAccess {
	private Properties props = null;
	EhCacheUtills ehCacheUtills = null;
	private static final Logger logger = Logger.getLogger(QueryHandeller.class);

	Class[] adv = new Class[1];

	public QueryHandeller() {
		this.props = new ConfigReader().getConfigurations();
		ehCacheUtills = EhCacheUtills.getEhcacheUtils();
	}

	public ArrayList<Advertisment> findAllAdvertisments() {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAllAdvertisments = clazz.getDeclaredMethod("findAllAdvertisments", null);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAllAdvertisments.invoke(obj);


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch blocka
			logger.error("ClassNotFoundException",e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("NoSuchMethodException",e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityException",e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error("InstantiationException",e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalAccessException",e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalArgumentException",e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("InvocationTargetException",e);
		}


		return null;
	}

	public ArrayList<Advertisment> findAllAdvertismentsByCategory() {

		return null;
	}

	public Advertisment findAdvertismentById(int id) {
		if(ehCacheUtills.isAvailableInCache(Integer.toString(id))) {
			return (Advertisment) ehCacheUtills.getElement(Integer.toString(id));
		}
		
		Class<?> clazz;
		try {
			
			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentById = clazz.getDeclaredMethod("findAdvertismentById", Integer.TYPE);
			Object obj = clazz.newInstance();
			Advertisment adv =  (Advertisment)  findAdvertismentById.invoke(obj, id);
			ehCacheUtills.putNewElement(Integer.toString(adv.getAdvertismentId()),adv);
			return adv;
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch blocka
			logger.error("ClassNotFoundException",e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("NoSuchMethodException",e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityException",e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error("InstantiationException",e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalAccessException",e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalArgumentException",e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("InvocationTargetException",e);
		}

		return null;

	}

	public ArrayList<Advertisment> findAdvertismentByTitle(String title) {
 		Class<?> clazz;
		try {
			
			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentByTitle = clazz.getDeclaredMethod("findAdvertismentByTitle", String.class);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAdvertismentByTitle.invoke(obj, title);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch blocka
			logger.error("ClassNotFoundException",e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("NoSuchMethodException",e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityException",e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error("InstantiationException",e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalAccessException",e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalArgumentException",e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("InvocationTargetException",e);
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
			logger.error("ClassNotFoundException",e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error("InstantiationException",e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalAccessException",e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("NoSuchMethodException",e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityException",e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalArgumentException",e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("InvocationTargetException",e);
		}

		return true;
	}

	public boolean updateAdvertisment(Advertisment adv) {
		try {

			Class<?> clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method mUpdateAdd = clazz.getDeclaredMethod("updateAdvertisment", Advertisment.class);
			Object obj = clazz.newInstance();
			mUpdateAdd.invoke(obj, adv);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("ClassNotFoundException",e);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error("InstantiationException",e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalAccessException",e);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("NoSuchMethodException",e);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityException",e);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalArgumentException",e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("InvocationTargetException",e);
		}

		return true;
	}

	public boolean deleteAdvertisment(Advertisment adv) {
        try {

            Class<?> clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
            Method mDeleteAdd = clazz.getDeclaredMethod("deleteAdvertisment", Advertisment.class);
            Object obj = clazz.newInstance();
            mDeleteAdd.invoke(obj, adv);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            logger.error("ClassNotFoundException",e);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            logger.error("InstantiationException",e);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            logger.error("IllegalAccessException",e);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            logger.error("NoSuchMethodException",e);
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            logger.error("SecurityException",e);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            logger.error("IllegalArgumentException",e);
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            logger.error("InvocationTargetException",e);
        }

        return true;
	}

	public void testMethod(Advertisment add) {

	}

}
