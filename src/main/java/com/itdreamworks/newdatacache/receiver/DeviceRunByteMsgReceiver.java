package com.itdreamworks.newdatacache.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class DeviceRunByteMsgReceiver extends BaseReceiver {

    @Autowired
    private com.itdreamworks.newdatacache.utils.CacheUtil cacheUtil;

    @RabbitListener(queues = "ByteDeviceMsgQueue")
    @RabbitHandler
    public void process(byte[] msg) {
        String key = new String(msg, 0, 20, CHAR_SET);
        cacheUtil.putData(key, msg);
    }
}

