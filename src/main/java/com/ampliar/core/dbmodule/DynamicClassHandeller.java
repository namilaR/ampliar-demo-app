package com.ampliar.core.dbmodule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;
import com.ampliar.demo.models.Car;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class DynamicClassHandeller {

	public ArrayList<Advertisment> createDynamicClassList(ResultSet rst) {
		System.out.println("createDynamicClassList called");
		ArrayList<Advertisment> list = new ArrayList<Advertisment>();
		Gson gson = new Gson();
		ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();

		try {

			Class noparams[] = {};
			Class paramInt[] = { Integer.TYPE };
			Class paramDouble[] = { Double.TYPE };
			Class paramString[] = { String.class };
			Class addCategoty[] = { Category.class };
			Class addSubCategoty[] = { SubCategory.class };
			Class addDistrict[] = { District.class };
			Class addDistrictLocalArea[] = { DistrictLocalArea.class };

			// int id = rst.getInt(1);
			while (rst.next()) {
				int thisrowId = rst.getInt(1);

				adimage.add(new AdvertismentImage(rst.getInt(13), rst.getInt(14), rst.getString(15), rst.getInt(16)));

				if (!rst.next() || rst.getInt(1) != thisrowId) {
					rst.previous();
					
					Class<?> clazz = Class.forName("com.ampliar.demo.models."+rst.getString(6));
					Object obj = clazz.newInstance();
					System.out.println();

					Method setAdvertismentId = clazz.getSuperclass().getDeclaredMethod("setAdvertismentId", paramInt);
					Method setUserId = clazz.getSuperclass().getDeclaredMethod("setUserId", paramInt);
					Method setAdvertismentImage = clazz.getSuperclass().getDeclaredMethod("setAdvertismentImage",
							ArrayList.class);
					Method setAdvertismentCategoty = clazz.getSuperclass().getDeclaredMethod("setAdvertismentCategoty",
							addCategoty);
					Method setAdvertismentSubCategoty = clazz.getSuperclass().getDeclaredMethod("setAdvertismentSubCategoty",
							addSubCategoty);
					Method setAdvertismentDistrict = clazz.getSuperclass().getDeclaredMethod("setAdvertismentDistrict",
							addDistrict);
					Method setDistrictLoacalArea = clazz.getSuperclass().getDeclaredMethod("setDistrictLoacalArea",
							addDistrictLocalArea);
					Method setPrice = clazz.getSuperclass().getDeclaredMethod("setPrice", paramDouble);
					Method setTitle = clazz.getSuperclass().getDeclaredMethod("setTitle", paramString);
					Method toString = clazz.getDeclaredMethod("toString", noparams);

					obj = gson.fromJson(rst.getString(9), clazz);
					setAdvertismentId.invoke(obj, rst.getInt(1));
					setUserId.invoke(obj, rst.getInt(2));
					setTitle.invoke(obj, rst.getString(3));
					setPrice.invoke(obj, rst.getDouble(4));
					setAdvertismentCategoty.invoke(obj, new Category(rst.getString(5)));
					setAdvertismentSubCategoty.invoke(obj, new SubCategory(rst.getString(6)));
					setAdvertismentDistrict.invoke(obj, new District(rst.getString(7)));
					setDistrictLoacalArea.invoke(obj, new DistrictLocalArea(rst.getString(8)));
					setAdvertismentImage.invoke(obj, adimage.clone());

					
					list.add((Advertisment) obj);
					obj = null;
					clazz = null;
					//obj = clazz.newInstance();
					adimage.clear();

				} else {
					rst.previous();
				}

			} 



		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(gson.toJson(list));
		System.out.println(list.size());
		// System.out.println(gson.toJson(list.get(1).getAdvertismentImage()));
		// System.out.println(gson.toJson(list.get(3).getAdvertismentImage()));
		return list;
	}

}
