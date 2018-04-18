package com.itdreamworks.newdatacache.controller;

import com.itdreamworks.newdatacache.entity.Device;
import com.itdreamworks.newdatacache.entity.DeviceSnapshot;
import com.itdreamworks.newdatacache.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/output")
public class DataOutputController {

    @Autowired
    CacheUtil cacheUtil;

    /**
     * 获取设备快照信息-快照信息可以缓存几分钟以减少服务器计算压力
     *
     * @param devicesIds
     * @return
     */
    @PostMapping(value = "/devices")
    public List<DeviceSnapshot> getDevicesSnapshot(@RequestParam(name = "ids") String devicesIds) {
        String[] deviceKeys = devicesIds.split(",");
        return DeviceSnapshot.getDeviceSnapshotList(cacheUtil.getDevices(deviceKeys));
    }

    /**
     * 获取设备详细信息
     *
     * @param deviceId
     * @return
     */
    @PostMapping(value = "/device")
    public Device getDeviceInfo(@RequestParam(name = "id") String deviceId) {
        return cacheUtil.getDevice(deviceId);
    }

   }
