package com.itdreamworks.newdatacache.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheUtil {
    @Autowired
    private ByteCacheUtil byteCacheUtil;

    public byte[] getCacheData(String key) {
        return byteCacheUtil.getCacheData(key);
    }

    public void putData(String key, byte[] data) {
        byteCacheUtil.putData(key, data);
    }

    public List<byte[]> getCacheDatas(String[] keys) {
        ArrayList<byte[]> ls = new ArrayList<>(keys.length);
        if (keys.length > 0) {
            return byteCacheUtil.getCacheDatas(keys);
        }
        return ls;
    }
}
