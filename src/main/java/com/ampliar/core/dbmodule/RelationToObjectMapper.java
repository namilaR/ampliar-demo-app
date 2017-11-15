package com.ampliar.core.dbmodule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.ampliar.core.models.Advertisment;
import com.ampliar.core.models.AdvertismentImage;
import com.ampliar.core.models.Category;
import com.ampliar.core.models.District;
import com.ampliar.core.models.DistrictLocalArea;
import com.ampliar.core.models.SubCategory;
import com.ampliar.demo.models.Car;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RelationToObjectMapper {

    public ArrayList<Advertisment> createMappedRowList(ResultSet rst) {
        ArrayList<Advertisment> list = new ArrayList<Advertisment>();
        Gson gson = new Gson();
        ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();

        try {

            Class noparams[] = {};
            Class paramInt[] = {Integer.TYPE};
            Class paramDouble[] = {Double.TYPE};
            Class paramString[] = {String.class};
            Class addCategoty[] = {Category.class};
            Class addSubCategoty[] = {SubCategory.class};
            Class addDistrict[] = {District.class};
            Class addDistrictLocalArea[] = {DistrictLocalArea.class};


            while (rst.next()) {


                Class<?> clazz = Class.forName("com.ampliar.demo.models." + rst.getString(6));
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

                obj = gson.fromJson(rst.getString(10), clazz);
                setAdvertismentId.invoke(obj, rst.getInt(1));
                setUserId.invoke(obj, rst.getInt(2));
                setTitle.invoke(obj, rst.getString(3));
                setPrice.invoke(obj, rst.getDouble(4));
                setAdvertismentCategoty.invoke(obj, new Category(rst.getString(5)));
                setAdvertismentSubCategoty.invoke(obj, new SubCategory(rst.getString(6)));
                setAdvertismentDistrict.invoke(obj, new District(rst.getString(7)));
                setDistrictLoacalArea.invoke(obj, new DistrictLocalArea(rst.getString(8)));


                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(rst.getString(9));
                for (int i = 0; i < array.size(); i++) {
                    System.out.println("JSON ID " + ((JSONObject) array.get(i)).get("imageUrl"));
                    String URL = (String) ((JSONObject) array.get(i)).get("imageUrl");
                    adimage.add(new AdvertismentImage(URL));
                    setAdvertismentImage.invoke(obj, adimage.clone());
                }


                list.add((Advertisment) obj);
                obj = null;
                clazz = null;
                adimage.clear();


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
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("Add Image Size : " + adimage.size());

        return list;
    }

    public Advertisment createMappedRow(ResultSet rst){
        Advertisment add = null;
        Gson gson = new Gson();
        ArrayList<AdvertismentImage> adimage = new ArrayList<AdvertismentImage>();

        try {

            Class noparams[] = {};
            Class paramInt[] = {Integer.TYPE};
            Class paramDouble[] = {Double.TYPE};
            Class paramString[] = {String.class};
            Class addCategoty[] = {Category.class};
            Class addSubCategoty[] = {SubCategory.class};
            Class addDistrict[] = {District.class};
            Class addDistrictLocalArea[] = {DistrictLocalArea.class};


            while (rst.next()) {


                Class<?> clazz = Class.forName("com.ampliar.demo.models." + rst.getString(6));
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

                obj = gson.fromJson(rst.getString(10), clazz);
                setAdvertismentId.invoke(obj, rst.getInt(1));
                setUserId.invoke(obj, rst.getInt(2));
                setTitle.invoke(obj, rst.getString(3));
                setPrice.invoke(obj, rst.getDouble(4));
                setAdvertismentCategoty.invoke(obj, new Category(rst.getString(5)));
                setAdvertismentSubCategoty.invoke(obj, new SubCategory(rst.getString(6)));
                setAdvertismentDistrict.invoke(obj, new District(rst.getString(7)));
                setDistrictLoacalArea.invoke(obj, new DistrictLocalArea(rst.getString(8)));


                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(rst.getString(9));
                for (int i = 0; i < array.size(); i++) {
                    System.out.println("JSON ID " + ((JSONObject) array.get(i)).get("imageUrl"));
                    String URL = (String) ((JSONObject) array.get(i)).get("imageUrl");
                    adimage.add(new AdvertismentImage(URL));
                    setAdvertismentImage.invoke(obj, adimage.clone());
                }


                //list.add((Advertisment) obj);
                add = (Advertisment) obj;
                obj = null;
                clazz = null;
                adimage.clear();


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
        } catch (ParseException e) {
            e.printStackTrace();
        }


        System.out.println("Add Image Size : " + adimage.size());

        return add;
    }
}
