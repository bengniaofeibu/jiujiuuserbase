package com.jiujiu.service;


import com.jiujiu.entity.request.UserBaseInfoReq;
import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiuwisdom.utils.AppletResult;

public interface UserService {

   /**
    * 完善用户资料
    * @param userBaseInfoReq
    * @return
    */
   AppletResult perfectUserBaseInfo(UserBaseInfoReq userBaseInfoReq);

   /**
    * 获取首页饮食建议，运动信息和文章信息
    * @param userInfoReq
    * @return
    */
   AppletResult getHomePageNutritionAndExerciseInfo(UserInfoReq userInfoReq);

}
