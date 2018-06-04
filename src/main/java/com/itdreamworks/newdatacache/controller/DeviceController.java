package com.itdreamworks.newdatacache.controller;

import com.itdreamworks.newdatacache.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    CacheUtil cacheUtil;

    /**
     * 获取设备快照信息-快照信息可以缓存几分钟以减少服务器计算压力
     *
     * @param devicesIds
     * @return
     */
    @PostMapping(value = "/find")
    public void getDevicesInfo(@RequestParam(name = "ids") String devicesIds, HttpServletResponse response) {
        String[] deviceKeys = devicesIds.split(",");
        List<byte[]> data = cacheUtil.getCacheDatas(deviceKeys);
        try {
            ServletOutputStream stream = response.getOutputStream();
            for (byte[] d : data) {
                stream.write(d);
            }
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备详细信息
     *
     * @param deviceId
     * @return
     */
    @PostMapping(value = "/get")
    public void getDeviceInfo(@RequestParam(name = "id") String deviceId, HttpServletResponse response) {
        byte[] data = cacheUtil.getCacheData(deviceId);
        if (null == data)
            return;
        try {
            ServletOutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
