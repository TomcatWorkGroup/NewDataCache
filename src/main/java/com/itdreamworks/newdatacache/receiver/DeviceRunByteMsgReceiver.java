package com.itdreamworks.newdatacache.receiver;

import com.itdreamworks.newdatacache.utils.CacheUtil;
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
public class DeviceRunByteMsgReceiver extends BaseReceiver {


    @RabbitListener(queues = "ByteDeviceMsgQueue")
    @RabbitHandler
    public void process(byte[] msg) {
        String key = new String(msg,0,20, CHAR_SET);
        cacheUtil.putData(key,msg);
    }
}

