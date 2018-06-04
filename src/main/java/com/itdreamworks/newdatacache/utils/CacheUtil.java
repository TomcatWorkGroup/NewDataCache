package com.itdreamworks.newdatacache.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheUtil {
    @Autowired
    private ByteCacheUtil byteCacheUtil;
    @Autowired
    private Byte2CacheUtil byte2CacheUtil;

    public byte[] getCacheData(String key) {
        if (key.length() == 10) {
            return byteCacheUtil.getCacheData(key);
        } else if (key.length() == 20) {
            return byte2CacheUtil.getCacheData(key);
        }
        return null;
    }

    public void putData(String key, byte[] data) {
        if(key.length() == 10){
            byteCacheUtil.putData(key, data);
        }else if(key.length() == 20){
            byte2CacheUtil.putData(key, data);
            byteCacheUtil.putData(key.substring(10), data);
        }
    }

    public List<byte[]> getCacheDatas(String[] keys) {
        ArrayList<byte[]> ls = new ArrayList<>(keys.length);
        if (keys.length > 0) {
            if (keys[0].length() == 10) {
                return byteCacheUtil.getCacheDatas(keys);
            } else if (keys[0].length() == 20) {
                return byte2CacheUtil.getCacheDatas(keys);
            }
        }
        return ls;
    }
}
