package com.ampliar.core.dbmodule;

import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.ehcache.*;


import java.net.URL;

public class EhCacheUtills {
    CacheManager cacheManager;

    public  EhCacheUtills(){
        URL myUrl = getClass().getResource("/ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        this.cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
    }

    public void putNewElement(String key, Object value){
       // cacheManager.getCache().put(new Eleme);
    }



}
