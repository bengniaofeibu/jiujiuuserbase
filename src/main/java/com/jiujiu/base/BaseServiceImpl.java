package com.jiujiu.base;

import com.jiujiu.util.PublicEvent;
import com.jiujiu.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl {

    @Autowired
    protected PublicEvent publicEvent;

    @Autowired
    protected SmsUtil smsUtil;
}
