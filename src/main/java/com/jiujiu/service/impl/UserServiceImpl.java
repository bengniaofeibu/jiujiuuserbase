package com.jiujiu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jiujiu.Listener.UserElsewhereLoginListener;
import com.jiujiu.base.BaseServiceImpl;
import com.jiujiu.entity.request.*;
import com.jiujiu.entity.response.*;
import com.jiujiu.mapper.HProjectCourseMapper;
import com.jiujiu.mapper.SystemUserMapper;
import com.jiujiu.Listener.UserInfoUpdateListener;
import com.jiujiu.enums.ResultEnums;
import com.jiujiu.mapper.HUserBaseInfoMapper;
import com.jiujiu.mapper.UserInfoMapper;
import com.jiujiu.model.ArticleInfo;
import com.jiujiu.model.HProjectCourse;
import com.jiujiu.model.HUserBaseInfo;
import com.jiujiu.model.UserInfo;
import com.jiujiu.service.UserService;
import com.jiujiuwisdom.constant.DateFormatConstant;
import com.jiujiuwisdom.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HUserBaseInfoMapper hUserBaseInfoMapper;

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private HProjectCourseMapper projectCourseMapper;


    private static final Logger LOGGER  = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 用户注册登录
     *
     * @param userRegisterLoginReq
     * @return
     */
    @Override
    public AppletResult userRegisterLogin(UserRegisterLoginReq userRegisterLoginReq) {

        final String userPhone = userRegisterLoginReq.getPhone();

        //验证手机号格式
        boolean phoneFormat = RegularUtil.verifyPhoneFormat(userPhone);

        if (!phoneFormat){

            return ResultUtil.error(ResultEnums.PHONE_FORMAT_FAIL);

        }

        //验证短信验证码是否正确
        boolean isCheck = smsUtil.checkSMSVerificationCode(userPhone, userRegisterLoginReq.getVerificationCode());

        if ( !isCheck){
            return ResultUtil.error(ResultEnums.VERIFICATION_CODE_CHECK_FAIL);
        }

        //获取用户信息
        UserInfo userBaseInfo = userInfoMapper.selectUserBaseInfo(userPhone);
        LOGGER.debug("用户信息 {}",userBaseInfo);


        //登录时间
        Date loginTime = new Date();

        int dataStatus = 0; //用户是否完善资料状态

        //用户id
        String userId;

        //判断用户是已经注册过了
        if ( userBaseInfo  == null ){

            userBaseInfo = new UserInfo();

            userId = UUIDUtil.randomUUID();

            //新注册
            userBaseInfo.setPhone(userPhone);
            userBaseInfo.setLoginTime(loginTime);
            userBaseInfo.setId(userId);
            userBaseInfo.setAppVersion(userRegisterLoginReq.getAppVersion());
            userBaseInfo.setNickname(new StringBuilder("99").append(userPhone.substring(7,userPhone.length())).toString());
            userBaseInfo.setPicurl("");
            userInfoMapper.insertNewUser(userBaseInfo);

        } else {

            userId = userInfoMapper.selectUserIdByPhone(userPhone);

            //赋值用户是否完善资料状态
            dataStatus = userBaseInfo.getUserBaseInfo().getStatus();

            //已经注册
            userBaseInfo.setAppVersion(userRegisterLoginReq.getAppVersion());
            userInfoMapper.updateUserInfoByUserIdOrPhone(userBaseInfo);

            //异步处理更新用户deviceToken或通知用户别处登录
            publicEvent.publicEvent(new UserElsewhereLoginListener(this,userRegisterLoginReq));

        }

        return ResultUtil.success(new UserRegisterLoginRes(userId,dataStatus));
    }

    /**
     * 获取用户信息
     *
     * @param userInfoReq
     * @return
     */
    @Override
    public AppletResult<UserInfoRes> getUserInfo(UserInfoReq userInfoReq) {

        UserInfo userInfo = userInfoMapper.selectUserBaseInfo(userInfoReq.getUserId());

        if ( userInfo == null ){

            return ResultUtil.error(ResultEnums.USER_NOT_FOUND_INFO_FAIL);
        }

        //获取用户类型
        Integer userType = systemUserMapper.selectUserTypeByPhone(userInfo.getPhone());

        //用户完善的信息
        HUserBaseInfo userBaseInfo = userInfo.getUserBaseInfo();

        if ( userType == null || userType != 1){

            // 0 普通用户 1 教练 2 企业用户
            userType = StringUtils.isBlank(userBaseInfo.getCustomId())? 0:2;
        }


          //用户生日
         String userBirth = DateUtil.format(userBaseInfo.getBirth(), DateFormatConstant.DATE_FMT_4);

         UserInfoRes userInfoRes = new UserInfoRes(userInfo.getPicurl(), userInfo.getNickname(), userInfo.getGender(),
                userInfo.getPhone(), userBirth, userBaseInfo.getHeight(),
                userBaseInfo.getWeight(), userBaseInfo.getWorkType(),userType);

        return ResultUtil.success(userInfoRes);
    }

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

        HUserBaseInfo userBaseInfo = new HUserBaseInfo(userBaseInfoReq.getUserId(),userBaseInfoReq.getWeight(),
                userBaseInfoReq.getHeight(), date,userBaseInfoReq.getWorkType(),userBaseInfoReq.getUserGender());

        //记录用户完善的资料
        int insertCount  = hUserBaseInfoMapper.insertUserBaseInfo(userBaseInfo);

        //判断是否已经存入数据库中
        if (insertCount > 0){

            //更新成功后，异步处理更新用户信息
            publicEvent.publicEvent(new UserInfoUpdateListener(this,userBaseInfoReq));
            return ResultUtil.success(ResultEnums.PERFECT_USER_BASE_INFO_OK);
        }

        return ResultUtil.success(ResultEnums.PERFECT_USER_BASE_INFO_FAIL);
    }

    /**
     * 获取上课日程
     *
     * @param classScheduleReq
     * @return
     */
    @Override
    public AppletResult<ClassScheduleResPage> getClassSchedule(ClassScheduleReq classScheduleReq) {

        Page<HProjectCourse> page = PageHelper.startPage(classScheduleReq.getCurrentPage(), 10, false);

        List<HProjectCourse> hProjectCourses = projectCourseMapper.selectCourseInfoByCoachId(classScheduleReq.getUserId());

        if (hProjectCourses == null || hProjectCourses.size() == 0){

            return ResultUtil.success(ResultEnums.CLASS_NOT_FOUND_FAIL);
        }

        List<ClassScheduleRes> classScheduleList = new LinkedList<>();
        ClassScheduleRes classScheduleRes;
        for(HProjectCourse hProjectCourse:hProjectCourses){
            classScheduleRes = new ClassScheduleRes();
            classScheduleRes.setCompanyId(hProjectCourse.getCustomId());
            classScheduleRes.setClassId(hProjectCourse.getCourseBaseId());
            classScheduleRes.setClassCheckoutCount(hProjectCourse.getCheckoutCount());
            classScheduleRes.setClassCode("");
            classScheduleRes.setClassTime(DateUtil.format(hProjectCourse.getClassTime(),DateFormatConstant.TIME_PATTERN));
            classScheduleRes.setClassName(hProjectCourse.getHCourseBaseInfo().getName());
            classScheduleRes.setClassCompany(hProjectCourse.getHCustom().getCustomName());
            classScheduleRes.setClassAddress(hProjectCourse.getHCustom().getCustomAddr());
            classScheduleList.add(classScheduleRes);
        }

        //根据上课时间倒序
        classScheduleList.sort(Comparator.comparing(ClassScheduleRes::getClassTime).reversed());

        ClassScheduleResPage classScheduleResPage = new ClassScheduleResPage();
        classScheduleResPage.setClassScheduleResList(classScheduleList);
        classScheduleResPage.setTotalPage(page.getPages());
        return ResultUtil.success(classScheduleResPage);
    }

    /**
     * 获取文章信息
     *
     * @param articleInfoReq
     * @return
     */
    @Override
    public AppletResult<ArticleInfoRes> getArticleInfo(ArticleInfoReq articleInfoReq) {

        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle("我离美人鱼的距离只差一条人鱼线？");
        articleInfo.setDesc("减肥事业年复一年，何时是个头啊");
        articleInfo.setAuthor("乌鸦白云");
        articleInfo.setPublishedTime(DateUtil.format(new Date(),DateFormatConstant.DATE_FMT_9));

        ArticleInfo articleInfo2 = new ArticleInfo();
        articleInfo2.setTitle("我离美人鱼的距离只差一条人鱼线？");
        articleInfo2.setDesc("减肥事业年复一年，何时是个头啊");
        articleInfo2.setAuthor("乌鸦白云");
        articleInfo2.setPublishedTime(DateUtil.format(new Date(),DateFormatConstant.DATE_FMT_9));

        List<ArticleInfo> articleInfoList = new LinkedList<>();
        articleInfoList.add(articleInfo);
        articleInfoList.add(articleInfo2);

        return ResultUtil.success(new ArticleInfoRes(articleInfoList));
    }
}
