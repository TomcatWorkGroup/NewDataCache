package com.itdreamworks.newdatacache.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdreamworks.newdatacache.config.DeviceFocusConfig;
import com.itdreamworks.newdatacache.entity.Device;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;

@Component
public class DeviceRunJsonMsgReceiver extends BaseReceiver {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    DeviceFocusConfig deviceFocusConfig;


    @RabbitListener(queues = "${listeningQueue.json}")
    @RabbitHandler
    public void process(byte[] msg) {
        String jsonStr = new String(msg, CHAR_SET);
        //logger.error("DeviceRunMsgReceiver receiver msg");
        try {
            LinkedHashMap jsonObj = (LinkedHashMap) mapper.readValue(jsonStr, Object.class);
            String key = jsonObj.get(Device.KEY_DEVICE_NO).toString();
            String nickName = jsonObj.get(Device.KEY_NICK_NAME).toString();
            jsonObj.remove(Device.KEY_DEVICE_NO);
            jsonObj.remove(Device.KEY_NICK_NAME);
            Device device = cacheUtil.getDevice(key);
            if (null == device) {
                device = Device.getInstance(key);
                device.setDeviceNo(key);
                device.setNickName(nickName);
                modifyDeviceFields(jsonObj, device);
                device.setComeDate(new Date());
                cacheUtil.putDevice(device);
                //sender.send(DeviceSnapshot.getDeviceSnapshot(device).toJson());
                //logger.error("DeviceRunMsgReceiver put cache and send storage msg");
            } else {
                modifyDeviceFields(jsonObj, device);
                //logger.error("DeviceRunMsgReceiver modify device fields");
            }
            device.initFocusItems(deviceFocusConfig);
        } catch (IOException e1) {
            e1.printStackTrace();
            logger.error(e1.getMessage());
        }
    }

    private void modifyDeviceFields(LinkedHashMap map, Device device) {
        map.remove(Device.KEY_DEVICE_NO);
        Field[] fs = device.getClass().getDeclaredFields();
        for (Field f : fs) {
            String key = f.getName();
            if (map.containsKey(key)) {
                float value = Float.parseFloat(map.get(key).toString());
                int ivalue = (int)value;
                f.setAccessible(true);
                try {
                    if(value > ivalue)
                        f.set(device, value);
                    else
                        f.set(device,ivalue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        }
    }
}

