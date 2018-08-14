package com.itdreamworks.newdatacache.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class BaseReceiver {
    static Charset CHAR_SET = Charset.forName("utf-8");
    static final String EXCHANGE_NAME = "DeviceMsgExchange";
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
