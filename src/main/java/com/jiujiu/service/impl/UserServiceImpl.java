package com.jiujiu.service.impl;

import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiu.entity.response.DietaryAdviceRes;
import com.jiujiu.mapper.UserInfoMapper;
import com.jiujiu.model.Food;
import com.jiujiu.model.UserInfo;
import com.jiujiu.service.UserService;
import com.jiujiu.util.DietRequiredUtil;
import com.jiujiuwisdom.utils.AppletResult;
import com.jiujiuwisdom.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final Logger LOGGER  = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public AppletResult  getHomePageNutritionAndExerciseInfo(UserInfoReq userInfoReq) {

        UserInfo userInfo = userInfoMapper.selectUserBaseInfo(userInfoReq.getUserId());

        int heat = DietRequiredUtil.calculateHeat(userInfo).intValue();

        int protein = DietRequiredUtil.calculateProtein(heat).intValue();

        return ResultUtil.success(new DietaryAdviceRes(heat,protein,12,new ArrayList<>()));
    }
}
