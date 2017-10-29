/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ampliar.authenticationmodule.data;

import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Ishani
 */
public class Location {
    private String CountryCode;
    private String CountryName;
    private String Region;
    private String RegionName;
    private String City;
    private String PostalCodel;
    private String Latitude;
    private String Longitude;
    private String PostalCode;

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String CountryName) {
        this.CountryName = CountryName;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String RegionName) {
        this.RegionName = RegionName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getPostalCodel() {
        return PostalCodel;
    }

    public void setPostalCodel(String PostalCodel) {
        this.PostalCodel = PostalCodel;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }
    
    
    
    public static Location getLocation(String ipAddress) {

	File file = new File(
	    "G:\\Resources\\GeoLiteCity.dat\\GeoLiteCity.dat");
	return getLocation(ipAddress, file);

  }

  public static Location getLocation(String ipAddress, File file) {

	Location serverLocation = null;

	try {

	serverLocation = new Location();

	LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
	com.maxmind.geoip.Location locationServices = lookup.getLocation(ipAddress);

	serverLocation.setCountryCode(locationServices.countryCode);
	serverLocation.setCountryName(locationServices.countryName);
	serverLocation.setRegion(locationServices.region);
	serverLocation.setRegionName(regionName.regionNameByCode(
             locationServices.countryCode, locationServices.region));
	serverLocation.setCity(locationServices.city);
	serverLocation.setPostalCode(locationServices.postalCode);
	serverLocation.setLatitude(String.valueOf(locationServices.latitude));
	serverLocation.setLongitude(String.valueOf(locationServices.longitude));

	} catch (IOException e) {
		System.err.println(e.getMessage());
	}

	return serverLocation;

  }
}
