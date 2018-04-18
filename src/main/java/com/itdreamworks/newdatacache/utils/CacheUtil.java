package com.itdreamworks.newdatacache.utils;

import com.itdreamworks.newdatacache.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheUtil {
    private static final String CACHE_JSON_DEVICE_INFO = "cache_json_device_info";

    private static final String CACHE_BYTE_DEVICE_INFO = "cache_byte_device_info";

    @Autowired
    CacheManager cacheManager;


    public Cache getCache(String cacheName){
        return cacheManager.getCache(cacheName);
    }

    public byte[] getCacheData(String key){
        Cache cache = getCache(CACHE_BYTE_DEVICE_INFO);
        Cache.ValueWrapper element = cache.get(key);
        if(null != element){
            return (byte[]) element.get();
        }
        return null;
    }

    public void putData(String key,byte[] data){
        Cache cache = getCache(CACHE_BYTE_DEVICE_INFO);
        cache.put(key,data);
    }

    public List<byte[]> getCacheDatas(String[] keys){
        ArrayList<byte[]> ls = new ArrayList<>(keys.length);
        Cache cache = getCache(CACHE_BYTE_DEVICE_INFO);
        for(String key : keys){
            Cache.ValueWrapper element = cache.get(key);
            if(null != element){
                ls.add((byte[]) element.get());
            }
        }
        return ls;
    }

    public Device getDevice(String key){
        Cache cache = getCache(CACHE_JSON_DEVICE_INFO);
        Cache.ValueWrapper element = cache.get(key);
        if(null != element){
            return (Device) element.get();
        }
        return null;
    }

    public void putDevice(Device device){
        Cache cache = getCache(CACHE_JSON_DEVICE_INFO);
        cache.put(device.getDeviceNo(),device);
    }

    public List<Device> getDevices(String[] keys){
        ArrayList<Device> ls = new ArrayList<>(keys.length);
        Cache cache = getCache(CACHE_JSON_DEVICE_INFO);
        for(String key : keys){
            Cache.ValueWrapper element = cache.get(key);
            if(null != element){
                ls.add((Device) element.get());
            }
        }
        return ls;
    }
}

