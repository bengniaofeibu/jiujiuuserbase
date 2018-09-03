package com.jiujiu.service.impl;

import com.jiujiu.util.PublicEvent;
import com.jiujiu.Listener.UserListener;
import com.jiujiu.entity.request.UserBaseInfoReq;
import com.jiujiu.entity.request.UserInfoReq;
import com.jiujiu.entity.response.DietaryAdviceRes;
import com.jiujiu.enums.ResultEnums;
import com.jiujiu.mapper.HUserBaseInfoMapper;
import com.jiujiu.mapper.UserInfoMapper;
import com.jiujiu.model.HUserBaseInfo;
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
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HUserBaseInfoMapper hUserBaseInfoMapper;

    @Autowired
    private PublicEvent publicEvent;

    private static final Logger LOGGER  = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 完善用户资料
     *
     * @param userBaseInfoReq
     * @return
     */
    @Override
    public AppletResult perfectUserBaseInfo(UserBaseInfoReq userBaseInfoReq) {


        String birthStr = userBaseInfoReq.getBirth().toString();
        Date date = new Date(birthStr.length() == 10?userBaseInfoReq.getBirth()*1000:userBaseInfoReq.getBirth());
        HUserBaseInfo userBaseInfo = new HUserBaseInfo(userBaseInfoReq.getUserId(),userBaseInfoReq.getAge(),userBaseInfoReq.getWeight(),
                userBaseInfoReq.getHeight(), date,userBaseInfoReq.getWorkType(),userBaseInfoReq.getUserGender());

        int insertCount  = hUserBaseInfoMapper.insertUserBaseInfo(userBaseInfo);

        if (insertCount > 0){

            //更新成功后，异步处理更新用户信息
            publicEvent.publicEvent(new UserListener(this,userBaseInfoReq));
            return ResultUtil.success(ResultEnums.PERFECT_USER_BASE_INFO_OK);
        }

        return ResultUtil.success(ResultEnums.PERFECT_USER_BASE_INFO_FAIL);
    }

    @Override
    public AppletResult  getHomePageNutritionAndExerciseInfo(UserInfoReq userInfoReq) {

        //获取用户信息
        UserInfo userInfo = userInfoMapper.selectUserBaseInfo(userInfoReq.getUserId());

        //获取用户今日所需热量
        int heat = DietRequiredUtil.calculateHeat(userInfo).intValue();

        //获取用户今日所需蛋白质
        int protein = DietRequiredUtil.calculateProtein(heat).intValue();

        return ResultUtil.success(new DietaryAdviceRes(heat,protein,12,new ArrayList<>()));
    }
}
