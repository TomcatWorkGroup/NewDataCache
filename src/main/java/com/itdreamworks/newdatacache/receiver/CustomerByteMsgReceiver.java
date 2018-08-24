package com.itdreamworks.newdatacache.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * 特殊客户设备信息接收程序
 * 目前只有前田热力在用
 */
@Component
public class CustomerByteMsgReceiver extends BaseReceiver {

    @Autowired
    private com.itdreamworks.newdatacache.utils.CacheUtil cacheUtil;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("CustomerPlcMsgQueue"),
            exchange = @Exchange(EXCHANGE_NAME),
            key = "CustomerPlcMsg"
    ))
    @RabbitHandler
    public void process(byte[] msg) {
        String key = String.format("%1$010d", new BigInteger(new byte[]{msg[5], msg[6], msg[7], msg[8]}).intValue());
        cacheUtil.putData(key, msg);
    }
}

