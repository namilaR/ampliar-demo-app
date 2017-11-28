package com.ampliar.core.dbmodule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ampliar.authenticationmodule.data.LoginInfo;
import com.ampliar.authenticationmodule.data.User;
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

	public ArrayList<Advertisment> findAdvertismentByCategory(String Category) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentByCategory = clazz.getDeclaredMethod("findAdvertismentByCategory", String.class);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAdvertismentByCategory.invoke(obj, Category);



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

	public ArrayList<Advertisment> findAdvertismentBySubCategory(String subCategory) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentBySubCategory = clazz.getDeclaredMethod("findAdvertismentBySubCategory", String.class);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAdvertismentBySubCategory.invoke(obj, subCategory);



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

	public ArrayList<Advertisment> findAdvertismentByDistrict(String district) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentByDistrict = clazz.getDeclaredMethod("findAdvertismentByDistrict", String.class);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAdvertismentByDistrict.invoke(obj, district);



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

	public ArrayList<Advertisment> findAdvertismentByDistrictLocalArea(String districtLocalArea) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method findAdvertismentByDistrictLocalArea = clazz.getDeclaredMethod("findAdvertismentByDistrictLocalArea", String.class);
			Object obj = clazz.newInstance();
			return (ArrayList<Advertisment>) findAdvertismentByDistrictLocalArea.invoke(obj, districtLocalArea);



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

	public int AddUser(User user) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method AddUser = clazz.getDeclaredMethod("AddUser", User.class);
			Object obj = clazz.newInstance();
			return (Integer) AddUser.invoke(obj, user);




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
		return 0;
	}

	public boolean CheckFederatedUserExists(String email, String authenticator) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method CheckFederatedUserExists = clazz.getDeclaredMethod("CheckFederatedUserExists", String.class,String.class);
			Object obj = clazz.newInstance();
			return (Boolean) CheckFederatedUserExists.invoke(obj, email,authenticator);




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
		return false;
	}

	public int AddLoginInfo(LoginInfo info) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method AddLoginInfo = clazz.getDeclaredMethod("AddLoginInfo", LoginInfo.class);
			Object obj = clazz.newInstance();
			return (Integer) AddLoginInfo.invoke(obj, info);




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
		return 0;
	}

	public boolean Login(String email, String password) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method Login = clazz.getDeclaredMethod("Login", String.class,String.class);
			Object obj = clazz.newInstance();
			return (Boolean) Login.invoke(obj, email,password);




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
		return false;
	}

	public boolean CheckEmailExists(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method CheckEmailExists = clazz.getDeclaredMethod("CheckEmailExists", String.class);
			Object obj = clazz.newInstance();
			return (Boolean) CheckEmailExists.invoke(obj, email);




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
		return false;
	}

	public ResultSet GetSecurityQuestion(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetSecurityQuestion = clazz.getDeclaredMethod("GetSecurityQuestion", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetSecurityQuestion.invoke(obj, email);




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

	public boolean CheckSecurityAnswer(String email, String answer) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method CheckSecurityAnswer = clazz.getDeclaredMethod("CheckSecurityAnswer", String.class,String.class);
			Object obj = clazz.newInstance();
			return (Boolean) CheckSecurityAnswer.invoke(obj, email,answer);




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
		return false;
	}

	public ResultSet GetIPaddressRecords(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetIPaddressRecords = clazz.getDeclaredMethod("GetIPaddressRecords", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetIPaddressRecords.invoke(obj, email);




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

	public ResultSet GetLastIPaddress(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetLastIPaddress = clazz.getDeclaredMethod("GetLastIPaddress", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetLastIPaddress.invoke(obj, email);




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

	public ResultSet GetBrowserRecords(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetBrowserRecords = clazz.getDeclaredMethod("GetBrowserRecords", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetBrowserRecords.invoke(obj, email);




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

	public ResultSet GetLastBrowser(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetLastBrowser = clazz.getDeclaredMethod("GetLastBrowser", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetLastBrowser.invoke(obj, email);




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

	public ResultSet GetDeviceRecords(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetDeviceRecords = clazz.getDeclaredMethod("GetDeviceRecords", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetDeviceRecords.invoke(obj, email);




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

	public ResultSet GetLastDevice(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetLastDevice = clazz.getDeclaredMethod("GetLastDevice", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetLastDevice.invoke(obj, email);




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

	public ResultSet GetTimeRecords(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetTimeRecords = clazz.getDeclaredMethod("GetTimeRecords", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetTimeRecords.invoke(obj, email);




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

	public ResultSet GetLastTime(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetLastTime = clazz.getDeclaredMethod("GetLastTime", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetLastTime.invoke(obj, email);




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

	public ResultSet GetLocationRecords(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetLocationRecords = clazz.getDeclaredMethod("GetLocationRecords", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetLocationRecords.invoke(obj, email);




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

	public ResultSet GetLastLocation(String email) {
		Class<?> clazz;
		try {

			clazz = Class.forName("com.ampliar.dbmodule." + props.getProperty("dbms") + "DataAccess");
			Method GetLastLocation = clazz.getDeclaredMethod("GetLastLocation", String.class);
			Object obj = clazz.newInstance();
			return (ResultSet) GetLastLocation.invoke(obj, email);




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

	public void testMethod(Advertisment add) {

	}

}
