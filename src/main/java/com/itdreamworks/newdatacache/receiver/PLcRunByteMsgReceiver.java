package com.itdreamworks.newdatacache.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;


@Component
public class PLcRunByteMsgReceiver extends BaseReceiver {

    @Autowired
    private com.itdreamworks.newdatacache.utils.CacheUtil cacheUtil;

    @RabbitListener(queues = "BytePlcMsgQueue")
    @RabbitHandler
    public void process(byte[] msg) {
        String key = String.format("%1$010d", new BigInteger(new byte[]{msg[5], msg[6], msg[7], msg[8]}).intValue());
        cacheUtil.putData(key, msg);
    }
}

