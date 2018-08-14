package com.itdreamworks.newdatacache.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;


@Component
public class NjrtT2RunByteMsgReceiver extends BaseReceiver {

    @Autowired
    private com.itdreamworks.newdatacache.utils.CacheUtil cacheUtil;

    
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("ByteNjrtMsgQueue"),
            exchange = @Exchange(EXCHANGE_NAME),
            key = "NJRT_T2"
    ))
    @RabbitHandler
    public void process(byte[] msg) {
        //String deviceNo = new String(msg, 67, 20);
        String key = String.format("%d%d%d%d%d%d%d%d%d%d",
                msg[68],msg[70],msg[72],msg[74],msg[76],
                msg[78],msg[80],msg[82],msg[84],msg[86]);
        cacheUtil.putData(key, msg);
    }
}

