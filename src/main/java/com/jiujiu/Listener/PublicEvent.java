package com.jiujiu.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class PublicEvent {

    @Autowired
    private ApplicationContext applicationContext;

    public void publicEvent(BaseListener baseListener){
        applicationContext.publishEvent(baseListener);
    }
}
