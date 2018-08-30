package com.jiujiu.service;


import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiuwisdom.utils.AppletResult;

public interface UserService {

   AppletResult getHomePageNutritionAndExerciseInfo(UserInfoReq userInfoReq);

}
