package com.jiujiu.Listener;


import com.jiujiu.base.BaseListener;
import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiu.entity.request.UserRegisterLoginReq;

public class UserElsewhereLoginListener extends BaseListener<UserRegisterLoginReq> {

    public UserElsewhereLoginListener(Object source, UserRegisterLoginReq userRegisterLoginReq) {
        super(source, userRegisterLoginReq);
    }
}
