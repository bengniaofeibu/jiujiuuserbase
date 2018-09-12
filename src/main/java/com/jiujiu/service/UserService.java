package com.jiujiu.service;


import com.jiujiu.entity.request.*;
import com.jiujiu.entity.response.ArticleInfoRes;
import com.jiujiu.entity.response.ClassScheduleRes;
import com.jiujiu.entity.response.ClassScheduleResPage;
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


   /**
    * 获取上课日程
    * @param classScheduleReq
    * @return
    */
   AppletResult<ClassScheduleResPage> getClassSchedule(ClassScheduleReq classScheduleReq);

   /**
    * 获取文章信息
    * @param articleInfoReq
    * @return
    */
   AppletResult<ArticleInfoRes> getArticleInfo(ArticleInfoReq articleInfoReq);

}
