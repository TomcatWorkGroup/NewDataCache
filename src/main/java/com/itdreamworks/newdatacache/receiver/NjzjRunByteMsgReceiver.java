package com.itdreamworks.newdatacache.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class NjzjRunByteMsgReceiver extends BaseReceiver {

    @Autowired
    private com.itdreamworks.newdatacache.utils.CacheUtil cacheUtil;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("ByteNjzjMsgQueue"),
            exchange = @Exchange(EXCHANGE_NAME),
            key = "NJZJ_CTL"
    ))
    @RabbitHandler
    public void process(byte[] msg) {
        String key = new String(msg, 10, 10, CHAR_SET);
        cacheUtil.putData(key, msg);
    }
}

