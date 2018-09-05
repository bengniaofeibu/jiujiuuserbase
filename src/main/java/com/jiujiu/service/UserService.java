package com.jiujiu.service;


import com.jiujiu.entity.request.UserBaseInfoReq;
import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiu.entity.request.UserRegisterLoginReq;
import com.jiujiuwisdom.utils.AppletResult;

public interface UserService {


   /**
    * 用户注册登录
    * @param userRegisterLoginReq
    * @return
    */
   AppletResult userRegisterLogin(UserRegisterLoginReq userRegisterLoginReq);


   /**
    * 获取用户信息
    * @param userInfoReq
    * @return
    */
   AppletResult getUserInfo(UserInfoReq userInfoReq);


   /**
    * 完善用户资料
    * @param userBaseInfoReq
    * @return
    */
   AppletResult perfectUserBaseInfo(UserBaseInfoReq userBaseInfoReq);

}
