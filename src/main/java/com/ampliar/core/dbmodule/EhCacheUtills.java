package com.ampliar.core.dbmodule;

import com.ampliar.core.models.Advertisment;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.spi.copy.Copier;
import org.ehcache.xml.XmlConfiguration;



import java.net.URL;

public class EhCacheUtills {
    private static EhCacheUtills ehCacheUtills = null;
    CacheManager cacheManager;
    Cache<String, Advertisment> cachePool;


    private  EhCacheUtills(){
        URL myUrl = getClass().getResource("/ehcache.xml");
        Configuration xmlConfig = new XmlConfiguration(myUrl);
        this.cacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
        cacheManager.init();
        cachePool = cacheManager.getCache("cachePool",String.class,Advertisment.class);
    }

    public static EhCacheUtills getEhcacheUtils(){
        if(ehCacheUtills == null){
            ehCacheUtills = new EhCacheUtills();
        }
        return ehCacheUtills;
    }

    public void putNewElement(String key, Advertisment value){
        System.out.println("EhCahe :Put cache");
        cachePool.put(key,value);
    }

    public Object getElement(String key){
        System.out.println("EhCahe :  Get from cache");
        return cachePool.get(key);
    }

    public boolean isAvailableInCache(String key){
        System.out.println("EhCahe : check availability");
        return cachePool.containsKey(key);
    }



}
