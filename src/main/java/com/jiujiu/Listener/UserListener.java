package com.jiujiu.Listener;

import com.jiujiu.base.BaseListener;
import com.jiujiu.entity.request.UserBaseInfoReq;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListener extends BaseListener<UserBaseInfoReq> {


    /**
     * Create a new ApplicationEvent.
     *
     * @param source   the object on which the event initially occurred (never {@code null})
     * @param userBaseInfoReq
     */
    public UserListener(Object source, UserBaseInfoReq userBaseInfoReq) {
        super(source, userBaseInfoReq);
    }
}
