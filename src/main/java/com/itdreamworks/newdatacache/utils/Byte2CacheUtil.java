package com.itdreamworks.newdatacache.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Byte2CacheUtil {
    private static final String CACHE_BYTE_DEVICE_INFO = "cache_byte_device_info2";
    @Autowired
    CacheManager cacheManager;


    private Cache getCache() {
        return cacheManager.getCache(CACHE_BYTE_DEVICE_INFO);
    }

    public byte[] getCacheData(String key) {
        Cache cache = getCache();
        Cache.ValueWrapper element = cache.get(key);
        if (null != element) {
            return (byte[]) element.get();
        }
        return null;
    }

    public void putData(String key, byte[] data) {
        Cache cache = getCache();
        cache.put(key, data);
    }

    public List<byte[]> getCacheDatas(String[] keys) {
        ArrayList<byte[]> ls = new ArrayList<>(keys.length);
        Cache cache = getCache();
        for (String key : keys) {
            Cache.ValueWrapper element = cache.get(key);
            if (null != element) {
                ls.add((byte[]) element.get());
            }
        }
        return ls;
    }
}

